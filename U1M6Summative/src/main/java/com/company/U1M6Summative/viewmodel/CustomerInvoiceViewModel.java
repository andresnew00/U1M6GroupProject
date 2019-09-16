package com.company.U1M6Summative.viewmodel;

import java.math.BigDecimal;
import java.util.List;

public class CustomerInvoiceViewModel {
    private int id;
    private String customerFirstName;
    private String customerLastName;
    private String customerEmail;
    private String customerCompany;
    private String customerPhone;
    private List<InvoiceViewModel> invoices;
    //    private List<Invoice> invoices;
//    private List<List<InvoiceItem>> invoiceItems;
    private BigDecimal totalForAllInvoices;
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

    public BigDecimal getTotalForAllInvoices() {
        return totalForAllInvoices;
    }

    public void setTotalForAllInvoices(BigDecimal totalForAllInvoices) {
        this.totalForAllInvoices = totalForAllInvoices;
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

    public List<InvoiceViewModel> getInvoiceViewModels() {
        return invoices;
    }

    public void setInvoiceViewModels(List<InvoiceViewModel> invoiceViewModels) {
        this.invoices = invoiceViewModels;
    }
}
