package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasDirectoryIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.DirectoryService;
import org.gudartem.aars.api.service.HasDirectoryIdService;
import org.gudartem.aars.db.model.entity.Directory;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.DIRECTORY_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.DIRECTORY_SERVICE;

@Service(DIRECTORY_SERVICE)
public class DirectoryServiceImpl
        extends CRUDServiceUUIDImpl<Directory>
        implements DirectoryService<Directory, UUID> {

    private HasDirectoryIdRepository repository;

    public DirectoryServiceImpl(@Qualifier(DIRECTORY_REPOSITORY) HasDirectoryIdRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Directory> getAllByThemeId(UUID themeId) {
        return repository.getAllByThemeId(themeId);
    }

    @Override
    protected Repository<Directory, UUID> getRepository() {
        return repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Directory> getAllByDirectoryId(UUID directoryId) {
        return repository.getAllByDirectoryId(directoryId);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Directory> getPathToTheme(UUID directoryId) {
        List<Directory> path = new ArrayList<>();
        Directory current = getById(directoryId);
        path.add(current);
        while (current.getParentId() != null) {
            current = getById(current.getParentId());
            path.add(current);
        }
        return path;
    }
}
