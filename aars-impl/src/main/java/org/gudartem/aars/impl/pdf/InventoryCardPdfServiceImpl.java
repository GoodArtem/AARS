package org.gudartem.aars.impl.pdf;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Font;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.gudartem.aars.api.service.HasInventoryCardIdService;
import org.gudartem.aars.api.service.HasThemeIdService;
import org.gudartem.aars.api.service.InventoryCardPdfService;
import org.gudartem.aars.db.model.entity.Applicability;
import org.gudartem.aars.db.model.entity.CopiesInfo;
import org.gudartem.aars.db.model.entity.Format;
import org.gudartem.aars.db.model.entity.InventoryCard;
import org.gudartem.aars.db.model.entity.OnceOnlyIssue;
import org.gudartem.aars.db.model.entity.Stocktaking;
import org.gudartem.aars.db.model.entity.Subscriber;
import org.gudartem.aars.impl.pdf.builder.PdfPCellBuilder;
import org.gudartem.aars.impl.pdf.builder.PdfPTableBuilder;
import org.gudartem.aars.impl.pdf.util.PdfUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static com.itextpdf.text.Element.ALIGN_BOTTOM;
import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_TOP;
import static com.itextpdf.text.Rectangle.BOTTOM;
import static com.itextpdf.text.Rectangle.LEFT;
import static com.itextpdf.text.Rectangle.RIGHT;
import static com.itextpdf.text.Rectangle.TOP;

@Service
public class InventoryCardPdfServiceImpl implements InventoryCardPdfService {

    private HasThemeIdService<InventoryCard, UUID> invCardService;

    private HasInventoryCardIdService<CopiesInfo, UUID> copiesInfoService;

    private HasInventoryCardIdService<Applicability, UUID> applicabilityService;

    private HasInventoryCardIdService<Stocktaking, UUID> stocktakingService;

    private HasThemeIdService<Subscriber, UUID> subscriberService;

    private HasInventoryCardIdService<OnceOnlyIssue, UUID> onceOnlyIssueService;

    private static Font HEADER_FONT = PdfUtil.getInstance().getHeaderFont();

    private static Font SMALL_FONT = PdfUtil.getInstance().getSmallFont();

    public InventoryCardPdfServiceImpl(@Autowired HasThemeIdService<InventoryCard, UUID> invCardService,
                                       @Autowired HasInventoryCardIdService<CopiesInfo, UUID> copiesInfoService,
                                       @Autowired HasInventoryCardIdService<Applicability, UUID> applicabilityService,
                                       @Autowired HasInventoryCardIdService<Stocktaking, UUID> stocktakingService,
                                       @Autowired HasThemeIdService<Subscriber, UUID> subscriberService,
                                       @Autowired HasInventoryCardIdService<OnceOnlyIssue, UUID> onceOnlyIssueService) {
        this.invCardService = invCardService;
        this.copiesInfoService = copiesInfoService;
        this.applicabilityService = applicabilityService;
        this.stocktakingService = stocktakingService;
        this.subscriberService = subscriberService;
        this.onceOnlyIssueService = onceOnlyIssueService;


    }

    public File getPdfFile(UUID inventoryCarId) {
        InventoryCard inventoryCard = invCardService.getById(inventoryCarId);

        File pdfFile = null;
        try {
            pdfFile = File.createTempFile(inventoryCard.getCardName(), ".pdf");
            Document document = new Document(PageSize.A4, 0f, 0f, 28.4f, 28.4f);
            PdfWriter.getInstance(document, new FileOutputStream(pdfFile));
            document.open();
            fillContent(document, inventoryCard);
            document.close();
            pdfFile.deleteOnExit();
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }

        return pdfFile;
    }

    private void fillContent(Document document, InventoryCard inventoryCard) throws DocumentException {
        document.add(getFrontendTable(inventoryCard));
        document.newPage();
        document.add(getBackendTable(inventoryCard));
    }

