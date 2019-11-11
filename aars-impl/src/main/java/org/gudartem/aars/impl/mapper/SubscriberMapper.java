package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.gudartem.aars.model.dto.SubscriberDto;
import org.mapstruct.Mapper;

import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class)
public interface SubscriberMapper extends EntityMapper<Subscriber, SubscriberDto> {
}
