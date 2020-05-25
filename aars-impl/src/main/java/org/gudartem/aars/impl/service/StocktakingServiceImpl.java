package org.gudartem.aars.impl.service;

import org.gudartem.aars.api.error.model.ErrorCode;
import org.gudartem.aars.api.error.utils.ErrorUtils;
import org.gudartem.aars.api.repository.Repository;
import org.gudartem.aars.api.repository.StocktakingRepository;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.impl.service.abstraction.CRUDServiceUUIDImpl;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static org.gudartem.aars.api.constants.BusinessConstants.NEW_STOCKTAKING;
import static org.gudartem.aars.api.repository.RepositoryName.STOCKTAKING_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.STOCKTAKING_SERVICE;

@Service(STOCKTAKING_SERVICE)
public class StocktakingServiceImpl
        extends CRUDServiceUUIDImpl<Stocktaking>
        implements HasInventoryCardIdService<Stocktaking, UUID> {

    private final static String DASH = "-";
    private StocktakingRepository repository;

    public StocktakingServiceImpl(@Qualifier(STOCKTAKING_REPOSITORY) StocktakingRepository repository) {
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
    @Transactional
    protected Stocktaking preCreate(Stocktaking creatingEntity) {
        validate(creatingEntity);
        return super.preCreate(creatingEntity);
    }

    @Override
    @Transactional
    protected Stocktaking preUpdate(Stocktaking updatingEntity) {
        validate(updatingEntity);
        return super.preUpdate(updatingEntity);
    }

    @Transactional
    private void validate(Stocktaking creatingOrUpdatingEntity) {
        if (NEW_STOCKTAKING.equalsIgnoreCase(creatingOrUpdatingEntity.getChangedSheets())) {
            if (repository.newStocktakingAlreadyExists(creatingOrUpdatingEntity.getId(),
                    creatingOrUpdatingEntity.getInventoryCardId())) {
                throw ErrorUtils.getUnprocessableEntityException(ErrorCode.AARS_0003);
            }
            if (repository.newStocktakingHasIncorrectDate(creatingOrUpdatingEntity.getId(),
                    creatingOrUpdatingEntity.getInventoryCardId(), creatingOrUpdatingEntity.getDateChanging())) {
                throw ErrorUtils.getUnprocessableEntityException(ErrorCode.AARS_0004);
            }

            creatingOrUpdatingEntity.setChangedSheets(NEW_STOCKTAKING);
        } else {
            if (repository.stocktakingHasIncorrectDate(creatingOrUpdatingEntity.getId(),
                    creatingOrUpdatingEntity.getInventoryCardId(), creatingOrUpdatingEntity.getDateChanging())) {
                throw ErrorUtils.getUnprocessableEntityException(ErrorCode.AARS_0005);
            }
        }
    }

    @Override
    protected Repository<Stocktaking, UUID> getRepository() {
        return repository;
    }
}
