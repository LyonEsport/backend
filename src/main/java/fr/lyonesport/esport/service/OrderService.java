package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.Order;
import fr.lyonesport.esport.repository.OrderRepository;
import fr.lyonesport.esport.service.exception.BillMustNotBePaidException;
import fr.lyonesport.esport.service.exception.QuantityException;
import org.springframework.stereotype.Service;

@Service
public class OrderService {

    private static OrderRepository orderRepository;

    public OrderService(OrderRepository orderRepository) {
        OrderService.orderRepository = orderRepository;
    }

    public static void save(Order order) throws BillMustNotBePaidException {
        if (order.getQuantity() <= 0) {
            throw new QuantityException("Quantity must be greater than 0");
        } else if (order.getBill().getIsPayed() == 1) {
            throw new BillMustNotBePaidException("Bill must not be payed");
        } else {
            orderRepository.save(order);
        }
    }
}
