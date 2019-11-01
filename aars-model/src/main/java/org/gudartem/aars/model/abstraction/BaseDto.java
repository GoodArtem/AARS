package org.gudartem.aars.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gudartem.aars.model.HasId;

import java.util.Collection;
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

    @JsonIgnore
    @Override
    public Set<String> getNullFields() {
        return nullFields;
    }

    @Override
    public void setNullFields(Set<String> nullFields) {
        this.nullFields = nullFields;
    }

    protected void addNullField(String fieldName, Object fieldValue) {
        if (fieldValue == null) {
            getNullFields().add(fieldName);
        }
    }

    protected void addNullField(String fieldName, Collection fieldValue) {
        if (fieldValue == null || fieldValue.isEmpty()) {
            getNullFields().add(fieldName);
        }
    }
}
