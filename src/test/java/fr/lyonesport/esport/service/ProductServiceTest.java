package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.Product;
import fr.lyonesport.esport.repository.ProductRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;

class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductService productService = new ProductService(productRepository);

    @Test
    void get_all_products() {
        List<Product> products = productService.getAll();

        assertNotNull(products);
        assertEquals(0, products.size());
    }
}