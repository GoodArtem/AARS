package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.repository.HasInventoryCardIdRepository;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.STOCKTAKING_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.STOCKTAKING_SERVICE;

@Service(STOCKTAKING_SERVICE)
public class StocktakingServiceImpl
        extends CRUDServiceUUIDImpl<Stocktaking>
        implements HasInventoryCardIdService<Stocktaking, UUID> {

    private final static String NEW_STOCKTAKING = "Нов.";
    private final static String DASH = "-";
    private HasInventoryCardIdRepository repository;

    public StocktakingServiceImpl(@Qualifier(STOCKTAKING_REPOSITORY) HasInventoryCardIdRepository repository) {
        this.repository = repository;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Stocktaking> getAllByInvCardId(UUID inventoryCardId) {
        List<Stocktaking> result = repository.getAllByInvCardId(inventoryCardId);
        return postOperationEnrich(result);
    }

    @Override
    @Transactional
    protected List<Stocktaking> postOperationEnrich(List<Stocktaking> stocktakingList) {
        if (stocktakingList != null && !stocktakingList.isEmpty()) {
            Stocktaking firstStocktaking = stocktakingList.get(0);
            int gap = 1;
            if (NEW_STOCKTAKING.equalsIgnoreCase(firstStocktaking.getChangedSheets())) {
                gap = 0;
                firstStocktaking.setChanging(DASH);
            } else {
                firstStocktaking.setChanging(String.valueOf(1));
            }
            for (int i = 1; i < stocktakingList.size(); i++) {
                stocktakingList.get(i).setChanging(String.valueOf(i + gap));
            }
        }
        return super.postOperationEnrich(stocktakingList);
    }

    @Override
    protected Repository<Stocktaking, UUID> getRepository() {
        return repository;
    }
}
