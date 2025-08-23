package com.bank.service;

import java.util.List;
import com.bank.model.Customer;

public interface CustomerService {
    Customer saveCustomer(Customer customer);
    List<Customer> getAllCustomers();
    Customer getCustomerById(Long id);
    Customer updateCustomer(Customer customer);
    void deleteCustomer(Long id);
}
