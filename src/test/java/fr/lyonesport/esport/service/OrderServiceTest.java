package fr.lyonesport.esport.service;

import fr.lyonesport.esport.data.*;
import fr.lyonesport.esport.repository.OrderRepository;
import fr.lyonesport.esport.service.exception.BillMustNotBePaidException;
import fr.lyonesport.esport.service.exception.QuantityException;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceTest {

    private final OrderRepository orderRepository = mock(OrderRepository.class);
    private final OrderService orderService = new OrderService(orderRepository);

    @Test
    void save_order() throws BillMustNotBePaidException {
        OrderService.save(createOrder());
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void error_when_saving_on_payed_bill() {
        assertThrows(BillMustNotBePaidException.class, () -> OrderService.save(createOrderPayedBill()));
    }

    @Test
    void error_when_saving_with_0_or_less_quantity() {
        assertThrows(QuantityException.class, () -> OrderService.save(createOrder0Quantity()));
    }

    Order createOrder0Quantity() {
        Order order = new Order();
        order.setId(createOrderId(createBill(), createUser(), createProduct()));
        order.setQuantity(0);
        order.setBill(createBill());
        order.setUser(createUser());
        order.setProduct(createProduct());
        return order;
    }

    Order createOrder() {
        Order order = new Order();
        order.setId(createOrderId(createBill(), createUser(), createProduct()));
        order.setQuantity(1);
        order.setBill(createBill());
        order.setUser(createUser());
        order.setProduct(createProduct());
        return order;
    }

    OrderId createOrderId(Bill bill, User user, Product product) {
        OrderId orderId = new OrderId();
        orderId.setIdUser(user.getId());
        orderId.setIdBill(bill.getId());
        orderId.setIdProduct(product.getId());
        return orderId;
    }

    Order createOrderPayedBill() {
        Order order = new Order();
        order.setId(createOrderId(createBillPayedBill(), createUser(), createProduct()));
        order.setQuantity(1);
        order.setBill(createBillPayedBill());
        order.setUser(createUser());
        order.setProduct(createProduct());
        return order;
    }

    Product createProduct() {
        Product product = new Product();
        product.setId(1L);
        return product;
    }

    Bill createBill() {
        Bill bill = new Bill();
        bill.setId(1L);
        bill.setIsPayed(0);
        return bill;
    }

    Bill createBillPayedBill() {
        Bill bill = new Bill();
        bill.setId(2L);
        bill.setIsPayed(1);
        return bill;
    }

    User createUser() {
        User user = new User();
        user.setId(1L);
        return user;
    }
}