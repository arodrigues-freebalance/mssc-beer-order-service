package akr.microtraining.beer.order.service.services;

import org.springframework.data.domain.Pageable;

import akr.microtraining.brewery.model.CustomerPagedList;

public interface CustomerService {

    CustomerPagedList listCustomers(Pageable pageable);

}