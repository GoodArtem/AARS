package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;
import org.jooq.Condition;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;

@NoRepositoryBean
public interface Repository<T extends HasId<ID>, ID extends Serializable> {
    TableDescriptor getTableDescriptor();

    T findById(final ID id);

    void deleteById(final ID id);

    boolean existsById(final ID id);

    <S extends T> S insert(S entity);

    <S extends T> S update(S entity);

    Collection<T> findAll();

    Collection<T> findAll(Collection<? extends Condition> conditions);

    Collection<T> findAll(Condition...conditions);
}
