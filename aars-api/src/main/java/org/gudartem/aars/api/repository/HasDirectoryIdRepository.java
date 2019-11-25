package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.List;

public interface HasDirectoryIdRepository<T extends HasId<ID>, ID extends Serializable>
        extends HasThemeIdRepository<T, ID> {
    List<T> getAllByDirectoryId(ID directoryId);
}
