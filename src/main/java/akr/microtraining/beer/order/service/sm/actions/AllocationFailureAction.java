package akr.microtraining.beer.order.service.sm.actions;

import java.util.UUID;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.statemachine.StateContext;
import org.springframework.statemachine.action.Action;
import org.springframework.stereotype.Component;

import akr.microtraining.beer.order.service.config.JmsConfig;
import akr.microtraining.beer.order.service.domain.BeerOrderEventEnum;
import akr.microtraining.beer.order.service.domain.BeerOrderStatusEnum;
import akr.microtraining.beer.order.service.services.BeerOrderManagerImpl;
import akr.microtraining.brewery.model.events.AllocationFailureEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
@Component
public class AllocationFailureAction implements Action<BeerOrderStatusEnum, BeerOrderEventEnum> {

    private final JmsTemplate jmsTemplate;

    @Override
    public void execute(StateContext<BeerOrderStatusEnum, BeerOrderEventEnum> context) {
        String beerOrderId = (String) context.getMessage().getHeaders().get(BeerOrderManagerImpl.ORDER_ID_HEADER);

        jmsTemplate.convertAndSend(JmsConfig.ALLOCATE_FAILURE_QUEUE, AllocationFailureEvent.builder()
            .orderId(UUID.fromString(beerOrderId))
                    .build());

        log.debug("Sent Allocation Failure Message to queue for order id " + beerOrderId);
    }
} 