package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;

import java.math.BigDecimal;
import java.util.List;

public class CustomerInvoiceViewModel {
    private int id;
    private String customerName;
    private List<Invoice> invoices;
    private List<List<InvoiceItem>> invoiceItems;
    private BigDecimal unitRate;
    private BigDecimal discount;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public List<Invoice> getInvoices() {
        return invoices;
    }

    public void setInvoices(List<Invoice> invoices) {
        this.invoices = invoices;
    }

    public List<List<InvoiceItem>> getInvoiceItems() {
        return invoiceItems;
    }

    public void setInvoiceItems(List<List<InvoiceItem>> invoiceItems) {
        this.invoiceItems = invoiceItems;
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
}
