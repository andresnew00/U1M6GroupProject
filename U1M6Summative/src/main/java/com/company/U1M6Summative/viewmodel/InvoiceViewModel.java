package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.dto.Invoice;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class InvoiceViewModel extends Invoice {

    private List<InvoiceItemViewModel> invoiceItems;

    @Override
    public Integer getInvoiceId() {
        return super.getInvoiceId();
    }

    @Override
    public void setInvoiceId(Integer invoiceId) {
        super.setInvoiceId(invoiceId);
    }

    @Override
    public Integer getCustomerId() {
        return super.getCustomerId();
    }

    @Override
    public void setCustomerId(Integer customerId) {
        super.setCustomerId(customerId);
    }

    @Override
    public LocalDate getOrderDate() {
        return super.getOrderDate();
    }

    @Override
    public void setOrderDate(LocalDate orderDate) {
        super.setOrderDate(orderDate);
    }

    @Override
    public LocalDate getPickupDate() {
        return super.getPickupDate();
    }

    @Override
    public void setPickupDate(LocalDate pickupDate) {
        super.setPickupDate(pickupDate);
    }

    @Override
    public LocalDate getReturnDate() {
        return super.getReturnDate();
    }

    @Override
    public void setReturnDate(LocalDate returnDate) {
        super.setReturnDate(returnDate);
    }

    @Override
    public BigDecimal getLateFee() {
        return super.getLateFee();
    }

    @Override
    public void setLateFee(BigDecimal lateFee) {
        super.setLateFee(lateFee);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public List<InvoiceItemViewModel> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<InvoiceItemViewModel> invoiceItems) {
        this.invoiceItems = invoiceItems;
    }

}
