package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.gudartem.aars.model.dto.OnceOnlyIssueDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface OnceOnlyIssueMapper extends EntityMapper<OnceOnlyIssue, OnceOnlyIssueDto> {
}
