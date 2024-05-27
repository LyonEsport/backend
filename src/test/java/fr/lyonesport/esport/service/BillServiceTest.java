package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.Bill;
import fr.lyonesport.esport.data.Order;
import fr.lyonesport.esport.data.OrderId;
import fr.lyonesport.esport.repository.BillRepository;
import fr.lyonesport.esport.repository.PaymentModeRepository;
import fr.lyonesport.esport.repository.SaleModeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Optional;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
class BillServiceTest {

    private final BillRepository billRepository = mock(BillRepository.class);
    private final BillService billService = new BillService(billRepository);

    @BeforeEach
    void setUp() {
        when(billRepository.findById(1L)).thenReturn(createBill());
    }

    Optional<Bill> createBill() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setBillDate(LocalDate.now());
        bill.setFee(BigDecimal.ONE);
        bill.setIsPayed(0);
        return Optional.of(bill);
    }
}