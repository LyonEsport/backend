package fr.lyonesport.esport.service.exception;

import fr.lyonesport.esport.data.Bill;

import javax.management.BadAttributeValueExpException;

public class BillMustNotBePaidException extends BadAttributeValueExpException {
    public BillMustNotBePaidException(Bill bill) {
        super(bill);
    }

    public BillMustNotBePaidException(String message) {
        super(message);
    }
}
