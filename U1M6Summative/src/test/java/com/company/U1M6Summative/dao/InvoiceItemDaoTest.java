package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
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
public class InvoiceItemDaoTest {

    @Autowired
    private InvoiceItemDao invoiceItemDao;
    @Autowired
    private InvoiceDao invoiceDao;
    @Autowired
    private ItemDao itemDao;
    @Autowired
    private CustomerDao customerDao;

    /*
        customer
        item
        invoice
        invoice1
        invoiceItem
        invoiceItem1
        invoiceItem2
         */

    private Customer customer;
    private Item item;
    private Invoice invoice, invoice1;
    private InvoiceItem invoiceItem, invoiceItem1, invoiceItem2;

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

        List<Customer> customerList = customerDao.findAllCustomer();
        for (Customer c: customerList) {
            customerDao.deleteCustomer(c.getCustomerId());
        }

        customer = new Customer();
        customer.setFirstName("Delcie");
        customer.setLastName("Dion");
        customer.setEmail("email@address.com");
        customer.setCompany("Company Name");
        customer.setPhone("123-456-7890");
        customer = customerDao.saveCustomer(customer);

        item = new Item();
        item.setDailyRate(new BigDecimal("10.99"));
        item.setDescription("Test");
        item.setName("Saw");
        item = itemDao.saveItem(item);

        invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 8, 23));
        invoice.setPickupDate(LocalDate.of(2019, 8, 24));
        invoice.setReturnDate(LocalDate.of(2019,8,25));
        invoice.setLateFee(new BigDecimal("3.99"));
        invoice = invoiceDao.addInvoice(invoice);

        invoice1 = new Invoice();
        invoice1.setCustomerId(customer.getCustomerId());
        invoice1.setOrderDate(LocalDate.of(2019, 8, 23));
        invoice1.setPickupDate(LocalDate.of(2019, 8, 24));
        invoice1.setReturnDate(LocalDate.of(2019,8,25));
        invoice1.setLateFee(new BigDecimal("5797.88"));
        invoice1 = invoiceDao.addInvoice(invoice1);

        invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitRate(new BigDecimal("99.99"));
        invoiceItem.setDiscount(new BigDecimal("7.65"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(invoice.getInvoiceId());
        invoiceItem1.setItemId(item.getItemId());
        invoiceItem1.setQuantity(100);
        invoiceItem1.setUnitRate(new BigDecimal("8.99"));
        invoiceItem1.setDiscount(new BigDecimal("3.40"));
        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);

        invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(invoice1.getInvoiceId());
        invoiceItem2.setItemId(item.getItemId());
        invoiceItem2.setQuantity(100);
        invoiceItem2.setUnitRate(new BigDecimal("8.99"));
        invoiceItem2.setDiscount(new BigDecimal("3.40"));
        invoiceItem2 = invoiceItemDao.addInvoiceItem(invoiceItem2);
    }

    @Test
    public void addGetDeleteInvoiceItems() {
        InvoiceItem invoiceItemTest = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertEquals(invoiceItemTest, invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());

        invoiceItemTest = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertNull(invoiceItemTest);
    }

    @Test
    public void getAllInvoiceItems() {
        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        assertEquals(3, invoiceItemList.size());
    }

    @Test
    public void updateInvoiceItem() {
        invoiceItem.setQuantity(500);

        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItemTest = invoiceItemDao.getInvoiceItem(invoiceItem.getId());
        assertEquals(invoiceItemTest, invoiceItem);
    }

    @Test
    public void getAllByInvoiceId() {

        List<InvoiceItem> iList = invoiceItemDao.getAllByInvoiceId(invoice.getInvoiceId());
        assertEquals(2, iList.size());

        iList = invoiceItemDao.getAllByInvoiceId(invoice1.getInvoiceId());
        assertEquals(1, iList.size());


    }
}