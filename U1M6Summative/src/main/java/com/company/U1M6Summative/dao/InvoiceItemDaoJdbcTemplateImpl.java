package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.InvoiceItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class InvoiceItemDaoJdbcTemplateImpl implements InvoiceItemDao {


    // ===========================

    // Prepared Statements
    private static final String INSERT_INVOICEITEM_SQL=
            "insert into invoice_item (invoice_id, item_id, quantity, unit_rate, discount) values (?,?,?,?,?)";

    private static final String SELECT_INVOICEITEM_SQL =
            "select * from invoice_item where invoice_item_id = ?";

    private static final String SELECT_ALL_INVOICEITEMS_SQL =
            "select * from invoice_item";

    private static final String UPDATE_INVOICEITEM_SQL =
            "update invoice_item set invoice_id = ?, item_id = ?, quantity = ?, unit_rate = ?, discount = ? where invoice_item_id = ?";

    private static final String DELETE_INVOICEITEM_SQL =
            "delete from invoice_item where invoice_item_id =  ?";

    public static final String GET_IITEMS_BY_INVOICE_SQL =
            "select * from invoice_item where invoice_item.invoice_id = ?";

    // ===========================

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public InvoiceItemDaoJdbcTemplateImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ===========================

    @Override
    @Transactional
    public InvoiceItem addInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(INSERT_INVOICEITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getItemId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitRate(),
                invoiceItem.getDiscount());
        int id = jdbcTemplate.queryForObject("select last_insert_id()", Integer.class);
        invoiceItem.setId(id);

        return invoiceItem;
    }

    @Override
    public InvoiceItem getInvoiceItem(int id) {
        try {
            return jdbcTemplate.queryForObject(
                    SELECT_INVOICEITEM_SQL,
                    this::mapRowToInvoiceItem,
                    id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }
    }

    @Override
    public List<InvoiceItem> getAllInvoiceItems() {
        return jdbcTemplate.query(SELECT_ALL_INVOICEITEMS_SQL, this::mapRowToInvoiceItem);
    }

    @Override
    @Transactional
    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        jdbcTemplate.update(UPDATE_INVOICEITEM_SQL,
                invoiceItem.getInvoiceId(),
                invoiceItem.getItemId(),
                invoiceItem.getQuantity(),
                invoiceItem.getUnitRate(),
                invoiceItem.getDiscount(),
                invoiceItem.getId());
    }

    @Override
    @Transactional
    public void deleteInvoiceItem(int id) {
        jdbcTemplate.update(DELETE_INVOICEITEM_SQL, id);
    }

    @Override
    public List<InvoiceItem> getAllByInvoiceId(int id) {
        return jdbcTemplate.query(GET_IITEMS_BY_INVOICE_SQL, this::mapRowToInvoiceItem, id);
    }

    // ===========================

    // MapRow
    private InvoiceItem mapRowToInvoiceItem(ResultSet rs, int rowNum) throws SQLException {
        InvoiceItem ii = new InvoiceItem();
        ii.setId(rs.getInt("invoice_item_id"));
        ii.setInvoiceId(rs.getInt("invoice_id"));
        ii.setItemId(rs.getInt("item_id"));
        ii.setQuantity(rs.getInt("quantity"));
        ii.setUnitRate(rs.getBigDecimal("unit_rate"));
        ii.setDiscount(rs.getBigDecimal("discount"));
        return ii;
    }
}
