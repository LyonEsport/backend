package fr.lyonesport.esport.service;

import java.util.Optional;

import fr.lyonesport.esport.data.Product;
import fr.lyonesport.esport.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import fr.lyonesport.esport.data.Product;
import fr.lyonesport.esport.repository.ProductRepository;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public Product findById(Long id_to_get) {
        Optional<Product> product = productRepository.findById(id_to_get);
        return product.get();
    }

    public List<Product> getAll() {
        return productRepository.findAll();
    }
}
