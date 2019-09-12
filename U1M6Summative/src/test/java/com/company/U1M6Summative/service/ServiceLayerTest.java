package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.*;
import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.doReturn;

import static org.mockito.Mockito.mock;



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


            customerDao  = mock (CustomerDaoImpl.class);
            Customer customer = new Customer();

            customer.setCustomerId(1);
            customer.setFirstName("Jay");
            customer.setLastName("Jay");
            customer.setEmail("@jay");
            customer.setCompany("JayComp");
            customer.setPhone("111-222-3333");

            Customer  customer2 = new Customer();

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
        itemDao = mock (ItemDaoJdbcTemplateImpl.class);
        Item item= new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));


        Item  item2 = new Item();
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
    private void setUpInvoiceDaoMinvoiceItem(){
        invoiceDao = mock (InvoiceDaoJdbcTemplateImp.class);
        Invoice  invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setOrderDate(LocalDate.of(2019,9,12));
        invoice.setPickupDate(LocalDate.of(2019,9,12));
        invoice.setReturnDate(LocalDate.of(2019,9,12));
        invoice.setLateFee(new BigDecimal("12.99"));

        Invoice invoice2= new Invoice();
        invoice.setInvoiceId(1);
        invoice.setOrderDate(LocalDate.of(2019,9,12));
        invoice.setPickupDate(LocalDate.of(2019,9,12));
        invoice.setReturnDate(LocalDate.of(2019,9,12));
        invoice.setLateFee(new BigDecimal("12.99"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList);
    }




        private void setUpInvoiceItemDaoMock() {

            invoiceItemDao =  mock(InvoiceItemDaoJdbcTemplateImpl.class);

        InvoiceItem  invoiceItem = new InvoiceItem();
        invoiceItem.setId(1);
        invoiceItem.setInvoiceId(1);
        invoiceItem.setItemId(1);
        invoiceItem.setQuantity(10);
        invoiceItem.setUnitRate(new BigDecimal("10.99"));
        invoiceItem.setDiscount(new BigDecimal("0.00"));

            InvoiceItem  invoiceItem2 = new InvoiceItem();

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

}
