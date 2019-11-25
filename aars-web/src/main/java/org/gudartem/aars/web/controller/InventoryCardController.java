package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.service.HasDirectoryIdService;
import org.gudartem.aars.api.service.InventoryCardPdfService;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.model.dto.InventoryCardDto;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.UUID;

@CrossOrigin(origins = "*",
        allowedHeaders = "*",
        methods = {RequestMethod.GET, RequestMethod.DELETE, RequestMethod.POST},
        maxAge = 1800)
@RestController
@RequestMapping("/inventoryCard")
public class InventoryCardController {

    @Autowired
    private HasDirectoryIdService<InventoryCard, UUID> service;

    @Autowired
    private EntityMapper<InventoryCard, InventoryCardDto> mapper;

    @Autowired
    private InventoryCardPdfService pdfService;

    @GetMapping("{id}")
    public InventoryCardDto getById(@PathVariable UUID id,
                                    @RequestBody(required = false) Collection<String> fetchPlan) {
        return fetchPlan == null ? mapper.toDto(service.getById(id)) : mapper.toDto(service.getById(id, fetchPlan));
    }

    @PostMapping("/create")
    public InventoryCardDto create(@RequestBody InventoryCardDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/update")
    public InventoryCardDto update(@RequestBody InventoryCardDto requestParams) {
        return mapper.toDto(service.create(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/getByCondition")
    public Collection<InventoryCardDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }

    @GetMapping("/getByTheme/{themeId}")
    public Collection<InventoryCardDto> getAllByTheme(@PathVariable UUID themeId) {
        return mapper.toCollectionDto(service.getAllByThemeId(themeId));
    }

    @GetMapping("/getByDirectory/{directoryId}")
    public Collection<InventoryCardDto> getAllByDirectory(@PathVariable UUID directoryId) {
        return mapper.toCollectionDto(service.getAllByDirectoryId(directoryId));
    }

    @GetMapping("/downloadPdf/{id}")
    public ResponseEntity<Resource> getPdf(@PathVariable UUID id) {
        try {
            File file = pdfService.getPdfFile(id);
            InputStreamResource isr = new InputStreamResource(new FileInputStream(file));
            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment;filename=" + file.getName())
                    .contentLength(file.length())
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(isr);
        } catch (FileNotFoundException ex) {
            // ToDo: create exception
            throw new RuntimeException("File not found");
        }
    }
}
