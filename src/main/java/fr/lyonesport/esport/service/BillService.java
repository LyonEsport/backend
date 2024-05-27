package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.Order;
import fr.lyonesport.esport.repository.BillRepository;
import org.springframework.stereotype.Service;

@Service
public class BillService {

    private final BillRepository billRepository;

    public BillService(BillRepository billRepository) {
        this.billRepository = billRepository;
    }
}
