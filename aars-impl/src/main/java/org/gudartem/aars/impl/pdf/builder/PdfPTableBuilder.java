package org.gudartem.aars.impl.pdf.builder;

import com.itextpdf.text.pdf.PdfPTable;
import org.gudartem.aars.impl.pdf.util.PdfUtil;

public class PdfPTableBuilder {

    private PdfPTable table;

    private PdfPTableBuilder(PdfPTable table) {
        this.table = table;
    }

    public static PdfPTableBuilder of(float...widths) {
        float[] widthInPoint = new float[widths.length];
        for (int i = 0; i < widths.length; i++) {
            widthInPoint[i] = PdfUtil.calcPoint(widths[i]);
        }
        return new PdfPTableBuilder(new PdfPTable(widthInPoint));
    }

    public PdfPTableBuilder setHorizontalAlignment(int horizontalAlignment) {
        table.setHorizontalAlignment(horizontalAlignment);
        return this;
    }

    public PdfPTableBuilder setLockedWidth(boolean lockedWidth) {
        table.setLockedWidth(lockedWidth);
        return this;
    }

    public PdfPTableBuilder setTotalWidth(float totalWidth) {
        table.setTotalWidth(PdfUtil.calcPoint(totalWidth));
        return this;
    }

    public PdfPTable build() {
        return table;
    }
}
