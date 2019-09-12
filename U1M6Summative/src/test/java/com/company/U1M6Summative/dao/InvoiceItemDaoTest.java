package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceItemDaoTest {

    @Autowired
    private InvoiceItemDao invoiceItemDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ItemDao itemDao;

    @Before
    public void setUp() throws Exception {
        List<InvoiceItem> iiList = invoiceItemDao.getAllInvoiceItems();
        for (InvoiceItem ii: iiList) {
            invoiceItemDao.deleteInvoiceItem(ii.getId());
        }

        List<Invoice> invoiceList = invoiceDao.getAllInvoices();
        for (Invoice i: invoiceList) {
            invoiceDao.deleteInvoice(i.getInvoiceId());
        }

        List<Item> itemList =  itemDao.findAll();
        for (Item item: itemList) {
            itemDao.deleteItem(item.getItemId());
        }
    }

    @Test
    public void addGetDeleteInvoiceItems() {
        
    }

    @Test
    public void getAllInvoiceItems() {
    }

    @Test
    public void updateInvoiceItem() {
    }
}