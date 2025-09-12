package com.tomson.technologies;
import com.tomson.technologies.controller.FileUploadController;
import com.tomson.technologies.util.HtmlTemplateResolver;
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
import java.util.HashMap;

@Controller
public class HomeAction {

    @Autowired
    private FileUploadController fileUploadController;
    @Autowired
    private HtmlTemplateResolver htmlResolver;

    @GetMapping("/")
    public String showPortfolio() {
        return "portfolio";
    }
    @GetMapping("/ResumeCreator")
    public String goToResumeCreatorPage() {
        return "resumeCreatorForm";
    }
    @PostMapping("/generatePDF")
    public void generatePDF(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        System.out.println("FileUploadController.generatePDF() - START");

        HashMap<String, String> map = new HashMap<>();

        map.put("name", request.getParameter("fullName"));
        map.put("position", request.getParameter("title"));
        map.put("email", request.getParameter("email"));
        map.put("phone", request.getParameter("phone"));
        map.put("address", request.getParameter("address"));
        map.put("linkedIn", request.getParameter("linkedin"));
        map.put("proffesonalSummary", request.getParameter("linkedin"));
        map.put("skills", request.getParameter("summary"));
        map.put("currentRole", request.getParameter("experience1_title"));
        map.put("period", request.getParameter("experience1_period"));
        map.put("responsiblities", request.getParameter("experience1_details"));
        map.put("keyAchievements", request.getParameter("achievements"));
        map.put("education", request.getParameter("education"));
        map.put("competencies", request.getParameter("competencies"));
        map.put("contacts", request.getParameter("references"));

        Part filePart = htmlResolver.resolveTemplate(map);
        InputStream inputStream = filePart.getInputStream();
        try {
            // Read HTML file bytes
            byte[] fileBytes = inputStream.readAllBytes();

            // Convert HTML to PDF
            byte[] pdfBytes = fileUploadController.convertToPDF(fileBytes).toByteArray();

            // --- Send PDF to browser ---
            response.setContentType("application/pdf");
            response.setHeader("Content-Disposition", "attachment; generatedCV.pdf");
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