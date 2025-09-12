package com.tomson.technologies.util;

import jakarta.servlet.http.Part;
import org.springframework.stereotype.Service;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

@Service
public class HtmlTemplateResolver {

    // Custom Part implementation
    private static class InMemoryPart implements Part {
        private final String fileName;
        private final byte[] content;

        public InMemoryPart(String fileName, byte[] content) {
            this.fileName = fileName;
            this.content = content;
        }

        @Override
        public InputStream getInputStream() {
            return new ByteArrayInputStream(content);
        }

        @Override
        public String getContentType() {
            return "text/html";
        }

        @Override
        public String getName() {
            return fileName;
        }

        @Override
        public String getSubmittedFileName() {
            return fileName;
        }

        @Override
        public long getSize() {
            return content.length;
        }

        @Override
        public void write(String fileName) throws IOException {
            try (FileOutputStream fos = new FileOutputStream(fileName)) {
                fos.write(content);
            }
        }

        @Override
        public void delete() {
            // nothing to clean up (in-memory only)
        }

        @Override
        public String getHeader(String name) {
            return null;
        }

        @Override
        public Collection<String> getHeaders(String name) {
            return Collections.emptyList();
        }

        @Override
        public Collection<String> getHeaderNames() {
            return Collections.emptyList();
        }
    }


    public static Part resolveTemplate(HashMap<String, String> values) throws IOException {
        // Load file from resources/static
        InputStream inputStream = HtmlTemplateResolver.class.getClassLoader().getResourceAsStream("static/resumeTemplate.html");

        if (inputStream == null) {
            throw new FileNotFoundException("resumeTemplate.html not found in resources/static/");
        }

        String fileInput = new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);

        // Resolve Expressions
        for (String key : values.keySet()) {
            fileInput = fileInput.replace("${" + key + "}", values.get(key));
        }

        byte[] resolvedBytes = fileInput.getBytes(StandardCharsets.UTF_8);

        // Wrap into a custom Part
        return new InMemoryPart("resumeTemplate.html", resolvedBytes);
    }

}