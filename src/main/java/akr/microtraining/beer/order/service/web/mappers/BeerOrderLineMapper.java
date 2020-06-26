package akr.microtraining.beer.order.service.web.mappers;

import org.mapstruct.Mapper;

import akr.microtraining.beer.order.service.domain.BeerOrderLine;
import akr.microtraining.beer.order.service.web.model.BeerOrderLineDto;

@Mapper(uses = {DateMapper.class})
public interface BeerOrderLineMapper {
    BeerOrderLineDto beerOrderLineToDto(BeerOrderLine line);

    BeerOrderLine dtoToBeerOrderLine(BeerOrderLineDto dto);
}