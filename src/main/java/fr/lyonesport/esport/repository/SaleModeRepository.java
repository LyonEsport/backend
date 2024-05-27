package fr.lyonesport.esport.repository;

import fr.lyonesport.esport.data.SaleMode;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SaleModeRepository extends JpaRepository<SaleMode, Long> {
    SaleMode findByLabel(String label);
}
