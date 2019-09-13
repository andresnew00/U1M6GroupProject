package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;

import java.math.BigDecimal;
import java.util.List;

public class CustomerInvoiceViewModel {
    private int id;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerCompany;
    private String customerPhone;
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

    public String getCustomerFirstName() {
        return customerFirstName;
    }

    public void setCustomerFirstName(String customerName) {
        this.customerFirstName = customerName;
    }

    public String getCustomerLastName() {
        return customerLastName;
    }

    public void setCustomerLastName(String customerLasttName) {
        this.customerLastName = customerLasttName;
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

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getCustomerCompany() {
        return customerCompany;
    }

    public void setCustomerCompany(String customerCompany) {
        this.customerCompany = customerCompany;
    }

    public String getCustomerPhone() {
        return customerPhone;
    }

    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }
}
