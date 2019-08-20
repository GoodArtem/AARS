package org.gudartem.aars.db.model;

public interface HasId<T> extends HasNullFields, HasRevision {
    T getId();

    void setId(T id);
}
