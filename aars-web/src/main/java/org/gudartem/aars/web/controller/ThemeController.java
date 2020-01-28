package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.CRUDService;
import org.gudartem.aars.db.model.entity.Theme;
import org.gudartem.aars.model.dto.ThemeDto;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST},
        maxAge = 1800)
@RestController
@RequestMapping("/theme")
public class ThemeController {
    @Autowired
    private CRUDService<Theme, UUID> service;

    @Autowired
    private EntityMapper<Theme, ThemeDto> mapper;

    @GetMapping("{id}")
    public ThemeDto getById(@PathVariable UUID id,
                                 @RequestParam(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @GetMapping("/all")
    public Collection<ThemeDto> getAll() {
        return mapper.toCollectionDto(service.getAll());
    }

    @PostMapping("/create")
    public ThemeDto create(@RequestBody ThemeDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public ThemeDto update(@RequestBody ThemeDto requestParams) {
        return mapper.toDto(service.patch(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/delete")
    public void delete(@RequestBody ThemeDto deletingEntity) {
        service.delete(deletingEntity.getId());
    }

    @PostMapping("/getByCondition")
    public Collection<ThemeDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }
}
