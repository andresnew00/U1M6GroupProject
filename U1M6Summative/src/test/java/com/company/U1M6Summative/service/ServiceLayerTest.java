package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.*;
import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
import com.company.U1M6Summative.viewmodel.CustomerInvoiceViewModel;
import com.sun.org.apache.bcel.internal.generic.ACONST_NULL;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.times;

/**
 * Created by ahmedkaahin on 9/12/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ServiceLayerTest {

    ServiceLayer service;
    CustomerDao customerDao;
    ItemDao itemDao;
    InvoiceDao invoiceDao;
    InvoiceItemDao invoiceItemDao;

    // Helper methods
    private void setUpCustomerDaoMock() {

        customerDao = mock(CustomerDaoImpl.class);
        Customer customer = new Customer();

        customer.setCustomerId(1);
        customer.setFirstName("Jay");
        customer.setLastName("Jay");
        customer.setEmail("@jay");
        customer.setCompany("JayComp");
        customer.setPhone("111-222-3333");

        Customer customer2 = new Customer();
        customer2.setFirstName("Jay");
        customer2.setLastName("Jay");
        customer2.setEmail("@jay");
        customer2.setCompany("JayComp");
        customer2.setPhone("111-222-3333");

        List<Customer> customerList = new ArrayList<>();
        customerList.add(customer);
        doReturn(customer).when(customerDao).saveCustomer(customer2);
        doReturn(customer).when(customerDao).findCustomer(1);
        doReturn(customerList).when(customerDao).findAllCustomer();
    }

    private void setUpItemDaoMock() {
        itemDao = mock(ItemDaoJdbcTemplateImpl.class);
        Item item = new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));


        Item item2 = new Item();
        item2.setItemId(1);
        item2.setName("Computer");
        item2.setDescription("Mack Pro");
        item2.setDailyRate(new BigDecimal(1200.99));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        doReturn(item).when(itemDao).saveItem(item2);
        doReturn(item).when(itemDao).findOne(1);
        doReturn(itemList).when(itemDao).findAll();

    }

    private void setUpInvoiceDaoMock() {
        invoiceDao = mock(InvoiceDaoJdbcTemplateImp.class);
        Invoice invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2019, 9, 12));
        invoice.setPickupDate(LocalDate.of(2019, 9, 12));
        invoice.setReturnDate(LocalDate.of(2019, 9, 12));
        invoice.setLateFee(new BigDecimal("12.99"));

        Invoice invoice2 = new Invoice();
        invoice2.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2019, 9, 12));
        invoice.setPickupDate(LocalDate.of(2019, 9, 12));
        invoice.setReturnDate(LocalDate.of(2019, 9, 12));
        invoice.setLateFee(new BigDecimal("12.99"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList).when(invoiceDao).getAllInvoices();
    }

    private void setUpInvoiceItemDaoMock() {
        invoiceItemDao = mock(InvoiceItemDaoJdbcTemplateImpl.class);
        InvoiceItem invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setItemId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitRate(new BigDecimal("10.99"));
        invoiceItem.setDiscount(new BigDecimal("0.00"));

        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(1);
        invoiceItem2.setItemId(1);
        invoiceItem2.setQuantity(10);
        invoiceItem2.setUnitRate(new BigDecimal("10.99"));
        invoiceItem2.setDiscount(new BigDecimal("0.00"));

        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);
        doReturn(invoiceItem).when(invoiceItemDao).addInvoiceItem(invoiceItem2);
        doReturn(invoiceItem).when(invoiceItemDao).getInvoiceItem(1);
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItems();
    }

    @Before
    public void setUp() throws Exception {
        setUpCustomerDaoMock();
        setUpItemDaoMock();
        setUpInvoiceItemDaoMock();
        setUpInvoiceDaoMock();
        service = new ServiceLayer(customerDao, invoiceDao, invoiceItemDao, itemDao);
    }


    public void saveCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Jay");
        customer.setLastName("Jay");
        customer.setEmail("@jay");
        customer.setCompany("JayComp");
        customer.setPhone("111-222-3333");
        customer = service.saveCustomer(customer);
    }

    @Test
    public void findCustomer() {
        Customer customer = new Customer();
        customer.setFirstName("Jay");
        customer.setLastName("Jay");
        customer.setEmail("@jay");
        customer.setCompany("JayComp");
        customer.setPhone("111-222-3333");
        customer = service.saveCustomer(customer);
        Customer fromService = service.findCustomer(customer.getCustomerId());
        assertEquals(customer, fromService);
    }

    @Test
    public void findAllCustomers() {
        Customer customer = new Customer();
        customer.setFirstName("Jay");
        customer.setLastName("Jay");
        customer.setEmail("@jay");
        customer.setCompany("JayComp");
        customer.setPhone("111-222-3333");
        customer = service.saveCustomer(customer);
        List<Customer> customerList = service.findAllCustomers();
        assertEquals(1, customerList.size());
        assertEquals(customer, customerList.get(0));
    }

    @Test
    public void updateCustomer() {
        Customer customer = new Customer();
        customer.setLastName("Jay");
        customer.setEmail("@jay");
        customer.setCompany("JayComp");
        customer.setPhone("111-222-3333");
        customer = service.saveCustomer(customer);
        ArgumentCaptor<Customer> customerCaptor = ArgumentCaptor.forClass(Customer.class);
        doNothing().when(customerDao).updateCustomer(customerCaptor.capture());
        service.updateCustomer(customer);
        verify(customerDao, times(1)).updateCustomer(customerCaptor.getValue());
        Customer customer2 = customerCaptor.getValue();
        assertEquals(customer, customer2);
    }

    @Test
    public void deleteCustomer() {
        Customer customer = new Customer();
        customer.setCustomerId(10);
        ArgumentCaptor<Integer> integerCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(customerDao).deleteCustomer(integerCaptor.capture());
        service.deleteCustomer(10);
        verify(customerDao, times(1)).deleteCustomer(integerCaptor.getValue());
        assertEquals(10, integerCaptor.getValue().intValue());
    }

    //=======================================================

    @Test
    public void saveItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));
        item = service.saveItem(item);
    }

    @Test
    public void findItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));
        Item fromService = service.findItem(item.getItemId());
        assertEquals(item, fromService);
    }

    @Test
    public void findAllItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));
        List<Item> itemList = service.getAllItems();
        assertEquals(1, itemList.size());
        assertEquals(item, itemList.get(0));
    }

    @Test
    public void updateItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));
        ArgumentCaptor<Item> itemCaptor = ArgumentCaptor.forClass(Item.class);
        doNothing().when(itemDao).updateItem(itemCaptor.capture());
        service.updateItem(item);
        verify(itemDao, times(1)).updateItem(itemCaptor.getValue());
        Item item2 = itemCaptor.getValue();
        assertEquals(item, item2);
    }

    @Test
    public void deleteItem() {
        Item item = new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));
        ArgumentCaptor<Integer> integerCaptor = ArgumentCaptor.forClass(Integer.class);
        doNothing().when(itemDao).deleteItem(integerCaptor.capture());
        service.deleteItem(10);
        verify(itemDao, times(1)).deleteItem(integerCaptor.getValue());
        item.setItemId(10);
        assertEquals(10, integerCaptor.getValue().intValue());
    }


    @Test
    public void testViewModel() {

        CustomerInvoiceViewModel cvm = new CustomerInvoiceViewModel();
        cvm.setCustomerFirstName("Jay");
        cvm.setCustomerLastName("Jay");
        cvm.setCustomerEmail("@jay");
        cvm.setCustomerCompany("JayComp");
        cvm.setCustomerPhone("111-222-3333");
        Invoice invoice = new Invoice();
        invoice.setCustomerId(1);
        invoice.setOrderDate(LocalDate.of(2019, 9, 12));
        invoice.setPickupDate(LocalDate.of(2019, 9, 12));
        invoice.setReturnDate(LocalDate.of(2019, 9, 12));
        invoice.setLateFee(new BigDecimal("12.99"));
        service.saveInvoice(invoice);
        InvoiceItem invoiceItem2 = new InvoiceItem();
        invoiceItem2.setInvoiceId(1);
        invoiceItem2.setItemId(1);
        invoiceItem2.setQuantity(10);
        invoiceItem2.setUnitRate(new BigDecimal("10.99"));
        invoiceItem2.setDiscount(new BigDecimal("0.00"));
        service.saveInvoiceItem(invoiceItem2);
        List<List<InvoiceItem>> container = new ArrayList<>();
//        container.set(0, new ArrayList<>());
        container.add(service.getAllInvoiceItems());
        cvm.setInvoices(service.findAllInvoices());
        cvm.setInvoiceItems(container);
        cvm = service.saveCustomer(cvm);
    }


//    @Test
//    public void findInvoiceByCustomer() {
//        Invoice invoice = new Invoice();
//        invoice.setInvoiceId(1);
//        invoice.setCustomerId(10);
//        invoice.setOrderDate(LocalDate.of(2019, 9, 12));
//        invoice.setPickupDate(LocalDate.of(2019, 9, 12));
//        invoice.setReturnDate(LocalDate.of(2019, 9, 12));
//        invoice.setLateFee(new BigDecimal("12.99"));
//        service.saveInvoice(invoice);
//
//        Invoice invoice2 = new Invoice();
//        invoice2.setInvoiceId(1);
//        invoice2.setCustomerId(10);
//        invoice2.setOrderDate(LocalDate.of(2019, 9, 12));
//        invoice2.setPickupDate(LocalDate.of(2019, 9, 12));
//        invoice2.setReturnDate(LocalDate.of(2019, 9, 12));
//        invoice2.setLateFee(new BigDecimal("12.99"));
//        service.saveInvoice(invoice);
//
//
//       // List<Invoice> invoiceList = service.getInvoiceByCustomerId(10);
//       // assertEquals(invoiceList.size(), 2);
//
//        //invoiceList = service.getInvoiceByCustomerId(10);
//        //assertEquals(invoiceList.size(), 1);
//    }

//    @Test
//    public void saveAndDeleteInvoiceByItem() {
//
//        InvoiceItem  invoiceItem = new InvoiceItem();
//        invoiceItem.setId(1);
//        invoiceItem.setInvoiceId(1);
//        invoiceItem.setItemId(1);
//        invoiceItem.setQuantity(10);
//        invoiceItem.setUnitRate(new BigDecimal("10.99"));
//        invoiceItem.setDiscount(new BigDecimal("0.00"));
//        invoiceItem = service.saveInvoiceItem(invoiceItem);
//
//        ArgumentCaptor<Integer> integerCaptor = ArgumentCaptor.forClass(Integer.class);
//        doNothing().when(invoiceItemDao).deleteInvoiceItem(integerCaptor.capture());
//        service.deleteItem(10);
//        verify(itemDao, times(1)).deleteItem(integerCaptor.getValue());
//        invoiceItem.setItemId(10);
//        assertEquals(10, integerCaptor.getValue().intValue());
//    }

}


// *Your REST API must allow the end user to:
//        * Perform standard CRUD operations for Customers
//        * Perform standard CRUD operations for Items
//        * Create and delete Invoices, including the associated
//           Invoice Items
//        * Find Invoices by Customer
