package akr.microtraining.brewery.model.events;

import java.io.Serializable;

import akr.microtraining.brewery.model.BeerOrderDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AllocateOrderResult implements Serializable{
    /**
	 * 
	 */
	private static final long serialVersionUID = -8871393604664868884L;
	private BeerOrderDto beerOrderDto;
    private Boolean allocationError = false;
    private Boolean pendingInventory = false;
}