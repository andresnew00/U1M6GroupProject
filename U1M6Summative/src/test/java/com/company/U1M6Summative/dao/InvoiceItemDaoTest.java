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

    /*
    invoice_item_id int(11) not null auto_increment primary key,
    invoice_id int(11) not null,
    item_id int(11) not null,
    quantity int(11) not null,
    unit_rate decimal(8,2) not null,
    discount decimal(8,2) not null
     */

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
//        invoice.setPickupDate();
    }

    @Test
    public void getAllInvoiceItems() {
    }

    @Test
    public void updateInvoiceItem() {
    }
}