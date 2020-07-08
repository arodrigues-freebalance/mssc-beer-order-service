package akr.microtraining.brewery.model.events;

import java.io.Serializable;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ValidateOrderResult implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -309986826101672976L;
	private UUID orderId;
    private Boolean isValid;
}