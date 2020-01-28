package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.HasThemeIdService;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.gudartem.aars.model.dto.SubscriberDto;
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
@RequestMapping("/subscriber")
public class SubscriberController {
    @Autowired
    private HasThemeIdService<Subscriber, UUID> service;

    @Autowired
    private EntityMapper<Subscriber, SubscriberDto> mapper;

    @GetMapping("{id}")
    public SubscriberDto getById(@PathVariable UUID id,
                                  @RequestBody(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @PostMapping("/create")
    public SubscriberDto create(@RequestBody SubscriberDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public SubscriberDto update(@RequestBody SubscriberDto requestParams) {
        return mapper.toDto(service.patch(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/delete")
    public void delete(@RequestBody SubscriberDto deletingEntity) {
        service.delete(deletingEntity.getId());
    }

    @PostMapping("/getByCondition")
    public Collection<SubscriberDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }
}
