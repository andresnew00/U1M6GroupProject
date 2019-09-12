package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;

import java.util.List;

public interface InvoiceDao {
    //TODO Create and delete Invoices, including the associated Invoice Items
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
     * @param invoiceId
     */
    void updateInvoice(Integer invoiceId);

    /**
     * Delete an invoice in the database
     * @param invoiceId
     */
    void deleteInvoice(Integer invoiceId);
}
