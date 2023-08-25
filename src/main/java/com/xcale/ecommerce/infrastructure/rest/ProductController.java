package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.MyException;
import com.xcale.ecommerce.infrastructure.exception.Throws;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import com.xcale.ecommerce.infrastructure.rest.mapper.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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
    public ResponseEntity<List<ProductDto>> findAll() {
        try {
            List<ProductDto> listProductDto =  productUseCase.getAllProducts().stream()
                    .map(products -> productMapper.toDto(products)).collect(Collectors.toList());

            if (listProductDto.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(listProductDto, HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error in findAll", e);
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
    @PostMapping
    @Throws(MyException.class)
    public ResponseEntity<ProductDto> createProduct(@RequestBody Product product) {
        return ResponseEntity.ok(productMapper.toDto(
                productUseCase.createProduct(product)));
    }
}
