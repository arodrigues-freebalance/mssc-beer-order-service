package akr.microtraining.beer.order.service.services;

import java.util.UUID;

import akr.microtraining.beer.order.service.domain.BeerOrder;

public interface BeerOrderManager {

		BeerOrder newBewOrder(BeerOrder beerOrder );
		
		void processValidationResult(UUID beerOrderId, Boolean isValid);
}
