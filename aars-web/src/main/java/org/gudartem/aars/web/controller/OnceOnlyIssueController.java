package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.gudartem.aars.model.dto.OnceOnlyIssueDto;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@RestController
@RequestMapping("/onceOnlyIssue")
public class OnceOnlyIssueController {
    @Autowired
    private HasInventoryCardIdService<OnceOnlyIssue, UUID> service;

    @Autowired
    private EntityMapper<OnceOnlyIssue, OnceOnlyIssueDto> mapper;

    @GetMapping("{id}")
    public OnceOnlyIssueDto getById(@PathVariable UUID id,
                             @RequestBody(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @PostMapping("/create")
    public OnceOnlyIssueDto create(@RequestBody OnceOnlyIssueDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public OnceOnlyIssueDto update(@RequestBody OnceOnlyIssueDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/getByCondition")
    public Collection<OnceOnlyIssueDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }
}
