package com.company.U1M6Summative.service;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dao.InvoiceItemDao;
import com.company.U1M6Summative.dao.ItemDao;
import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.dto.InvoiceItem;
import com.company.U1M6Summative.dto.Item;
import com.company.U1M6Summative.viewmodel.CustomerInvoiceViewModel;
import com.company.U1M6Summative.viewmodel.InvoiceItemViewModel;
import com.company.U1M6Summative.viewmodel.InvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    public void updateCustomer(Customer customer) {
        customerDao.updateCustomer(customer);
    }

    public void deleteCustomer(int id) {
        customerDao.deleteCustomer(id);
    }

    public Invoice findInvoice(int id) {
        return invoiceDao.getInvoice(id);
    }

    public List<Invoice> findAllInvoices() {
        return invoiceDao.getAllInvoices();
    }

    public Invoice saveInvoice(Invoice invoice) {
        return invoiceDao.addInvoice(invoice);
    }

    public void deleteInvoice(int id) {
        invoiceDao.deleteInvoice(id);
    }

    public void updateInvoice(Invoice invoice) {
        invoiceDao.updateInvoice(invoice);
    }

    private InvoiceViewModel buildViewModel(Invoice invoice) {

        InvoiceViewModel viewModel = new InvoiceViewModel();
        List<Item> items = itemDao.findAllByInvoiceItem(invoice.getInvoiceId());
        Customer customer = customerDao.findCustomer(invoice.getCustomerId());
        List<InvoiceItem> invoiceItem = invoiceItemDao.getAllByInvoiceId(invoice.getInvoiceId());

        return viewModel;

    }

    public CustomerInvoiceViewModel saveCustomer(CustomerInvoiceViewModel customerInvoiceViewModel) {

        Customer customer = new Customer();
        customer.setFirstName(customerInvoiceViewModel.getCustomerFirstName());
        customer.setLastName(customerInvoiceViewModel.getCustomerLastName());
        customer.setCompany(customerInvoiceViewModel.getCustomerCompany());
        customer.setEmail(customerInvoiceViewModel.getCustomerEmail());
        customer.setPhone(customerInvoiceViewModel.getCustomerPhone());
        customer = customerDao.saveCustomer(customer);
        customerInvoiceViewModel.setId(customer.getCustomerId());

        List<Invoice> invoices = invoiceDao.getAllInvoices().stream().filter(invoice -> invoice.getCustomerId() == customerInvoiceViewModel.getId()).collect(Collectors.toList());
        List<List<InvoiceItem>> invoiceItems = new ArrayList<>();

        for (int i = 0; i < invoices.size(); i++) {
            List<InvoiceItem> invoiceItemAtId = invoiceItemDao.getAllByInvoiceId(invoices.get(i).getInvoiceId());
            invoiceItems.add(invoiceItemAtId);
        }

        customerInvoiceViewModel.setDiscount(new BigDecimal("0"));
        customerInvoiceViewModel.setTotalForAllInvoices(new BigDecimal("0"));

        return customerInvoiceViewModel;

    }

    public CustomerInvoiceViewModel findCustomerInvoice(int id) {
        Customer customer = customerDao.findCustomer(id);
        CustomerInvoiceViewModel build = buildCustomerInvoice(customer);
        return build;
    }

    private CustomerInvoiceViewModel buildCustomerInvoice(Customer customer) {

        CustomerInvoiceViewModel cvm = new CustomerInvoiceViewModel();
        cvm.setId(customer.getCustomerId());
        cvm.setCustomerFirstName(customer.getFirstName());
        cvm.setCustomerLastName(customer.getLastName());
        cvm.setCustomerCompany(customer.getCompany());
        cvm.setCustomerPhone(customer.getPhone());
        cvm.setCustomerEmail(customer.getEmail());
        cvm.setDiscount(BigDecimal.valueOf(0.10));

        List<InvoiceViewModel> views = invoiceDao.getAllInvoices().stream().filter(
                invoice -> invoice.getCustomerId() == customer.getCustomerId()).map(
                this::buildInvoiceView).collect(Collectors.toList());

        cvm.setInvoiceViewModels(views);

        final BigDecimal[] totalAmount = {new BigDecimal(0)};

        views.forEach((model) -> model.getInvoiceItems().forEach((items) -> {
            totalAmount[0] = totalAmount[0].add(items.getUnitRate());
        }));

        cvm.setTotalForAllInvoices(totalAmount[0].subtract(totalAmount[0].multiply(cvm.getDiscount()).setScale(2, RoundingMode.HALF_EVEN)));

        return cvm;

    }

    private InvoiceViewModel buildInvoiceView(Invoice invoice) {

        InvoiceViewModel invm = new InvoiceViewModel();

        invm.setCustomerId(invoice.getCustomerId());
        invm.setInvoiceId(invoice.getInvoiceId());
        invm.setOrderDate(invoice.getOrderDate());
        invm.setPickupDate(invoice.getPickupDate());
        invm.setReturnDate(invoice.getReturnDate());
        invm.setLateFee(invoice.getLateFee());

        List<InvoiceItemViewModel> views = invoiceItemDao.getAllByInvoiceId(invm.getInvoiceId()).stream().
                map(this::buildInvoiceItemView).collect(Collectors.toList());

        invm.setInvoiceItems(views);

        return invm;

    }

    private InvoiceItemViewModel buildInvoiceItemView(InvoiceItem item) {

        InvoiceItemViewModel itemViewModel = new InvoiceItemViewModel();

        itemViewModel.setDiscount(item.getDiscount());
        itemViewModel.setId(item.getId());
        itemViewModel.setInvoiceId(item.getInvoiceId());
        itemViewModel.setItemId(item.getItemId());
        itemViewModel.setQuantity(item.getQuantity());

        Item foundItem = itemDao.findOne(item.getItemId());
        itemViewModel.setName(foundItem.getName());
        itemViewModel.setDescription(foundItem.getDescription());
        itemViewModel.setDailyRate(foundItem.getDailyRate());

        itemViewModel.setUnitRate(itemViewModel.getDailyRate().multiply(new BigDecimal(itemViewModel.getQuantity())));

        return itemViewModel;

    }

}
