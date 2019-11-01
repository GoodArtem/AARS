package org.gudartem.aars.db.model.abstraction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.gudartem.aars.db.model.HasId;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static org.gudartem.aars.db.constants.ColumnName.HasId.ID;
import static org.gudartem.aars.db.constants.ColumnName.HasRevision.REVISION;
import static org.gudartem.aars.db.constants.PostgresCustomTypes.UUID_TYPE;

@MappedSuperclass
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class AbstractHasId<T> implements HasId<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = ID, columnDefinition = UUID_TYPE)
    private T id;

    @Column(name = REVISION)
    private Integer revision;

    @Transient
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


}
