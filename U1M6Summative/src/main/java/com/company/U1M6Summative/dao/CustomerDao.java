package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Customer;

import java.util.List;

public interface CustomerDao {

    Customer saveCustomer(Customer customer);

    Customer findCustomer(int customerId);

    List<Customer> findAllCustomer();

    void updateCustomer(Customer customer);

    void deleteCustomer(int customerId);

}
