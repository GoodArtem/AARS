package org.gudartem.aars.api.service;

import org.gudartem.aars.db.model.HasId;

import java.io.Serializable;
import java.util.List;

public interface HasThemeIdService<E extends HasId<ID>, ID extends Serializable>
        extends CRUDService<E, ID>  {
    List<E> getAllByThemeId(ID themeId);
}
