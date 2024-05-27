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
}package fr.lyonesport.esport.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import fr.lyonesport.esport.data.Product;
import fr.lyonesport.esport.data.ProductFamily;
import fr.lyonesport.esport.data.Stock;
import fr.lyonesport.esport.data.StockType;
import fr.lyonesport.esport.data.User;
import fr.lyonesport.esport.repository.ProductFamilyRepository;
import fr.lyonesport.esport.repository.ProductRepository;
import fr.lyonesport.esport.repository.StockRepository;
import fr.lyonesport.esport.repository.StockTypeRepository;

public class ProductServiceTest {

    private final ProductRepository productRepository = mock(ProductRepository.class);
    private final ProductFamilyRepository productFamilyRepository = mock(ProductFamilyRepository.class);
    private final StockRepository stockRepository = mock(StockRepository.class);
    private final StockTypeRepository stockTypeRepository = mock(StockTypeRepository.class);

    private final ProductService productService = new ProductService(productRepository);
    
    @BeforeEach
    void setUp() {
        when(productFamilyRepository.findById(1)).thenReturn(createProductFamily());
        when(stockTypeRepository.findById(1)).thenReturn(createStockType());
        when(stockRepository.findById(1)).thenReturn(createStock());
        when(productRepository.findById(1L)).thenReturn(createProduct());
    }
    
    @Test
    void get_one_product() {
        Long id_to_get = 1L;
        Product product = productService.findById(id_to_get);
        assertNotNull(product);
        assertEquals(id_to_get, product.getId());
    }

    Optional<ProductFamily> createProductFamily() {
        return Optional.of(
            new ProductFamily(
                1,
                "FIG - Figurines",
                114,
                BigDecimal.valueOf(100),
                BigDecimal.valueOf(50),
                BigDecimal.valueOf(50)
            )
        );
    }

    Optional<Product> createProduct() {
        return Optional.of(
            new Product(
                1L,
                "FIGLOL",
                "Figurine LoL",
                BigDecimal.valueOf(5),
                BigDecimal.valueOf(15),
                createProductFamily().get(),
                new ArrayList<Stock>()
            )
        );
    }

    Optional<StockType> createStockType() {
        return Optional.of(
            new StockType(
                1L,
                "Warehouse"
            )
        );
    }

    Optional<Stock> createStock() {
        return Optional.of(
            new Stock(
                1L,
                16597L,
                createStockType().get(),
                new ArrayList<Product>()
            )
        );
    }

    
}
