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
public class AllocateOrderRequest implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 8559959847192251002L;
	private BeerOrderDto beerOrderDto;
}