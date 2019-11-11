package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.model.dto.StocktakingDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface StocktakingMapper extends EntityMapper<Stocktaking, StocktakingDto> {
}
