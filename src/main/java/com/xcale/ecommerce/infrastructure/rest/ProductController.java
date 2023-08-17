package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import com.xcale.ecommerce.infrastructure.rest.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/product")
@Slf4j
public class ProductController {
    private final ProductUseCase productUseCase;
    private final ProductMapper productMapper;

    @GetMapping
    public List<ResponseEntity<ProductDto>> findAll() {
        return productUseCase.getAllProducts().stream()
                .map(productMapper::toDto)
                .map(ResponseEntity::ok)
                .collect(Collectors.toList());

    }
    @PostMapping
    public ResponseEntity<ProductDto> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productMapper.toDto(
                productUseCase.createProduct(product)));
    }
}
