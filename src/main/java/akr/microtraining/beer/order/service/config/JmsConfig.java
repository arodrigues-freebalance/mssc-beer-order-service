package akr.microtraining.beer.order.service.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.jms.support.converter.MappingJackson2MessageConverter;
import org.springframework.jms.support.converter.MessageConverter;
import org.springframework.jms.support.converter.MessageType;

import com.fasterxml.jackson.databind.ObjectMapper;

@Configuration
public class JmsConfig {
	
	public static final String VALIDATE_ORDER_QUEUE = "validate-order";
	public static final String VALIDATE_ORDER_RESPONSE_QUEUE = "validate-order-response";
	public static final String ALLOCATE_ORDER_QUEUE = "allocate-order";
	public static final String ALLOCATE_ORDER_RESPONSE_QUEUE = "allocate-order-response";
	
	public MessageConverter jacksonJmsMessageConverter(ObjectMapper objectMapper) {
		MappingJackson2MessageConverter converter =  new MappingJackson2MessageConverter();
		converter.setTargetType(MessageType.TEXT);
		converter.setTypeIdPropertyName("_type");
		converter.setObjectMapper(objectMapper);
		return converter;
		
	}
	
}
