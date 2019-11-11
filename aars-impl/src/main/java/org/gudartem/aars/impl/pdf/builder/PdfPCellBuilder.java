package org.gudartem.aars.impl.pdf.builder;

import com.itextpdf.text.Font;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import org.gudartem.aars.impl.pdf.util.PdfUtil;

import java.time.OffsetDateTime;

import static com.itextpdf.text.Element.ALIGN_CENTER;
import static com.itextpdf.text.Element.ALIGN_MIDDLE;
import static org.gudartem.aars.impl.pdf.util.PdfUtil.RUS_DATE_FORMAT;

public class PdfPCellBuilder {

    private static Font COMMON_FONT = PdfUtil.getInstance().getCommonFont();

    private PdfPCell cell;

    private PdfPCellBuilder(PdfPCell cell) {
        this.cell = cell;
    }

    public static PdfPCellBuilder emptyCell() {
        return new PdfPCellBuilder(setCenterAlignment(new PdfPCell()));
    }

    public static PdfPCellBuilder of(String str) {
        return of(str, null);
    }

    public static PdfPCellBuilder of(OffsetDateTime date) {
        return of(date, null);
    }

    public static PdfPCellBuilder of(OffsetDateTime date, Font font) {
        return of(date.format(RUS_DATE_FORMAT), font);
    }

    public static PdfPCellBuilder of(Integer integer) {
        return of(integer, null);
    }

    public static PdfPCellBuilder of(Integer integer, Font font) {
        return of(integer == null ? "" : integer.toString(), font);
    }

    public static PdfPCellBuilder of(String str, Font font) {
        return new PdfPCellBuilder(setCenterAlignment(new PdfPCell(new Phrase(str, font == null ? COMMON_FONT : font))));
    }

    public static PdfPCellBuilder of(PdfPTable table) {
        return new PdfPCellBuilder(new PdfPCell(table));
    }

    private static PdfPCell setCenterAlignment(PdfPCell cell) {
        cell.setHorizontalAlignment(ALIGN_CENTER);
        cell.setVerticalAlignment(ALIGN_MIDDLE);
        return cell;
    }

    public PdfPCellBuilder setHorizontalAlignment(int horizontalAlignment) {
        cell.setHorizontalAlignment(horizontalAlignment);
        return this;
    }

    public PdfPCellBuilder setVerticalAlignment(int verticalAlignment) {
        cell.setVerticalAlignment(verticalAlignment);
        return this;
    }

    public PdfPCellBuilder setPadding(float padding) {
        cell.setPadding(padding);
        return this;
    }

    public PdfPCellBuilder setRowSpan(int rowSpan) {
        cell.setRowspan(rowSpan);
        return this;
    }

    public PdfPCellBuilder setColSpan(int colSpan) {
        cell.setColspan(colSpan);
        return this;
    }

    public PdfPCellBuilder disableBorderSide(int side) {
        cell.disableBorderSide(side);
        return this;
    }

    public PdfPCellBuilder setRotation(int rotation) {
        cell.setRotation(rotation);
        return this;
    }

    public PdfPCell build() {
        return cell;
    }
}
