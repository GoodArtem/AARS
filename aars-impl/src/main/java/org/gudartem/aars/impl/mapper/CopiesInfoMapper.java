package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.gudartem.aars.model.dto.CopiesInfoDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface CopiesInfoMapper extends EntityMapper<CopiesInfo, CopiesInfoDto> {
}
