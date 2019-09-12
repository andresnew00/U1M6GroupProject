package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class InvoiceDaoTest {

    @Autowired
    CustomerDao customerDao;
    @Autowired
    InvoiceDao invoiceDao;
    @Autowired
    InvoiceItemDao invoiceItemDao;
    @Autowired
    ItemDao itemDao;

    private Invoice invoice, invoice2;

    @Before
    public void setUp() throws Exception {
        invoiceDao.getAllInvoices().forEach(invoice -> {
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        });

        invoice = new Invoice();
        invoice.setOrderDate(LocalDate.of(2019,10,12));
        invoice.setPickupDate(LocalDate.of(2019, 10, 13));
        invoice.setReturnDate(LocalDate.of(2019,10,25));
        invoice.setLateFee(new BigDecimal("1.99"));

        invoice2 = new Invoice();
        invoice.setOrderDate(LocalDate.of(2019,11,23));
        invoice.setPickupDate(LocalDate.of(2019, 11, 26));
        invoice.setReturnDate(LocalDate.of(2019,11,31));
        invoice.setLateFee(new BigDecimal("1.99"));
    }

}