package org.gudartem.aars.web.controller;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.api.model.TreeWithOpenedBranchResult;
import org.gudartem.aars.api.service.InventoryCardPdfService;
import org.gudartem.aars.api.service.InventoryCardService;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.db.model.entity.Theme;
import org.gudartem.aars.model.abstraction.BaseDto;
import org.gudartem.aars.model.dto.InventoryCardDto;
import org.gudartem.aars.model.dto.ThemeDto;
import org.gudartem.aars.model.request.SearchRequestParams;
import org.gudartem.aars.web.model.TreeWithOpenedBranchResponse;
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
    private InventoryCardService service;

    @Autowired
    private EntityMapper<InventoryCard, InventoryCardDto> mapper;

    @Autowired
    private InventoryCardPdfService pdfService;

    @Autowired
    private EntityMapper<Theme, ThemeDto> themeMapper;

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
        return mapper.toDto(service.patch(mapper.toDomainObject(requestParams)));
    }

    @PostMapping("/delete")
    public void delete(@RequestBody InventoryCardDto deletingEntity) {
        service.delete(deletingEntity.getId());
    }

    @PostMapping("/getByCondition")
    public Collection<InventoryCardDto> getByCondition(@RequestBody SearchRequestParams requestParams) {
        return mapper.toCollectionDto(service.getByCondition(requestParams));
    }

    @GetMapping("/getByTheme/{themeId}")
    public Collection<InventoryCardDto> getAllByTheme(@PathVariable UUID themeId) {
        return mapper.toCollectionDto(service.getAllByThemeId(themeId));
    }

    @GetMapping("/getAllByThemeIdWithLastChange/{themeId}")
    public Collection<InventoryCardDto> getAllByThemeIdWithLastChange(@PathVariable UUID themeId,
                                                                      @RequestParam Integer cardType) {
        return mapper.toCollectionDto(service.getAllByThemeIdWithLastChange(themeId, cardType));
    }

    @GetMapping("/getByDirectory/{directoryId}")
    public Collection<InventoryCardDto> getAllByDirectory(@PathVariable UUID directoryId) {
        return mapper.toCollectionDto(service.getAllByDirectoryId(directoryId));
    }

    @GetMapping("/getAllByDirectoryIdWithLastChange/{directoryId}")
    public Collection<InventoryCardDto> getAllByDirectoryIdWithLastChange(@PathVariable UUID directoryId) {
        return mapper.toCollectionDto(service.getAllByDirectoryIdWithLastChange(directoryId));
    }

    @GetMapping("/getNextSequenceInventoryNumber")
    public Integer getNextSequenceInventoryNumber(@RequestParam UUID parentDirectoryId) {
        return service.getNextSequenceInventoryNumber(parentDirectoryId);
    }

    @GetMapping("/getExistsInventoryCard")
    public Boolean getExistsInventoryCard(@RequestParam(required = false) UUID id,
                                          @RequestParam Integer inventoryNumber,
                                          @RequestParam(required = false) String inventoryNumberSuf,
                                          @RequestParam Integer cardType) {
        return service.getExistsInventoryCard(id, inventoryNumber, inventoryNumberSuf, cardType);
    }


    @GetMapping("/getPathToInventoryCard")
    public Collection<UUID> getPathToInventoryCard(@RequestParam UUID id) {
        return service.getPathToInventoryCard(id);
    }

    @GetMapping("/getTreeWithOpenedBranch")
    public TreeWithOpenedBranchResponse getTreeWithOpenedBranch(@RequestParam UUID id) {
        TreeWithOpenedBranchResult result = service.getTreeWithOpenedBranch(id);
        TreeWithOpenedBranchResponse response = new TreeWithOpenedBranchResponse();
        response.setTree(themeMapper.toCollectionDto(result.getTree()));
        for (UUID openId : result.getOpen()) {
            response.getOpen().add(new BaseDto<>(openId));
        }
        response.getActive().add(new BaseDto<>(result.getActive()));
        return response;
    }

    @GetMapping("/getInventoryCardBySearchString")
    public Collection<InventoryCardDto> getInventoryCardBySearchString(@RequestParam String searchString) {
        return mapper.toCollectionDto(service.getInventoryCardBySearchString(searchString));
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
            throw new RuntimeException("File not found");
        }
    }
}
