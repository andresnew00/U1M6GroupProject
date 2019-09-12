package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ahmedkaahin on 9/12/19.
 */
@Component
public class ServiceLayer {

    private final CustomerDao customerDao;
    private final InvoiceDao invoiceDao;
    private final InvoiceItemDao invoiceItemDao;
    private final ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao, ItemDao itemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    public Item saveItem(Item item) {
        return itemDao.saveItem(item);
    }

    public Item findItem(int id) {
        return itemDao.findOne(id);
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public List<Item> getAllItemsByInvoice(int id) {
        return itemDao.findAllByInvoiceItem(id);
    }

    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    public InvoiceItem getInvoiceItem(int id) {
        return invoiceItemDao.getInvoiceItem(id);
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem(int id) {
        invoiceItemDao.deleteInvoiceItem(id);
    }

    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemDao.getAllInvoiceItems();
    }


}
