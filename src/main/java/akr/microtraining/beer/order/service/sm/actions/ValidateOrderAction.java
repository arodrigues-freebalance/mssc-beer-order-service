package akr.microtraining.beer.order.service.sm.actions;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import akr.microtraining.beer.order.service.config.JmsConfig;
import akr.microtraining.beer.order.service.domain.BeerOrder;
import akr.microtraining.beer.order.service.domain.BeerOrderEventEnum;
import akr.microtraining.beer.order.service.domain.BeerOrderStatusEnum;
import akr.microtraining.beer.order.service.repositories.BeerOrderRepository;
import akr.microtraining.beer.order.service.services.BeerOrderManagerImpl;
import akr.microtraining.beer.order.service.web.mappers.BeerOrderMapper;
import akr.microtraining.brewery.model.events.ValidateOrderRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
@RequiredArgsConstructor
public class ValidateOrderAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final BeerOrderRepository beerOrderRepository;
    private final BeerOrderMapper beerOrderMapper;
    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);
        BeerOrder beerOrder = beerOrderRepository.findOneById(UUID.fromString(beerOrderId));

        jmsTemplate.convertAndSend(JmsConfig.VALIDATE_ORDER_QUEUE, ValidateOrderRequest.builder()
                    .beerOrder(beerOrderMapper.beerOrderToDto(beerOrder))
                    .build());

        log.debug("Sent Validation request to queue for order id " + beerOrderId);
    }
}