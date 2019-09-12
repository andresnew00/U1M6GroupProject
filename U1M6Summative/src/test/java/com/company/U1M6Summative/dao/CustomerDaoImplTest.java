package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Customer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class CustomerDaoImplTest {

    @Autowired
    private CustomerDao customerDao;

    private Customer customer, customer2;



    @Before
    public void setUp() throws Exception {

        customerDao.findAllCustomer().forEach(customer ->{
            customerDao.deleteCustomer(customer.getCustomerId());
        });

        // private int customerId;
        //    private String firstName;
        //    private String lastName;
        //    private String email;
        //    private String company;
        //    private String phone;

        customer = new Customer();
        customer.setFirstName("Jay");
        customer.setLastName("Medina");
        customer.setEmail("Jay@email.com");
        customer.setCompany("cognizant");
        customer.setPhone("987654321");


        customer2 = new Customer();
        customer.setFirstName("Pat");
        customer.setLastName("Hussey");
        customer.setEmail("Pat@email.com");
        customer.setCompany("cognizant");
        customer.setPhone("123456789");
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void saveCustomer() {
        customer = customerDao.saveCustomer(customer);
        assertEquals(1,customerDao.findAllCustomer().size());
    }

    @Test
    public void findCustomer() {
        customer = customerDao.saveCustomer(customer);
        Customer test = customerDao.findCustomer(customer.getCustomerId());
        assertEquals(test,customer);
    }

    @Test
    public void findAllCustomer() {
        customerDao.saveCustomer(customer);
        customerDao.saveCustomer(customer2);

        List<Customer> list = customerDao.findAllCustomer();
        assertEquals(2, list.size());

    }

    @Test
    public void updateCustomer() {
        customerDao.s
    }

    @Test
    public void deleteCustomer() {
    }
}