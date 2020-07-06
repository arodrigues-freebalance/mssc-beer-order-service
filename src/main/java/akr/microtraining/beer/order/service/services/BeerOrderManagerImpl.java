package akr.microtraining.beer.order.service.services;

import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import akr.microtraining.beer.order.service.domain.BeerOrder;
import akr.microtraining.beer.order.service.domain.BeerOrderEventEnum;
import akr.microtraining.beer.order.service.domain.BeerOrderStatusEnum;
import akr.microtraining.beer.order.service.repositories.BeerOrderRepository;
import akr.microtraining.beer.order.service.sm.BeerOrderStateChangeInterceptor;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BeerOrderManagerImpl implements BeerOrderManager {

	public static final String ORDER_ID_HEADER = "ORDER_ID_HEADER";
	
	private final StateMachineFactory<BeerOrderStatusEnum, BeerOrderEventEnum> stateMachineFactory;
	private final BeerOrderRepository beerOrderRepository;
	private final BeerOrderStateChangeInterceptor beerOrderStateChangeInterceptor;	

	@Transactional
	@Override
	public BeerOrder newBewOrder(BeerOrder beerOrder) {

		beerOrder.setId(null);
		beerOrder.setOrderStatus(BeerOrderStatusEnum.NEW);
		
		BeerOrder savedBeerOrder = beerOrderRepository.save(beerOrder);	
		
		sendBeerOrderEvent(savedBeerOrder, BeerOrderEventEnum.BEERORDER_PICKED_UP);
		
		return savedBeerOrder;
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private void sendBeerOrderEvent(BeerOrder beerOrder, BeerOrderEventEnum eventEnum) {
		
		StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = build(beerOrder);
		
		Message msg = MessageBuilder.withPayload(eventEnum)
				.setHeader(ORDER_ID_HEADER, beerOrder.getId().toString())
				.build();
		
		sm.sendEvent(msg);

				
	}

	private StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> build(BeerOrder beerOrder){
		StateMachine<BeerOrderStatusEnum, BeerOrderEventEnum> sm = stateMachineFactory.getStateMachine(beerOrder.getId());
		
		sm.stop();
		
		sm.getStateMachineAccessor()
			.doWithAllRegions(sma -> {
				sma.addStateMachineInterceptor(beerOrderStateChangeInterceptor);
				sma.resetStateMachine(new DefaultStateMachineContext<BeerOrderStatusEnum, BeerOrderEventEnum>(beerOrder.getOrderStatus(), null, null, null));
			});
		
		sm.start();
		
		return sm;
	}
	
	
}