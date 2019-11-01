package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasThemeIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasThemeIdService;
import org.gudartem.aars.db.model.entity.Directory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.DIRECTORY_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.DIRECTORY_SERVICE;

@Service(DIRECTORY_SERVICE)
public class DirectoryServiceImpl
        extends CRUDServiceImpl<Directory, UUID>
        implements HasThemeIdService<Directory, UUID> {

    private HasThemeIdRepository repository;

    public DirectoryServiceImpl(@Qualifier(DIRECTORY_REPOSITORY) HasThemeIdRepository repository) {
        this.repository = repository;
    }

    @Override
    public Collection<Directory> getAllByThemeId(UUID themeId) {
        return repository.getAllByThemeId(themeId);
    }

    @Override
    protected Repository<Directory, UUID> getRepository() {
        return repository;
    }
}
