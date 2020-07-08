package akr.microtraining.beer.order.service.services;

import java.util.UUID;

import akr.microtraining.beer.order.service.domain.BeerOrder;
import akr.microtraining.brewery.model.BeerOrderDto;

public interface BeerOrderManager {

		BeerOrder newBeerOrder(BeerOrder beerOrder );
		
		void processValidationResult(UUID beerOrderId, Boolean isValid);
		
	    void beerOrderAllocationPassed(BeerOrderDto beerOrder);

	    void beerOrderAllocationPendingInventory(BeerOrderDto beerOrder);

	    void beerOrderAllocationFailed(BeerOrderDto beerOrder);		
	    
}
