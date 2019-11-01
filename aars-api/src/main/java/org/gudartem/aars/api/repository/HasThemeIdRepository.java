package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;

public interface HasThemeIdRepository<T extends HasId<ID>, ID extends Serializable> extends Repository<T, ID> {
    Collection<T> getAllByThemeId(ID themeId);
}
