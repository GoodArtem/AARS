package org.gudartem.aars.api.repository;

import org.gudartem.aars.db.model.HasId;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.jooq.Condition;
import org.jooq.SortField;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@NoRepositoryBean
public interface Repository<T extends HasId<ID>, ID extends Serializable> {
    TableDescriptor getTableDescriptor();

    T findById(final ID id);

    T findById(final ID id, Collection<String> fetchPlan);

    void deleteById(final ID id);

    boolean existsById(final ID id);

    <S extends T> S insert(S entity);

    <S extends T> S update(S entity);

    List<T> findAll();

    List<T> findAll(Collection<? extends Condition> conditions);

    List<T> findAll(SearchRequestParams requestParams);

    List<T> findAll(Condition...conditions);

    List<T> findAll(SortField orderBy, Condition...conditions);
}