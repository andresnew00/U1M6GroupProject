package com.company.U1M6Summative.dao;

import com.company.U1M6Summative.dto.Customer;
import com.company.U1M6Summative.dto.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class CustomerDaoImpl implements CustomerDao{

    private JdbcTemplate sql;

    //   first_name varchar(50) not null,
    //    last_name varchar(50) not null,
    //    email varchar(75) not null,
    //    company varchar(50) not null,
    //    phone


    @Autowired
    public CustomerDaoImpl(JdbcTemplate sql){
        this.sql = sql;
    }

    private Customer mapItemToRow(ResultSet set, int rowNumber) throws SQLException {
        List<String> columnNames = new ArrayList<>();

        for (int i = 1; i <= set.getMetaData().getColumnCount(); i++) {
            columnNames.add(set.getMetaData().getColumnName(i));
        }

        columnNames = columnNames.stream().sorted(Comparator.naturalOrder()).collect(Collectors.toList());

        List<Method> setters = Arrays.stream(Customer.class.getMethods()).filter(method -> method.getName().contains("set")).
                sorted(Comparator.comparing(Method::getName)).collect(Collectors.toList());

        Customer customer = new Customer();

        for (int i = 0; i < setters.size(); i++) {
            try {
                setters.get(i).invoke(customer, set.getObject(columnNames.get(i)));
            } catch (IllegalAccessException | InvocationTargetException e) {
                System.err.println("Error in reflective method call");
            }
        }
        return customer;
    }

    //prepared statements
    private String INSERT_CUSTOMER_SQL=
            "INSERT INTO customer (first_name, last_name, email, company, phone) VALUES(?,?,?,?,?)";
    private String SELECT_CUSTOMER_SQL=
            "SELECT * FROM customer WHERE customer_id = ?";
    private String GET_ALL_CUSTTOMER_SQL=
            "SELECT * FROM customer";
    private String UPDATE_CUSTOMER_SQL=
            "UPDATE customer SET first_name=?, last_name=?, email=?, company=?,phone=?) WHERE customer.customer_id=?";
    private String DELETE_CUSTOMER_SQL=
            "DELETE FROM customer WHERE customer.customer_id=?";


    @Override
    public Customer saveCustomer(Customer customer) {
        sql.update(INSERT_CUSTOMER_SQL, customer.getFirstName(),customer.getLastName(),
                customer.getEmail(),customer.getCompany(), customer.getPhone());
        customer.setCustomerId(sql.queryForObject("select last_insert_id()", Integer.class));
        return customer;
    }

    @Override
    public Customer findCustomer(int customerId) {
        try{
            return sql.queryForObject(SELECT_CUSTOMER_SQL, this::mapItemToRow, customerId);

        }catch(EmptyResultDataAccessException e){
            return null;
        }
    }

    @Override
    public List<Customer> findAllCustomer() {
        return sql.query(GET_ALL_CUSTTOMER_SQL, this::mapItemToRow);
    }

    @Override
    public void updateCustomer(Customer customer) {
        sql.update(UPDATE_CUSTOMER_SQL, customer.getFirstName(),customer.getLastName(),customer.getEmail(),customer.getCompany(),customer.getPhone(),customer.getCustomerId());

    }

    @Override
    public void deleteCustomer(int customerId) {

        sql.update(DELETE_CUSTOMER_SQL,customerId);

    }
}
