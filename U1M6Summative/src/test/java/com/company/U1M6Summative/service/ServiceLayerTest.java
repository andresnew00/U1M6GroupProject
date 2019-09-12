package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
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
        CustomerDao     customerDao;
        ItemDao itemDao;
        InvoiceDao invoiceDao;
        InvoiceItemDao invoiceItemDao

        // Helper methods
        private void setUpCustomerDaoMock() {


            custmerDao = mock (customerDaoJdbcTemplateImpl.class);
            Custmer  customer = new Custmer();

            customer_id int(11) not null auto_increment primary key,
                    first_name varchar(50) not null,
                    last_name varchar(50) not null,
                    email varchar(75) not null,
                    company varchar(50) not null,
                    phone varchar(50) not null));

            customer.setCustomerId(i);
            customer.setFirstName();
            customer.setLastName();
            customer.setEmail();
            customer.setCompany();
            customer.phone();

            Customer  customer2 = new Customer();
            customer2.setCustomerId(i);
            customer2.setFirstName();
            customer2.setLastName();
            customer2.setEmail();
            customer2.setCompany();
            customer2.phone();

            List<Customer> customerList = new ArrayList<>();
            customerList.add(customer);
            doReturn(customer).when(customerDao).addCustomer(customer2);
            doReturn(customer).when(customerDao).getCustomer(1);
            doReturn(customerList).when(customerDao).getAllCustomers();
        }

    private void setUpItemDaoMock() {


        ItemDao = mock (itemDaoJdbcTemplateImpl.class);
        Item  item= new Item();
        item.setItemId(1);
        item.setName("Computer");
        item.setDescription("Mack Pro");
        item.setDailyRate(new BigDecimal(1200.99));


        Item2  item2 = new Item();
        item2.setItemId(1);
        item2.setName("Computer");
        item2.setDescription("Mack Pro");
        item2.setDailyRate(new BigDecimal(1200.99));

        List<Item> itemList = new ArrayList<>();
        itemList.add(item);

        doReturn(item).when(itemDao).addItem(item2);
        doReturn(item).when(itemDao).getItem(1);
        doReturn(itemList).when(itemDao).getAllItems();

    }
    private void setUpInvoiceDaoMinvoiceItem{

        InvoiceDao = mock (InvoiceDaoJdbcTemplateImpl.class);
        Invoice  invoice = new Invoice();
        invoice.setInvoiceId(1);
        invoice.setOrderDate(LocalDate.of(2019,09,12);
        invoice.setPickUPDate(LocalDate.of(2019,09,12);
        invoice.setRetunDate(LocalDate.of(2019,09,12);
        invoice.setLateFee(new BigDecimal("12.99"));

        Invoice invoice2= new Invoice();
        invoice.setInvoiceId(1);
        invoice.setOrderDate(LocalDate.of(2019,09,12);
        invoice.setPickUPDate(LocalDate.of(2019,09,12);
        invoice.setRetunDate(LocalDate.of(2019,09,12);
        invoice.setLateFee(new BigDecimal("12.99"));

        List<Invoice> invoiceList = new ArrayList<>();
        invoiceList.add(invoice);
        doReturn(invoice).when(invoiceDao).addInvoice(invoice2);
        doReturn(invoice).when(invoiceDao).getInvoice(1);
        doReturn(invoiceList;
    }




        private void setUpInvoiceItemDaoMock() {

            invoiceItemDao =  mock(invoiceItemDaoJdbcTemplateImpl.class)

        InvoiceItem  invoiceItem = new InvoiceItem();
        invoiceItem.setinvoiceItemId(1);invoiceItem.setinvoiceItemId(1);
        invoiceItem.setInvoiceId(1);invoiceItem.setinvoiceItemId(1);
        invoiceItem.setItemId(1);
        invoiceItem.setQantity();
        invoiceItem.setUnitRate(LocalDate.of(2019,09,12);
        invoiceItem.setDiscount(LocalDate.of(2019,09,12);

            InvoiceItem  invoiceItem2 = new InvoiceItem();
            invoiceItem2.setinvoiceItemId(1);invoiceItem.setinvoiceItemId(1);
            invoiceItem2.setInvoiceId(1);invoiceItem.setinvoiceItemId(1);
            invoiceItem2.setItemId(1);
            invoiceItem2.setQantity();
            invoiceItem2.setUnitRate(LocalDate.of(2019,09,12);
            invoiceItem2.setDiscount(LocalDate.of(2019,09,12);





        List<InvoiceItem> invoiceItemList = new ArrayList<>();
        invoiceItemList.add(invoiceItem);
        doReturn(invoiceItem).when(invoiceItemDao).addCustomer(invoiceItem2);
        doReturn(invoiceItem).when(invoiceItemDao).getCustomer(1);
        doReturn(invoiceItemList).when(invoiceItemDao).getAllInvoiceItem();
   }

}
