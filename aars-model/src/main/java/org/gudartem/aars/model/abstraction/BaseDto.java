package org.gudartem.aars.model.abstraction;

import org.gudartem.aars.model.HasId;

import java.util.HashSet;
import java.util.Set;

public class BaseDto<T> implements HasId<T> {
    private T id;

    private Integer revision;

    private Set<String> nullFields = new HashSet<>();

    @Override
    public T getId() {
        return id;
    }

    @Override
    public void setId(T id) {
        this.id = id;
    }

    @Override
    public Integer getRevision() {
        return revision;
    }

    @Override
    public void setRevision(Integer revision) {
        this.revision = revision;
    }

    @Override
    public Set<String> getNullFields() {
        return null;
    }

    @Override
    public void setNullFields(Set<String> nullFields) {
        this.nullFields = nullFields;
    }
}
