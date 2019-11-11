package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Directory;
import org.gudartem.aars.model.dto.DirectoryDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface DirectoryMapper extends EntityMapper<Directory, DirectoryDto> {
}
