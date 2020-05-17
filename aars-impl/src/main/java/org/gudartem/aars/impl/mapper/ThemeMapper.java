package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Theme;
import org.gudartem.aars.model.dto.ThemeDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, uses = DirectoryMapper.class)
public interface ThemeMapper extends EntityMapper<Theme, ThemeDto> {
}
