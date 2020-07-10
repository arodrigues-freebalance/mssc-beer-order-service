package akr.microtraining.beer.order.service.web.mappers;

import org.mapstruct.Mapper;

import akr.microtraining.beer.order.service.domain.Customer;
import akr.microtraining.brewery.model.CustomerDto;

@Mapper(uses = {DateMapper.class})
public interface CustomerMapper {
    CustomerDto customerToDto(Customer customer);

    Customer dtoToCustomer(Customer dto);
}