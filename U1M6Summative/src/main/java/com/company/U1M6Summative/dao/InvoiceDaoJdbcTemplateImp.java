package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceDaoJdbcTemplateImp implements InvoiceDao {

    // Prepared statements
    private static final String INSERT_INVOICE_SQL =
            "INSERT INTO invoice ( order_date, pickup_date, return_date, late_fee) VALUES (?,?,?,?)";
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
        jdbcTemplate.update(INSERT_INVOICE_SQL,
                invoice.getOrderDate(), invoice.getPickupDate(), invoice.getReturnDate(), invoice.getLateFee());

        Integer invoiceId = jdbcTemplate.queryForObject("select LAST_INSERT_ID()", Integer.class);

        invoice.setInvoiceId(invoiceId);
        return invoice;
    }
    /**
     * Get an invoice by id
     *
     * @param invoiceId
     * @return
     */
    @Override
    public Invoice getInvoice(Integer invoiceId) {
        try {
            return jdbcTemplate.queryForObject(SELECT_INVOICE_SQL, this::mapRowToInvoice, invoiceId);
        } catch (EmptyResultDataAccessException e) {
            // if there is no Invoice with this id, just return null
            return null;
        }
    }
    /**
     * Get all invoices in the database
     *
     * @return
     */
    @Override
    public List<Invoice> getAllInvoices() {
        return jdbcTemplate.query(SELECT_ALL_INVOICES_SQL, this::mapRowToInvoice);
    }

    /**
     * Update an Invoice in the database
     *
     * @param invoice
     */
    @Override
    public void updateInvoice(Invoice invoice) {
        jdbcTemplate.update(UPDATE_INVOICE_SQL, invoice.getInvoiceId(), invoice.getCustomerId(),
                invoice.getOrderDate(), invoice.getPickupDate(), invoice.getReturnDate(),invoice.getLateFee());
    }

    /**
     * Delete an invoice in the database
     *
     * @param invoiceId
     */
    @Override
    public void deleteInvoice(Integer invoiceId) {
        jdbcTemplate.update(DELETE_INVOICE_SQL, invoiceId);
    }

    // ===========================

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
