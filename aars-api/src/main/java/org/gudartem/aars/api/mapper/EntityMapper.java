package org.gudartem.aars.api.mapper;

import java.util.Collection;

public interface EntityMapper<DomainObject, Dto> {
    Dto toDto(DomainObject domainObject);

    DomainObject toDomainObject(Dto dto);

    Collection<Dto> toCollectionDto(Collection<DomainObject> domainObjectCollection);
}
