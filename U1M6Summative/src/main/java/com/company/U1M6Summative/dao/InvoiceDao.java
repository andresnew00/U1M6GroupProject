package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface InvoiceDao {
    /**
     * Adds a new Invoice
     * @param invoice
     * @return
     */
    Invoice addInvoice(Invoice invoice);

    /**
     * Get an invoice by id
     * @param invoiceId
     * @return
     */
    Invoice getInvoice(Integer invoiceId);

    /**
     * Get all invoices in the database
     * @return
     */
    List<Invoice> getAllInvoices();

    /**
     * Update an Invoice in the database
     * @param invoice
     */
    void updateInvoice(Invoice invoice);

    /**
     * Delete an invoice in the database
     * @param invoiceId
     */
    void deleteInvoice(Integer invoiceId);
}
