package fr.lyonesport.esport.repository;

import fr.lyonesport.esport.data.StockType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StockTypeRepository extends JpaRepository<StockType, Integer> {
}
