package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.HasDirectoryIdService;
import org.gudartem.aars.db.model.entity.Directory;
import org.gudartem.aars.model.dto.DirectoryDto;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
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
@RequestMapping("/directory")
public class DirectoryController {
    @Autowired
    private HasDirectoryIdService<Directory, UUID> service;

    @Autowired
    private EntityMapper<Directory, DirectoryDto> mapper;

    @GetMapping("{id}")
    public DirectoryDto getById(@PathVariable UUID id,
                                 @RequestBody(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @PostMapping("/create")
    public DirectoryDto create(@RequestBody DirectoryDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public DirectoryDto update(@RequestBody DirectoryDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/getByCondition")
    public Collection<DirectoryDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }

    @GetMapping("/getByTheme/{themeId}")
    public Collection<DirectoryDto> getAllByTheme(@PathVariable UUID themeId) {
        return mapper.toCollectionDto(service.getAllByThemeId(themeId));
    }

    @GetMapping("/getByDirectory/{directoryId}")
    public Collection<DirectoryDto> getAllByDirectory(@PathVariable UUID directoryId) {
        return mapper.toCollectionDto(service.getAllByDirectoryId(directoryId));
    }
}
