package org.gudartem.aars.impl.mapper;

import org.gudartem.aars.api.mapper.EntityMapper;
import org.gudartem.aars.db.model.entity.Directory;
import org.gudartem.aars.model.dto.DirectoryDto;
import org.mapstruct.AfterMapping;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.UUID;

@Mapper(componentModel = "spring", imports = UUID.class, uses = InventoryCardMapper.class)
public abstract class DirectoryMapper implements EntityMapper<Directory, DirectoryDto> {

    @Autowired
    private InventoryCardMapper inventoryCardMapper;

    @AfterMapping
    public void afterMapping(Directory source, @MappingTarget DirectoryDto target) {
        if (source == null) {
            return;
        }
        target.setChildren(new ArrayList<>());
        if (source.getChildDirectoryList() != null) {
            target.getChildren().addAll(toCollectionDto(source.getChildDirectoryList()));
        }
        if (source.getChildInventoryCardList() != null) {
            target.getChildren().addAll(inventoryCardMapper.toCollectionDto(source.getChildInventoryCardList()));
        }
    }
}
