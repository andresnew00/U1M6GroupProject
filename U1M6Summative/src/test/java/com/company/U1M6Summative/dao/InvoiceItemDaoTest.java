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
    }

    @Test
    public void addGetDeleteInvoiceItems() {
        Customer customer = new Customer();
        customer.setFirstName("Delcie");
        customer.setLastName("Dion");
        customer.setEmail("email@address.com");
        customer.setCompany("Company Name");
        customer.setPhone("123-456-7890");
        customer = customerDao.saveCustomer(customer);

        Item item = new Item();
        item.setDailyRate(new BigDecimal("10.99"));
        item.setDescription("Test");
        item.setName("Saw");
        item = itemDao.saveItem(item);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 8, 23));
        invoice.setPickupDate(LocalDate.of(2019, 8, 24));
        invoice.setReturnDate(LocalDate.of(2019,8,25));
        invoice.setLateFee(new BigDecimal("3.99"));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitRate(new BigDecimal("99.99"));
        invoiceItem.setDiscount(new BigDecimal("7.65"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertEquals(invoiceItem1, invoiceItem);

        invoiceItemDao.deleteInvoiceItem(invoiceItem.getId());

        invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getId());

        assertNull(invoiceItem1);
    }

    @Test
    public void getAllInvoiceItems() {
        Customer customer = new Customer();
        customer.setFirstName("Delcie");
        customer.setLastName("Dion");
        customer.setEmail("email@address.com");
        customer.setCompany("Company Name");
        customer.setPhone("123-456-7890");
        customer = customerDao.saveCustomer(customer);

        Item item = new Item();
        item.setDailyRate(new BigDecimal("10.99"));
        item.setDescription("Test");
        item.setName("Saw");
        item = itemDao.saveItem(item);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 8, 23));
        invoice.setPickupDate(LocalDate.of(2019, 8, 24));
        invoice.setReturnDate(LocalDate.of(2019,8,25));
        invoice.setLateFee(new BigDecimal("3.99"));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitRate(new BigDecimal("99.99"));
        invoiceItem.setDiscount(new BigDecimal("7.65"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = new InvoiceItem();
        invoiceItem1.setInvoiceId(invoice.getInvoiceId());
        invoiceItem1.setItemId(item.getItemId());
        invoiceItem1.setQuantity(100);
        invoiceItem1.setUnitRate(new BigDecimal("8.99"));
        invoiceItem1.setDiscount(new BigDecimal("3.40"));
        invoiceItem1 = invoiceItemDao.addInvoiceItem(invoiceItem1);

        List<InvoiceItem> invoiceItemList = invoiceItemDao.getAllInvoiceItems();
        assertEquals(2, invoiceItemList.size());
    }

    @Test
    public void updateInvoiceItem() {
        Customer customer = new Customer();
        customer.setFirstName("Delcie");
        customer.setLastName("Dion");
        customer.setEmail("email@address.com");
        customer.setCompany("Company Name");
        customer.setPhone("123-456-7890");
        customer = customerDao.saveCustomer(customer);

        Item item = new Item();
        item.setDailyRate(new BigDecimal("10.99"));
        item.setDescription("Test");
        item.setName("Saw");
        item = itemDao.saveItem(item);

        Invoice invoice = new Invoice();
        invoice.setCustomerId(customer.getCustomerId());
        invoice.setOrderDate(LocalDate.of(2019, 8, 23));
        invoice.setPickupDate(LocalDate.of(2019, 8, 24));
        invoice.setReturnDate(LocalDate.of(2019,8,25));
        invoice.setLateFee(new BigDecimal("3.99"));
        invoice = invoiceDao.addInvoice(invoice);

        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setInvoiceId(invoice.getInvoiceId());
        invoiceItem.setItemId(item.getItemId());
        invoiceItem.setQuantity(5);
        invoiceItem.setUnitRate(new BigDecimal("99.99"));
        invoiceItem.setDiscount(new BigDecimal("7.65"));
        invoiceItem = invoiceItemDao.addInvoiceItem(invoiceItem);

        invoiceItem.setQuantity(500);

        invoiceItemDao.updateInvoiceItem(invoiceItem);

        InvoiceItem invoiceItem1 = invoiceItemDao.getInvoiceItem(invoiceItem.getId());
        assertEquals(invoiceItem1, invoiceItem);
    }
}