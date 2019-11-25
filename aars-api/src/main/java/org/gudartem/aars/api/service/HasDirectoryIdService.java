package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.List;

public interface HasDirectoryIdService<E extends HasId<ID>, ID extends Serializable> extends HasThemeIdService<E, ID> {
    List<E> getAllByDirectoryId(ID directoryId);
}
