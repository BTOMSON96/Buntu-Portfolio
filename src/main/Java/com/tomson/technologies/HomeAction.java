package com.tomson.technologies;
import com.tomson.technologies.controller.FileUploadController;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class HomeAction {

    @Autowired
    private FileUploadController fileUploadController;

    @GetMapping("/")
    public String showPortfolio() {
        return "portfolio";
    }
    @GetMapping("/ResumeCreator")
    public String goToResumeCreatorPage() {
        return "menu";
    }

    @PostMapping("/generatePDF")
    public void generatePDF(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("FileUploadController.generatePDF() - START");

        Part filePart = request.getPart("file");
        if (filePart == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No file uploaded");
            return;
        }

        String originalFileName = filePart.getSubmittedFileName();
        System.out.println("FileUploadController.generatePDF() - Uploaded File name: " + originalFileName);
        if (!originalFileName.endsWith(".html")) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "File is not of type HTML");
            return;
        }

        InputStream inputStream = filePart.getInputStream();
        try {
            // Read HTML file bytes
            byte[] fileBytes = inputStream.readAllBytes();

            // Convert HTML to PDF
            byte[] pdfBytes = fileUploadController.convertToPDF(fileBytes).toByteArray();

            // --- Send PDF to browser ---
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; filename=" + originalFileName.replace(".html", ".pdf"));
            response.getOutputStream().write(pdfBytes);
            response.getOutputStream().flush();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PostMapping("/downloadMyCV")
    public ResponseEntity<ClassPathResource> downloadMyCV() throws IOException {
        ClassPathResource cvFile = new ClassPathResource("static/buntuResume.pdf");

        if (!cvFile.exists()) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_PDF)
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=Buntu_Tomson_CV.pdf")
                .body(cvFile);
    }
}