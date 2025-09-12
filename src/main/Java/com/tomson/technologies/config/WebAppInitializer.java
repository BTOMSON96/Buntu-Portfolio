package com.tomson.technologies.config;


import jakarta.servlet.MultipartConfigElement;
import jakarta.servlet.ServletRegistration;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import java.io.File;

public class WebAppInitializer extends AbstractAnnotationConfigDispatcherServletInitializer {

    public static final String UPLOAD_DIR = "C:/ResumeCreator/temp";
    private static final long MAX_FILE_SIZE = 10485760;       // 10MB
    private static final long MAX_REQUEST_SIZE = 52428800;    // 50MB
    private static final int FILE_SIZE_THRESHOLD = 1048576;   // 1MB

    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] { AppConfig.class };
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] { WebMvcConfig.class };
    }

    @Override
    protected String[] getServletMappings() {
        return new String[] { "/" };
    }

    @Override
    protected void customizeRegistration(ServletRegistration.Dynamic registration) {
        // Ensure temp folder exists
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            boolean created = uploadDir.mkdirs();
            if (!created) {
                throw new RuntimeException("Failed to create upload directory: " + UPLOAD_DIR);
            }else{
                System.out.println("WebAppInitializer.customizeRegistration() - Upload directory created successfully: "+UPLOAD_DIR);
            }
        }else{
            System.out.println("WebAppInitializer.customizeRegistration() - Upload directory exist: "+UPLOAD_DIR);
        }
        MultipartConfigElement multipartConfig = new MultipartConfigElement(UPLOAD_DIR, MAX_FILE_SIZE, MAX_REQUEST_SIZE, FILE_SIZE_THRESHOLD);
        registration.setMultipartConfig(multipartConfig);
    }
}