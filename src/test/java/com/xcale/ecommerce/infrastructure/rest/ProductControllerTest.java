package com.xcale.ecommerce.infrastructure.rest;

import com.xcale.ecommerce.application.ProductUseCase;
import com.xcale.ecommerce.domain.Product;
import com.xcale.ecommerce.infrastructure.rest.dto.ProductDto;
import com.xcale.ecommerce.infrastructure.rest.mapper.ProductMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductControllerTest {

    @Mock
    private ProductMapper productMapper;

    @BeforeEach
    public void init(){
        productMapper =   mock(ProductMapper.class);
    }

    @Test
    public void testFindAllSuccess() {
        // Arrange
        ProductUseCase productUseCase = mock(ProductUseCase.class);
        List<Product> products = new ArrayList<>();
        products.add(new Product());
        when(productUseCase.getAllProducts()).thenReturn(products);

        ProductController productController = new ProductController(productUseCase, productMapper);

        Product product = new Product();

        ProductDto productDto = new ProductDto();

        when(productMapper.toDto(product)).thenReturn(productDto);
        // Act
        ResponseEntity<List<ProductDto>> response = productController.findAll();

        // Assert
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(products.size(), response.getBody().size());
    }

    @Test
    public void testFindAllNoContent() {
        // Arrange
        ProductUseCase productUseCase = mock(ProductUseCase.class);
        when(productUseCase.getAllProducts()).thenReturn(new ArrayList<>());

        ProductController productController = new ProductController(productUseCase, productMapper);

        // Act
        ResponseEntity<List<ProductDto>> response = productController.findAll();

        // Assert
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testFindAllServerError() {
        // Arrange
        ProductUseCase productUseCase = mock(ProductUseCase.class);
        when(productUseCase.getAllProducts()).thenThrow(new RuntimeException());

        ProductController productController = new ProductController(productUseCase, productMapper);

        // Act
        ResponseEntity<List<ProductDto>> response = productController.findAll();

        // Assert
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}