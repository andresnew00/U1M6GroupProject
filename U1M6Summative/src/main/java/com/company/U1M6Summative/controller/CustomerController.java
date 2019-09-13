package com.company.U1M6Summative.controller;

import com.company.U1M6Summative.dao.CustomerDao;
import com.company.U1M6Summative.dto.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/Customer")
public class CustomerController {

    @Autowired
    CustomerDao customerDao;

    private List<Customer> customerList = new ArrayList<Customer>();

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(value = HttpStatus.CREATED)
    public Customer addCustomer(@RequestBody @Valid Customer customer) {
        return customerDao.saveCustomer(customer);
    }

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public List<Customer> getAllCustomers() {
        if (customerDao.findAllCustomer().size() > 0) {
           return customerDao.findAllCustomer();
        } else {
            throw new IllegalArgumentException("Customer not found.");
        }
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(value = HttpStatus.OK)
    public Customer getCustomer(@PathVariable int id) {
                return customerDao.findCustomer(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseStatus(value = HttpStatus.OK)
    public void updateCustomer(@PathVariable int id,@RequestBody Customer updated) {
        updated.setCustomerId(id);
      customerDao.updateCustomer(updated);
    }

}