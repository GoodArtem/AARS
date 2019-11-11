package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Applicability;
import org.gudartem.aars.model.dto.ApplicabilityDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface ApplicabilityMapper extends EntityMapper<Applicability, ApplicabilityDto> {
}
