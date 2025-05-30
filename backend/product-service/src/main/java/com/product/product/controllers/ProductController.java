package com.product.product.controllers;

import com.product.product.Repository.ProductRepository;
import com.product.product.dtos.ProductResponseDto;
import com.product.product.dtos.ProductRequestDto;
import com.product.product.dtos.ProductResponseDto;
import com.product.product.model.Product;
import com.product.product.services.CloudinaryImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

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

    @PostMapping("/create")
    public ResponseEntity<String> createProduct(@RequestBody ProductRequestDto productRequestDto) {
        try {
            Product product = new Product();
            product.setProductName(productRequestDto.getTitle());
            product.setQuantity(productRequestDto.getQuantity());
            product.setExpiryDateTime(productRequestDto.getExpiryDate());
            product.setCategory(productRequestDto.getCategory());
            product.setProductImage(productRequestDto.getImageUrl());

            productRepository.save(product);

            return ResponseEntity.ok("Product created successfully.");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Error while creating product: " + e.getMessage());
        }
    }

    @GetMapping("/getProducts")
    public ResponseEntity<List<ProductResponseDto>> getProducts() {
        List<Product> products = productRepository.findAll();

        List<ProductResponseDto> response = products.stream().map(product -> {
            ProductResponseDto dto = new ProductResponseDto();
            dto.setId(product.getProductId());
            dto.setTitle(product.getProductName());
            dto.setQuantity(product.getQuantity());
            dto.setCreatedAt(product.getCreated());
            dto.setCategory(product.getCategory());
            dto.setImage(product.getProductImage());
            return dto;
        }).toList();

        return ResponseEntity.ok(response);
    }

    @GetMapping("/getProduct/{id}")
    public ResponseEntity<ProductResponseDto> getProduct(@PathVariable Long id) {
        Optional<Product> product = productRepository.findById(id);

        ProductResponseDto dto = new ProductResponseDto();
        if (product.isPresent()) {
            dto.setId(product.get().getProductId());
            dto.setTitle(product.get().getProductName());
            dto.setQuantity(product.get().getQuantity());
            dto.setCreatedAt(product.get().getCreated());
            dto.setCategory(product.get().getCategory());
            dto.setImage(product.get().getProductImage());

        }
        return ResponseEntity.ok(dto);
    }


}
