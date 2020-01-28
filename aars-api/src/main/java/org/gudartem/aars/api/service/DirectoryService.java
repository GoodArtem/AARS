package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.List;

public interface DirectoryService<E extends HasId<ID>, ID extends Serializable> extends HasDirectoryIdService<E, ID> {
    List<E> getPathToTheme(ID directoryId);
}
