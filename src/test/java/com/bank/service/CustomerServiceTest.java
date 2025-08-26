package com.bank.service;

import com.bank.entity.Customer;
import com.bank.repository.CustomerRepository;
import com.bank.serviceImpl.CustomerServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;

    @InjectMocks
    private CustomerServiceImpl customerService;

    public CustomerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomer() {
        Customer customer = new Customer(1L, "John Doe", "john@test.com", "1234567890");
        when(customerRepository.save(customer)).thenReturn(customer);

        Customer saved = customerService.saveCustomer(customer);
        assertEquals("John Doe", saved.getCustomerName());
    }

    @Test
    void testFindCustomer() {
        Customer customer = new Customer(1L, "Jane Doe", "jane@test.com", "9876543210");
        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer found = customerService.getCustomerById(1L);
        assertEquals("Jane Doe", found.getCustomerName());
    }
}
