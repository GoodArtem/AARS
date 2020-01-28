package org.gudartem.aars.impl.service.internal;

import org.gudartem.aars.db.model.entity.Format;
import org.gudartem.aars.impl.repository.internal.CardToFormatRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Set;
import java.util.UUID;

import static org.gudartem.aars.api.repository.RepositoryName.CARD_TO_FORMAT_REPOSITORY;
import static org.gudartem.aars.api.service.ServiceName.CARD_TO_FORMAT_SERVICE;

@Service(CARD_TO_FORMAT_SERVICE)
public class CardToFormatService {

    private CardToFormatRepository repository;

    public CardToFormatService(@Qualifier(CARD_TO_FORMAT_REPOSITORY) CardToFormatRepository repository) {
        this.repository = repository;
    }

    @Transactional(readOnly = true)
    public Set<Format> getFormatSet(UUID inventoryCardId) {
        return repository.getFormatSet(inventoryCardId);
    }

    @Transactional
    public void updateFormatSet(UUID inventoryCardId, Set<Format> formatSet) {
        repository.updateRelations(inventoryCardId, formatSet);
    }
}
