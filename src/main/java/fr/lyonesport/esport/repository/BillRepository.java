package fr.lyonesport.esport.repository;

import fr.lyonesport.esport.data.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BillRepository extends JpaRepository<Bill, Long> {
    @Override
    Optional<Bill> findById(Long aLong);
}
