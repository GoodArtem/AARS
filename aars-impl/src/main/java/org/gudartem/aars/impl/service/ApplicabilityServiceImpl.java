package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.api.service.InventoryCardService;
import org.gudartem.aars.db.model.entity.Applicability;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.APPLICABILITY_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.APPLICABILITY_SERVICE;
import static org.gudartem.aars.db.constants.ColumnName.InventoryCard.DESIGNATION;
import static org.gudartem.aars.model.PojoFieldNames.HasId.ID;
import static org.gudartem.aars.model.PojoFieldNames.InventoryCard.CIPHER;

@Service(APPLICABILITY_SERVICE)
public class ApplicabilityServiceImpl
        extends CRUDServiceUUIDImpl<Applicability>
        implements HasInventoryCardIdService<Applicability, UUID> {

    private HasInventoryCardIdRepository repository;
    private InventoryCardService inventoryCardService;
    private final static Set<String> INVENTORY_CARD_FETCH_PLAN = new HashSet<>();

    public ApplicabilityServiceImpl(@Qualifier(APPLICABILITY_REPOSITORY) HasInventoryCardIdRepository repository,
                                    InventoryCardService inventoryCardService) {
        this.repository = repository;
        this.inventoryCardService = inventoryCardService;
        INVENTORY_CARD_FETCH_PLAN.add(ID);
        INVENTORY_CARD_FETCH_PLAN.add(DESIGNATION);
        INVENTORY_CARD_FETCH_PLAN.add(CIPHER);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Applicability> getAllByInvCardId(UUID inventoryCardId) {
        List<Applicability> result = repository.getAllByInvCardId(inventoryCardId);
        return this.postOperationEnrich(result);
    }

    @Override
    @Transactional
    protected Applicability postOperationEnrich(Applicability entityToEnrich) {
        InventoryCard appInventoryCard =
                inventoryCardService.getById(entityToEnrich.getAppInventoryCardId(), INVENTORY_CARD_FETCH_PLAN);
        entityToEnrich.setDesignation(appInventoryCard.getDesignation());
        entityToEnrich.setCipher(appInventoryCard.getCipher());
        return super.postOperationEnrich(entityToEnrich);
    }

    @Override
    protected Repository<Applicability, UUID> getRepository() {
        return repository;
    }
}
