package org.gudartem.aars.api.service;

import java.io.File;
import java.util.UUID;

public interface InventoryCardPdfService {
    File getPdfFile(UUID inventoryCarId);
}
