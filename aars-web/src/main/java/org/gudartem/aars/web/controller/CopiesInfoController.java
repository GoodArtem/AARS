package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.gudartem.aars.model.dto.CopiesInfoDto;
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
@RequestMapping("/copiesInfo")
public class CopiesInfoController {
    @Autowired
    private HasInventoryCardIdService<CopiesInfo, UUID> service;

    @Autowired
    private EntityMapper<CopiesInfo, CopiesInfoDto> mapper;

    @GetMapping("{id}")
    public CopiesInfoDto getById(@PathVariable UUID id,
                                    @RequestBody(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @PostMapping("/create")
    public CopiesInfoDto create(@RequestBody CopiesInfoDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public CopiesInfoDto update(@RequestBody CopiesInfoDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/getByCondition")
    public Collection<CopiesInfoDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }

    @GetMapping("/getByInventoryCard/{inventoryCardId}")
    public Collection<CopiesInfoDto> getAllByDirectory(@PathVariable UUID inventoryCardId) {
        return mapper.toCollectionDto(service.getAllByInvCardId(inventoryCardId));
    }
}
