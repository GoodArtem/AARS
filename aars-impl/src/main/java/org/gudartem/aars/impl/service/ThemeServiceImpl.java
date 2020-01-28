package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.db.model.entity.Directory;
import org.gudartem.aars.db.model.entity.Theme;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.THEME_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.DIRECTORY_SERVICE;
import static org.gudartem.aars.api.service.ServiceName.THEME_SERVICE;

@Service(THEME_SERVICE)
public class ThemeServiceImpl extends CRUDServiceUUIDImpl<Theme> {

    private Repository repository;

    CRUDService<Directory, UUID> directoryService;

    public ThemeServiceImpl(@Qualifier(THEME_REPOSITORY) Repository repository,
                            @Qualifier(DIRECTORY_SERVICE) CRUDService<Directory, UUID> directoryService) {
        this.repository = repository;
        this.directoryService = directoryService;
    }

    @Override
    protected Repository<Theme, UUID> getRepository() {
        return repository;
    }

    @Override
    @Transactional
    public Theme create(Theme theme) {
        Theme result = super.create(theme);
        Directory kd = new Directory();
        kd.setDirectoryName("КД");
        kd.setDirectoryType(1);
        kd.setThemeId(result.getId());
        directoryService.create(kd);
        Directory td = new Directory();
        td.setDirectoryName("ТД");
        td.setDirectoryType(2);
        td.setThemeId(result.getId());
        directoryService.create(td);

        return result;
    }
}
