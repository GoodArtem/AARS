package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Format;
import org.gudartem.aars.model.dto.FormatDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface FormatMapper extends EntityMapper<Format, FormatDto> {
}
