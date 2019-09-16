package com.company.U1M6Summative.viewmodel;

import com.company.U1M6Summative.dto.InvoiceItem;

import java.math.BigDecimal;

public class InvoiceItemViewModel extends InvoiceItem {

    private String name;
    private String description;
    private BigDecimal dailyRate;

    public InvoiceItemViewModel() {
        super();
    }

    public InvoiceItemViewModel(int id, int invoiceId, int itemId, int quantity, BigDecimal unitRate, BigDecimal discount) {
        super(id, invoiceId, itemId, quantity, unitRate, discount);
    }

    @Override
    public int getId() {
        return super.getId();
    }

    @Override
    public void setId(int id) {
        super.setId(id);
    }

    @Override
    public int getInvoiceId() {
        return super.getInvoiceId();
    }

    @Override
    public void setInvoiceId(int invoiceId) {
        super.setInvoiceId(invoiceId);
    }

    @Override
    public int getItemId() {
        return super.getItemId();
    }

    @Override
    public void setItemId(int itemId) {
        super.setItemId(itemId);
    }

    @Override
    public int getQuantity() {
        return super.getQuantity();
    }

    @Override
    public void setQuantity(int quantity) {
        super.setQuantity(quantity);
    }

    @Override
    public BigDecimal getUnitRate() {
        return super.getUnitRate();
    }

    @Override
    public void setUnitRate(BigDecimal unitRate) {
        super.setUnitRate(unitRate);
    }

    @Override
    public BigDecimal getDiscount() {
        return super.getDiscount();
    }

    @Override
    public void setDiscount(BigDecimal discount) {
        super.setDiscount(discount);
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getDailyRate() {
        return dailyRate;
    }

    public void setDailyRate(BigDecimal dailyRate) {
        this.dailyRate = dailyRate;
    }
}
