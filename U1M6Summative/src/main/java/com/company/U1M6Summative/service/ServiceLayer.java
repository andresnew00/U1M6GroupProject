package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
import com.company.U1M6Summative.viewmodel.InvoiceItemViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by ahmedkaahin on 9/12/19.
 */
@Component
public class ServiceLayer {

    private final CustomerDao customerDao;
    private final InvoiceDao invoiceDao;
    private final InvoiceItemDao invoiceItemDao;
    private final ItemDao itemDao;

    @Autowired
    public ServiceLayer(CustomerDao customerDao, InvoiceDao invoiceDao, InvoiceItemDao invoiceItemDao, ItemDao itemDao) {
        this.customerDao = customerDao;
        this.invoiceDao = invoiceDao;
        this.invoiceItemDao = invoiceItemDao;
        this.itemDao = itemDao;
    }

    public Item saveItem(Item item) {
        return itemDao.saveItem(item);
    }

    public Item findItem(int id) {
        return itemDao.findOne(id);
    }

    public void updateItem(Item item) {
        itemDao.updateItem(item);
    }

    public void deleteItem(int id) {
        itemDao.deleteItem(id);
    }

    public List<Item> getAllItems() {
        return itemDao.findAll();
    }

    public List<Item> getAllItemsByInvoice(int id) {
        return itemDao.findAllByInvoiceItem(id);
    }

    public InvoiceItem saveInvoiceItem(InvoiceItem invoiceItem) {
        return invoiceItemDao.addInvoiceItem(invoiceItem);
    }

    public InvoiceItem getInvoiceItem(int id) {
        return invoiceItemDao.getInvoiceItem(id);
    }

    public void updateInvoiceItem(InvoiceItem invoiceItem) {
        invoiceItemDao.updateInvoiceItem(invoiceItem);
    }

    public void deleteInvoiceItem(int id) {
        invoiceItemDao.deleteInvoiceItem(id);
    }

    public List<InvoiceItem> getAllInvoiceItems() {
        return invoiceItemDao.getAllInvoiceItems();
    }

    public Customer findCustomer(int id) {
        return customerDao.findCustomer(id);
    }

    public List<Customer> findAllCustomers() {
        return customerDao.findAllCustomer();
    }

    public Customer saveCustomer(Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    private InvoiceItemViewModel buildViewModel(Invoice invoice) {

        InvoiceItemViewModel viewModel = new InvoiceItemViewModel();

        List<Item> items = itemDao.findAllByInvoiceItem(invoice.getInvoiceId());
        Customer customer = customerDao.findCustomer(invoice.getCustomerId());
        List<InvoiceItem> invoiceItem = invoiceItemDao.getAllByInvoiceId(invoice.getInvoiceId());

        viewModel.setCustomer(customer);
        viewModel.setItem(items);

        viewModel.setUnitRate(items.get(0).getDailyRate().multiply(new BigDecimal(viewModel.getQuantity())));

        viewModel.setDiscount(new BigDecimal(0.00));

        return viewModel;

    }


}
