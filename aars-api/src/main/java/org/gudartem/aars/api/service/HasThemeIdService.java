package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.Collection;

public interface HasThemeIdService<E extends HasId<ID>, ID extends Serializable>
        extends CRUDService<E, ID>  {
    Collection<E> getAllByThemeId(ID themeId);
}
