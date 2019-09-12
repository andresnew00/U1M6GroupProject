package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.InvoiceItem;
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

    @Before
    public void setUp() throws Exception {
        List<InvoiceItem> iiList = invoiceItemDao.getAllInvoiceItems();
        for (InvoiceItem ii: iiList) {
            invoiceItemDao.deleteInvoiceItem(ii.getId());
        }
    }

    @Test
    public void getAllInvoiceItems() {
    }

    @Test
    public void updateInvoiceItem() {
    }
}