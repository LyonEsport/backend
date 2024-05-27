package fr.lyonesport.esport.service;

import fr.lyonesport.esport.repository.ProductFamilyRepository;

public class ProductFamilyService {
    private final ProductFamilyRepository productFamilyRepository;

    public ProductFamilyService(ProductFamilyRepository productFamilyRepository) {
        this.productFamilyRepository = productFamilyRepository;
    }
}
