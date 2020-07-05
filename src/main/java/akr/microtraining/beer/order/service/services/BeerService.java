package akr.microtraining.beer.order.service.services;

import java.util.Optional;
import java.util.UUID;

import akr.microtraining.brewery.model.BeerDto;

public interface BeerService {

    Optional<BeerDto> getBeerById(UUID uuid);

    Optional<BeerDto> getBeerByUpc(String upc);
}