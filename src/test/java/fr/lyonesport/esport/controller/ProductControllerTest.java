package fr.lyonesport.esport.controller;

import fr.lyonesport.esport.data.Product;
import fr.lyonesport.esport.repository.ProductRepository;
import fr.lyonesport.esport.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

class ProductControllerTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);
    private final ProductController productController = new ProductController(productService);

    @Test
    void get_all_products() {
        ResponseEntity<List<Product>> response = productController.getAll();

        assertEquals(200, response.getStatusCode().value());
        assertEquals(0, Objects.requireNonNull(response.getBody()).size());
    }
}