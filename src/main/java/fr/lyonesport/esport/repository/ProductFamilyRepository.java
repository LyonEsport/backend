package fr.lyonesport.esport.repository;

import fr.lyonesport.esport.data.ProductFamily;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductFamilyRepository extends JpaRepository<ProductFamily, Integer> {
}
