package com.product.product.controllers;

import com.product.product.services.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
public class ProductController {

    @Autowired
    private CloudinaryImageService cloudinaryImageService;
    
    @PostMapping("/upload")
    public ResponseEntity<String> uploadProductImage(@RequestParam("file") MultipartFile file) {
        try {
            String imageUrl = cloudinaryImageService.uploadImage(file);
            return ResponseEntity.ok(imageUrl);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Upload failed: " + e.getMessage());
        }
    }

}
