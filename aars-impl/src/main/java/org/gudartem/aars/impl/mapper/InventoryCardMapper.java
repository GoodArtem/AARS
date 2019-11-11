package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.model.dto.InventoryCardDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", uses = {FormatMapper.class}, imports = UUID.class)
public interface InventoryCardMapper extends EntityMapper<InventoryCard, InventoryCardDto> {
}
