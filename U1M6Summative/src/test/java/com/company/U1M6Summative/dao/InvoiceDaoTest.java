package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Invoice;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

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
        CustomerDao.getAllInvoices().forEach(invoice -> {
            invoiceDao.deleteInvoice(invoice.getInvoiceId());
        });


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
        invoice.setReturnDate(LocalDate.of(2019,11,20));
        invoice.setLateFee(new BigDecimal("1.99"));
    }

    @Test
    public void addGetDeleteInvoiceTest() {
        //this gives the invoice its id and makes it part of the database
        invoice = invoiceDao.addInvoice(invoice);

        //checking if 1 invoice was added to the db
        assertEquals(1,invoiceDao.getAllInvoices().size());

        //adding a test case for the invoice
        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());
        //comparing to object in the database
        assertEquals(invoice1, invoice);

        //delete the object from the db using its id
        invoiceDao.deleteInvoice(invoice.getInvoiceId());

    }

    @Test
    public void updateInvoiceTest(){
        invoiceDao.updateInvoice(invoice);

        invoice.setLateFee(new BigDecimal("2.99"));
        invoiceDao.updateInvoice(invoice);
        Invoice invoice1 = invoiceDao.getInvoice(invoice.getInvoiceId());

    }

    @Test
    public void findAllInvoicesTest() {
        invoice = invoiceDao.addInvoice(invoice);
        invoice2 = invoiceDao.addInvoice(invoice2);

        //we make a list containing the items we created up here
        List<Invoice> invoicesList = invoiceDao.getAllInvoices();
        assertEquals(2, invoicesList.size());
    }

}