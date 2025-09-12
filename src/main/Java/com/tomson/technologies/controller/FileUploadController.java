package com.tomson.technologies.controller;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.nio.charset.StandardCharsets;

@Service
public class FileUploadController {
    public ByteArrayOutputStream convertToPDF(byte[] fileBytes) throws Exception {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

        String html = new String(fileBytes, StandardCharsets.UTF_8);
        // Parse and clean HTML
        Document doc = Jsoup.parse(html);
        doc.outputSettings().syntax(Document.OutputSettings.Syntax.xml);
        doc.outputSettings().escapeMode(org.jsoup.nodes.Entities.EscapeMode.xhtml);
        String xhtml = doc.html();

        // Generate PDF
        PdfRendererBuilder builder = new PdfRendererBuilder();
        builder.withHtmlContent(xhtml, null);
        builder.toStream(outputStream);
        builder.run();
        return outputStream;
    }
}