    private PdfPTable getFrontendTable(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(25f, 20f, 30f, 30f, 35f, 16f, 34f, 20f)
                .setHorizontalAlignment(ALIGN_CENTER)
                .setLockedWidth(true)
                .setTotalWidth(210f)
                .build();
        result.addCell(PdfPCellBuilder.of("Карточка учета документов", HEADER_FONT).setColSpan(8)
                .setVerticalAlignment(ALIGN_BOTTOM).disableBorderSide(LEFT | TOP | RIGHT).build());
        result.addCell(PdfPCellBuilder.of(inventoryCard.getCipher()).setRowSpan(2).build());
        result.addCell(PdfPCellBuilder.of("Лист").build());
        result.addCell(PdfPCellBuilder.of("(Наименование)").setColSpan(3).disableBorderSide(BOTTOM).build());
        result.addCell(PdfPCellBuilder.of("(Обозначение)").setColSpan(3).disableBorderSide(BOTTOM).build());
        result.addCell(PdfPCellBuilder.of("Продолж. на листе").setVerticalAlignment(ALIGN_TOP).build());
        result.addCell(PdfPCellBuilder.of(inventoryCard.getCardName()).setColSpan(3).disableBorderSide(TOP).build());
        result.addCell(PdfPCellBuilder.of(inventoryCard.getDesignation()).setColSpan(3).disableBorderSide(TOP).build());
        result.addCell(PdfPCellBuilder.of("Подразд.").build());
        result.addCell(PdfPCellBuilder.emptyCell().build());
        result.addCell(PdfPCellBuilder.of("Подл. на предпр.").build());
        result.addCell(PdfPCellBuilder.emptyCell().build());
        result.addCell(PdfPCellBuilder.emptyCell().build());
        result.addCell(PdfPCellBuilder.of("Формат").build());
        result.addCell(PdfPCellBuilder.of(getFormatNames(inventoryCard)).setColSpan(2).build());
        result.addCell(PdfPCellBuilder.of("Вид документа").build());
        result.addCell(PdfPCellBuilder.of(getCardTypeName(inventoryCard)).build());
        result.addCell(PdfPCellBuilder.of("Инв. №").build());
        result.addCell(PdfPCellBuilder.of(getFullNumber(inventoryCard)).build());
        result.addCell(PdfPCellBuilder.of("Дата поступл.").setHorizontalAlignment(LEFT).build());
        result.addCell(PdfPCellBuilder.of(inventoryCard.getCardDate()).build());
        result.addCell(PdfPCellBuilder.of("Кол. листов").build());
        result.addCell(PdfPCellBuilder.of(inventoryCard.getSheetCount()).build());

        result.addCell(PdfPCellBuilder.of(getCopiesInfoTable(inventoryCard)).setColSpan(2).setPadding(0f).build());
        result.addCell(PdfPCellBuilder.of(getApplicabilityAndStocktakingTables(inventoryCard))
                .setColSpan(6).setPadding(0f).build());
        result.addCell(PdfPCellBuilder.of(getBottomTable(inventoryCard)).setColSpan(8).setPadding(0f).build());

        result.getRow(0).setFinalMaxHeights(PdfUtil.calcPoint(10f));
        result.getRow(1).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        result.getRow(2).setFinalMaxHeights(PdfUtil.calcPoint(13f));
        result.getRow(3).setFinalMaxHeights(PdfUtil.calcPoint(7f));
        result.getRow(4).setFinalMaxHeights(PdfUtil.calcPoint(7f));

        return result;
    }

