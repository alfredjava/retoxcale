package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import com.xcale.ecommerce.infrastructure.rest.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    private final ProductUseCase productUseCase;
    private final ProductMapper productMapper;

    @GetMapping
    public ResponseEntity<ProductDto> findAll() {
        return (ResponseEntity<ProductDto>) productUseCase.getAllProducts().stream()
                .map(productMapper::toDto).collect(Collectors.toList());

    }
}
