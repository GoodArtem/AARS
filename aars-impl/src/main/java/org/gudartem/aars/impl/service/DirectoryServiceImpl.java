package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasDirectoryIdRepository;
import org.gudartem.aars.api.repository.HasThemeIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasDirectoryIdService;
import org.gudartem.aars.api.service.HasThemeIdService;
import org.gudartem.aars.db.model.entity.Directory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.DIRECTORY_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.DIRECTORY_SERVICE;

@Service(DIRECTORY_SERVICE)
public class DirectoryServiceImpl
        extends CRUDServiceImpl<Directory, UUID>
        implements HasDirectoryIdService<Directory, UUID> {

    private HasDirectoryIdRepository repository;

    public DirectoryServiceImpl(@Qualifier(DIRECTORY_REPOSITORY) HasDirectoryIdRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Directory> getAllByThemeId(UUID themeId) {
        return repository.getAllByThemeId(themeId);
    }

    @Override
    protected Repository<Directory, UUID> getRepository() {
        return repository;
    }

    @Override
    public List<Directory> getAllByDirectoryId(UUID directoryId) {
        return repository.getAllByDirectoryId(directoryId);
    }
}