    private PdfPTable getCopiesInfoTable(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(10f, 15f, 10f, 10f).setLockedWidth(true).setTotalWidth(45f).build();

        result.addCell(PdfPCellBuilder.of("Учет копий").setColSpan(4).build());
        result.addCell(PdfPCellBuilder.of("Дата").setRowSpan(2).build());
        result.addCell(PdfPCellBuilder.of("Основание").setRowSpan(2).build());
        result.addCell(PdfPCellBuilder.of("Кол. (№ экз.)").setColSpan(2).build());
        result.addCell(PdfPCellBuilder.of("посту-\nпило").build());
        result.addCell(PdfPCellBuilder.of("спи-\nсано").build());

        List<CopiesInfo> copiesInfoCollection = copiesInfoService.getAllByInvCardId(inventoryCard.getId());
        int i = 0;
        if (copiesInfoCollection != null && !copiesInfoCollection.isEmpty()) {
            for (i = 0; i < copiesInfoCollection.size(); i++) {
                if (i == 17) {
                    break;
                }
                CopiesInfo copiesInfo = copiesInfoCollection.get(i);
                result.addCell(PdfPCellBuilder.of(copiesInfo.getCopyDate(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(copiesInfo.getDesignation(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(copiesInfo.getReceivedCopy(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(copiesInfo.getAnnulledCopy(), SMALL_FONT).build());
            }
        }
        for (int j = i; j < 17; j++) {
            for (int k = 0; k < 4; k++) {
                result.addCell(PdfPCellBuilder.emptyCell().build());
            }
        }

        result.getRow(0).setFinalMaxHeights(PdfUtil.calcPoint(7f));
        result.getRow(1).setFinalMaxHeights(PdfUtil.calcPoint(4f));
        result.getRow(2).setFinalMaxHeights(PdfUtil.calcPoint(8f));

        for (i = 3; i < 20; i++) {
            result.getRow(i).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        }

        return result;
    }

    private PdfPTable getApplicabilityTable(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(10f, 58f, 12f).setLockedWidth(true).setTotalWidth(80f).build();

        result.addCell(PdfPCellBuilder.of("Применяемость").setColSpan(3).build());
        result.addCell(PdfPCellBuilder.of("Дата").build());
        result.addCell(PdfPCellBuilder.of("Обозначение").build());
        result.addCell(PdfPCellBuilder.of("Шифр").build());

        List<Applicability> applicabilityList = applicabilityService.getAllByInvCardId(inventoryCard.getId());
        int i = 0;
        if (applicabilityList != null && !applicabilityList.isEmpty()) {
            for (i = 0; i < applicabilityList.size(); i++) {
                if (i == 17) {
                    break;
                }
                Applicability applicability = applicabilityList.get(i);
                result.addCell(PdfPCellBuilder.of(applicability.getApplicabilityDate(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(applicability.getDesignation(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(applicability.getCipher(), SMALL_FONT).build());
            }
        }

        for (int j = i; j < 17; j++) {
            for (int k = 0; k < 3; k++) {
                result.addCell(PdfPCellBuilder.emptyCell().build());
            }
        }

        result.getRow(0).setFinalMaxHeights(PdfUtil.calcPoint(7f));
        result.getRow(1).setFinalMaxHeights(PdfUtil.calcPoint(12f) + 0.6f);

        for (i = 2; i < 19; i++) {
            result.getRow(i).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        }

        return result;
    }

    private PdfPTable getStocktakingTable(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(10f, 40f, 10f, 25f).setLockedWidth(true).setTotalWidth(85f).build();

        result.addCell(PdfPCellBuilder.of("Учет").setColSpan(4).build());
        result.addCell(PdfPCellBuilder.of("Изм.").build());
        result.addCell(PdfPCellBuilder.of("№ документа").build());
        result.addCell(PdfPCellBuilder.of("Дата внесения").build());
        result.addCell(PdfPCellBuilder.of("Листы").build());

        List<Stocktaking> stocktakingList = stocktakingService.getAllByInvCardId(inventoryCard.getId());
        int i = 0;
        if (stocktakingList != null && !stocktakingList.isEmpty()) {
            for (i = 0; i < stocktakingList.size(); i++) {
                if (i == 17) {
                    break;
                }
                Stocktaking stocktaking = stocktakingList.get(i);
                result.addCell(PdfPCellBuilder.of(stocktaking.getChanging(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(stocktaking.getDocNumber(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(stocktaking.getDateChanging(), SMALL_FONT).build());
                result.addCell(PdfPCellBuilder.of(stocktaking.getChangedSheets(), SMALL_FONT).build());
            }
        }

        for (int j = i; j < 17; j++) {
            for (int k = 0; k < 4; k++) {
                result.addCell(PdfPCellBuilder.emptyCell().build());
            }
        }

        result.getRow(0).setFinalMaxHeights(PdfUtil.calcPoint(7f));
        result.getRow(1).setFinalMaxHeights(PdfUtil.calcPoint(12f) + 0.6f);

        for (i = 2; i < 19; i++) {
            result.getRow(i).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        }

        return result;
    }

    private PdfPTable getApplicabilityAndStocktakingTables(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(80f, 85f).setLockedWidth(true).setTotalWidth(165f).build();

        result.addCell(PdfPCellBuilder.of(getApplicabilityTable(inventoryCard)).build());
        result.addCell(PdfPCellBuilder.of(getStocktakingTable(inventoryCard)).build());

        return result;
    }

    private PdfPTable getBottomTable(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(45f, 80f, 85f).setLockedWidth(true).setTotalWidth(210f).build();

        result.addCell(PdfPCellBuilder.of("Технологический документ").build());

        UUID tdId = null;
        if (inventoryCard.getMk() != null) {
            tdId = inventoryCard.getMk();
        } else if (inventoryCard.getMkt() != null) {
            tdId = inventoryCard.getMkt();
        } else if (inventoryCard.getOkufreg() != null) {
            tdId = inventoryCard.getOkufreg();
        } else if (inventoryCard.getOkufsb() != null) {
            tdId = inventoryCard.getOkufsb();
        } else if (inventoryCard.getTl() != null) {
            tdId = inventoryCard.getTl();
        } else if (inventoryCard.getVtd() != null) {
            tdId = inventoryCard.getVtd();
        } else if (inventoryCard.getVoptk() != null) {
            tdId = inventoryCard.getVoptk();
        }

        String tdName = "";
        if (tdId != null) {
            InventoryCard tdInventoryCard = invCardService.getById(tdId);
            tdName = tdInventoryCard.getCardName();
        }
        result.addCell(PdfPCellBuilder.of(tdName).build());

        result.addCell(PdfPCellBuilder.of("(Отметки о микрофильмировании,\nвосстановлении, аннулировании)").build());

        result.getRow(0).setFinalMaxHeights(PdfUtil.calcPoint(12f));

        return result;
    }

    private String getFormatNames(InventoryCard inventoryCard) {
        Collection<Format> formats = inventoryCard.getFormatSet();
        if (formats == null || formats.isEmpty()) {
            return "";
        }
        return String.join(",",
                formats.stream().map(format -> format.getFormatName()).collect(Collectors.toList()));
    }

    private String getCardTypeName(InventoryCard inventoryCard) {
        return inventoryCard.getCardType() == 1 ? "КД" : "ТД";
    }

    private String getFullNumber(InventoryCard inventoryCard) {
        String numberSuf = inventoryCard.getInventoryNumberSuf();
        return String.format("%03d%s", inventoryCard.getInventoryNumber(), numberSuf == null ? "" : numberSuf);
    }

    private PdfPTable getBackendTable(InventoryCard inventoryCard) {
        PdfPTable result = PdfPTableBuilder.of(10f, 24f, 16f, 16f, 16f, 16f, 16f, 16f, 16f, 16f, 16f, 16f, 16f)
                .setHorizontalAlignment(ALIGN_CENTER)
                .setLockedWidth(true)
                .setTotalWidth(210f)
                .build();

        result.addCell(PdfPCellBuilder.of("Выдача копий").setColSpan(13).setVerticalAlignment(ALIGN_BOTTOM).build());
        result.addCell(PdfPCellBuilder.of("Учтенные абоненты").setRowSpan(22).setRotation(90).build());
        PdfPCell subscriberHeader = PdfPCellBuilder.of("Абоненты").build();
        PdfPCell dateHeader = PdfPCellBuilder.of("Дата").build();
        PdfPCell exNumberHeader = PdfPCellBuilder.of("Кол. экз.").build();
        PdfPCell designationHeader = PdfPCellBuilder.of("Основание").build();
        PdfPCell annulledHeader = PdfPCellBuilder.of("Списано").build();

        result.addCell(subscriberHeader);

        List<Subscriber> subscriberList = subscriberService.getAllByThemeId(inventoryCard.getThemeId());
        int c = 0;
        boolean subscriberListNotEmpty = subscriberList != null && !subscriberList.isEmpty();
        if (subscriberListNotEmpty) {
            for (c = 0; c < subscriberList.size(); c++) {
                if (c == 11) {
                    break;
                }
                Subscriber subscriber = subscriberList.get(c);
                result.addCell(PdfPCellBuilder.of(subscriber.getSubscriberName()).build());
            }
        }
        for (int i = c; i < 11; i++) {
            result.addCell(PdfPCellBuilder.emptyCell().build());
        }
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 4; j++) {
                c = 0;
                if (j == 0) {
                    result.addCell(dateHeader);
                    if (i == 0 && subscriberListNotEmpty) {
                        for (c = 0; c < subscriberList.size(); c++) {
                            if (c == 11) {
                                break;
                            }
                            Subscriber subscriber = subscriberList.get(c);
                            result.addCell(PdfPCellBuilder.of(subscriber.getSubscribeDate()).build());
                        }
                    }
                } else if (j == 1) {
                    result.addCell(exNumberHeader);
                    if (i == 0 && subscriberListNotEmpty) {
                        for (c = 0; c < subscriberList.size(); c++) {
                            if (c == 11) {
                                break;
                            }
                            Subscriber subscriber = subscriberList.get(c);
                            result.addCell(PdfPCellBuilder.of(subscriber.getExNumber()).build());
                        }
                    }
                } else if (j == 2) {
                    result.addCell(designationHeader);
                    if (i == 0 && subscriberListNotEmpty) {
                        for (c = 0; c < subscriberList.size(); c++) {
                            if (c == 11) {
                                break;
                            }
                            Subscriber subscriber = subscriberList.get(c);
                            result.addCell(PdfPCellBuilder.of(subscriber.getDesignation()).build());
                        }
                    }
                } else if (j == 3) {
                    result.addCell(annulledHeader);
                    if (i == 0 && subscriberListNotEmpty) {
                        for (c = 0; c < subscriberList.size(); c++) {
                            if (c == 11) {
                                break;
                            }
                            Subscriber subscriber = subscriberList.get(c);
                            result.addCell(PdfPCellBuilder.of(subscriber.getAnnulled()).build());
                        }
                    }
                }
                for (int k = c; k < 11; k++) {
                    result.addCell(PdfPCellBuilder.emptyCell().build());
                }
            }
        }
        result.addCell(subscriberHeader);
        for (int i = 0; i < 11; i++) {
            result.addCell(PdfPCellBuilder.emptyCell().build());
        }
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                if (j == 0) {
                    result.addCell(dateHeader);
                } else if (j == 1) {
                    result.addCell(exNumberHeader);
                } else if (j == 2) {
                    result.addCell(designationHeader);
                } else if (j == 3) {
                    result.addCell(annulledHeader);
                }
                for (int k = 0; k < 11; k++) {
                    result.addCell(PdfPCellBuilder.emptyCell().build());
                }
            }
        }

        PdfPCell toWhomHeader = PdfPCellBuilder.of("Кому").build();
        result.addCell(PdfPCellBuilder.of("Разовые\nвыдачи").setRowSpan(4).setRotation(90).build());
        result.addCell(toWhomHeader);

        List<OnceOnlyIssue> onceOnlyIssueList = onceOnlyIssueService.getAllByInvCardId(inventoryCard.getId());
        boolean onceOnlyIssueNotEmpty = onceOnlyIssueList != null && !onceOnlyIssueList.isEmpty();
        c = 0;
        if (onceOnlyIssueNotEmpty) {
            for (c = 0; c < onceOnlyIssueList.size(); c++) {
                if (c == 11) {
                    break;
                }
                OnceOnlyIssue issue = onceOnlyIssueList.get(c);
                result.addCell(PdfPCellBuilder.of(issue.getToWhom()).build());
            }
        }
        for (int i = c; i < 11; i++) {
            result.addCell(PdfPCellBuilder.emptyCell().build());
        }
        for (int j = 0; j < 3; j++) {
            c = 0;
            if (j == 0) {
                result.addCell(dateHeader);
                if (onceOnlyIssueNotEmpty) {
                    for (c = 0; c < onceOnlyIssueList.size(); c++) {
                        if (c == 11) {
                            break;
                        }
                        OnceOnlyIssue issue = onceOnlyIssueList.get(c);
                        result.addCell(PdfPCellBuilder.of(issue.getIssueDate()).build());
                    }
                }
            } else if (j == 1) {
                result.addCell(exNumberHeader);
                if (onceOnlyIssueNotEmpty) {
                    for (c = 0; c < onceOnlyIssueList.size(); c++) {
                        if (c == 11) {
                            break;
                        }
                        OnceOnlyIssue issue = onceOnlyIssueList.get(c);
                        result.addCell(PdfPCellBuilder.of(issue.getExNumber()).build());
                    }
                }
            } else if (j == 2) {
                result.addCell(designationHeader);
                if (onceOnlyIssueNotEmpty) {
                    for (c = 0; c < onceOnlyIssueList.size(); c++) {
                        if (c == 11) {
                            break;
                        }
                        OnceOnlyIssue issue = onceOnlyIssueList.get(c);
                        result.addCell(PdfPCellBuilder.of(issue.getDesignation()).build());
                    }
                }
            }
            for (int k = c; c < 11; c++) {
                result.addCell(PdfPCellBuilder.emptyCell().build());
            }
        }

        result.getRow(0).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        result.getRow(1).setFinalMaxHeights(PdfUtil.calcPoint(10f));
        for (int i = 2; i < 10; i++) {
            result.getRow(i).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        }
        result.getRow(10).setFinalMaxHeights(PdfUtil.calcPoint(10f));
        for (int i = 11; i < 22; i++) {
            result.getRow(i).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        }
        result.getRow(23).setFinalMaxHeights(PdfUtil.calcPoint(8f));
        for (int i = 24; i < 27; i++) {
            result.getRow(i).setFinalMaxHeights(PdfUtil.calcPoint(5f));
        }

        return result;
    }
}
