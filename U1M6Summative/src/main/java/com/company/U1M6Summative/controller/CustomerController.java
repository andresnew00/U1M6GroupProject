package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dao.InvoiceDao;
import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Invoice;
import com.company.U1M6Summative.service.ServiceLayer;
import com.company.U1M6Summative.viewmodel.CustomerInvoiceViewModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController {



    @Autowired
    ServiceLayer service;


    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        return service.saveCustomer(customer);

    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        if (service.findAllCustomers().size() > 0) {
            return service.findAllCustomers();
        } else {
            throw new IllegalArgumentException("Customers not found.");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) {
        return service.findCustomer(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@PathVariable int id, @RequestBody @Valid Customer updated) {
        updated.setCustomerId(id);
        service.updateCustomer(updated);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(value = HttpStatus.OK)
    public void deleteCity(@PathVariable int id) {
        if (service.findAllCustomers().contains(service.findCustomer(id))) {
            service.deleteCustomer(id);
        } else {
            throw new IllegalArgumentException("Customer ID not Found");

        }
    }
//get invoices by customer
    @RequestMapping(value = "/GetInvoice/{customerId}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public CustomerInvoiceViewModel getInvoiceByCustomer(@PathVariable int customerId) {
        return service.findCustomerInvoice(customerId);
    }
}