package org.gudartem.aars.impl.pdf.util;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Font;
import com.itextpdf.text.pdf.BaseFont;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.format.DateTimeFormatter;

public class PdfUtil {

    private static PdfUtil instance = new PdfUtil();

    public static float POINT_BY_MM = 2.8346f;

    public static DateTimeFormatter RUS_DATE_FORMAT = DateTimeFormatter.ofPattern("dd.MM.yy");

    private Font headerFont;

    private Font commonFont;

    private Font smallFont;

    private PdfUtil() {
        BaseFont baseFont;
        try {
            File file = ResourceUtils.getFile("times.ttf");
            byte[] ttfBytes = Files.readAllBytes(file.toPath());
            baseFont = BaseFont.createFont( "times.ttf", BaseFont.IDENTITY_H, BaseFont.EMBEDDED, false, ttfBytes, null);
            headerFont = new Font(baseFont, 11, Font.BOLD, BaseColor.BLACK);
            commonFont = new Font(baseFont, 8, Font.NORMAL, BaseColor.BLACK);
            smallFont = new Font(baseFont, 6, Font.NORMAL, BaseColor.BLACK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static PdfUtil getInstance() {
        return instance;
    }

    public Font getHeaderFont() {
        return headerFont;
    }

    public Font getCommonFont() {
        return commonFont;
    }

    public Font getSmallFont() {
        return smallFont;
    }

    public static float calcPoint(float mm) {
        return POINT_BY_MM * mm;
    }
}
