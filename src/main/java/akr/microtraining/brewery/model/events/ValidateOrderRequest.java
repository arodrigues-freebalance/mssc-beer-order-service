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
public class ValidateOrderRequest implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -413605834255233875L;
	private BeerOrderDto beerOrder;
}