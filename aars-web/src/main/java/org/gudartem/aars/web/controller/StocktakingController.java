package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.model.dto.StocktakingDto;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST},
        maxAge = 1800)
@RestController
@RequestMapping("/stocktaking")
public class StocktakingController {
    @Autowired
    private HasInventoryCardIdService<Stocktaking, UUID> service;

    @Autowired
    private EntityMapper<Stocktaking, StocktakingDto> mapper;

    @GetMapping("{id}")
    public StocktakingDto getById(@PathVariable UUID id,
                                    @RequestBody(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @PostMapping("/create")
    public StocktakingDto create(@RequestBody StocktakingDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public StocktakingDto update(@RequestBody StocktakingDto requestParams) {
        return mapper.toDto(service.patch(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/delete")
    public void delete(@RequestBody StocktakingDto deletingEntity) {
        service.delete(deletingEntity.getId());
    }

    @PostMapping("/getByCondition")
    public Collection<StocktakingDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }

    @GetMapping("/getByInventoryCard/{inventoryCardId}")
    public Collection<StocktakingDto> getAllByDirectory(@PathVariable UUID inventoryCardId) {
        return mapper.toCollectionDto(service.getAllByInvCardId(inventoryCardId));
    }
}
