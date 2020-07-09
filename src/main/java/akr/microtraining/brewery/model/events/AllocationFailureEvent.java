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
public class AllocationFailureEvent implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -9135790883457923229L;
	
	private UUID orderId;
}