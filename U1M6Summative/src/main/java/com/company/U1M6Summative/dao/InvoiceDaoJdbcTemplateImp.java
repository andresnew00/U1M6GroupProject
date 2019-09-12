package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImp implements InvoiceDao {

    // Prepared statements
    private static final String INSERT_INVOICE_SQL =
            "INSERT INTO invoice (invoice_id, customer_id, order_id, order_date, pickup_date, return_date, late_fee) VALUES (?,?,?,?,?,?)";
    private static final String SELECT_INVOICE_SQL =
            "SELECT * FROM invoice WHERE invoice_id = ?";
    private static final String SELECT_ALL_INVOICES_SQL =
            "SELECT * FROM invoice";
    private static final String UPDATE_INVOICE_SQL =
            "UPDATE invoice SET invoice_id = ?, customer_id = ?, order_id = ?, order_date = ?, pickup_date = ?, return_date = ?, late_fee = ? WHERE invoice_id = ?";
    private static final String DELETE_INVOICE_SQL =
            "DELETE FROM invoice WHERE invoice_id = ?";

    // ===========================

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceDaoJdbcTemplateImp(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ===========================
    /**
     * Adds a new Invoice
     *
     * @param invoice
     * @return
     */
    @Override
    public Invoice addInvoice(Invoice invoice) {
        return null;
    }

    /**
     * Get an invoice by id
     *
     * @param invoiceId
     * @return
     */
    @Override
    public Invoice getInvoice(Integer invoiceId) {
        return null;
    }

    /**
     * Get all invoices in the database
     *
     * @return
     */
    @Override
    public List<Invoice> getAllInvoices() {
        return null;
    }

    /**
     * Update an Invoice in the database
     *
     * @param invoiceId
     */
    @Override
    public void updateInvoice(Integer invoiceId) {

    }

    /**
     * Delete an invoice in the database
     *
     * @param invoiceId
     */
    @Override
    public void deleteInvoice(Integer invoiceId) {

    }

    private Invoice mapRowToInvoice(ResultSet rs, int rowNum) throws SQLException {
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(rs.getInt("invoice_id"));
        invoice.setCustomerId(rs.getInt("customer_id"));
        invoice.setOrderDate(rs.getDate("order_date").toLocalDate());
        invoice.setPickupDate(rs.getDate("pickup_date").toLocalDate());
        invoice.setReturnDate(rs.getDate("return_date").toLocalDate());
        invoice.setLateFee(rs.getBigDecimal("late_fee"));

        return invoice;
    }
}
