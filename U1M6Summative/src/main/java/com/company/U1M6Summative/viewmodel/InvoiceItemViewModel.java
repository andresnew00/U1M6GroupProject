package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.Item;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

public class InvoiceItemViewModel {

    private int id;
    private Invoice invoice;
    private List<Item> item;
    private int quantity;
    private BigDecimal unitRate;
    private BigDecimal discount;
    private Customer customer;

    public InvoiceItemViewModel() {
    }

    public InvoiceItemViewModel(int id, Invoice invoice, List<Item> item, int quantity, BigDecimal unitRate, BigDecimal discount, Customer customer) {
        this.id = id;
        this.invoice = invoice;
        this.item = item;
        this.quantity = quantity;
        this.unitRate = unitRate;
        this.discount = discount;
        this.customer = customer;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Invoice getInvoice() {
        return invoice;
    }

    public void setInvoice(Invoice invoice) {
        this.invoice = invoice;
    }

    public List<Item> getItem() {
        return item;
    }

    public void setItem(List<Item> item) {
        this.item = item;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getUnitRate() {
        return unitRate;
    }

    public void setUnitRate(BigDecimal unitRate) {
        this.unitRate = unitRate;
    }

    public BigDecimal getDiscount() {
        return discount;
    }

    public void setDiscount(BigDecimal discount) {
        this.discount = discount;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InvoiceItemViewModel that = (InvoiceItemViewModel) o;
        return id == that.id &&
                quantity == that.quantity &&
                Objects.equals(invoice, that.invoice) &&
                Objects.equals(item, that.item) &&
                Objects.equals(unitRate, that.unitRate) &&
                Objects.equals(discount, that.discount) &&
                Objects.equals(customer, that.customer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, invoice, item, quantity, unitRate, discount, customer);
    }
}
