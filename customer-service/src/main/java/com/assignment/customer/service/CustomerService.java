package com.assignment.customer.service;

import com.assignment.customer.dto.Account;
import com.assignment.customer.dto.CustomerRequest;
import com.assignment.customer.entity.Customer;
import com.assignment.customer.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CustomerService {

    private static final Logger logger = LoggerFactory.getLogger(CustomerService.class);


    @Autowired
    private CustomerRepository customerRepository;


    // Cache the result of this method with key = customerId
   // @Cacheable(value = "customers", key = "#customerRequest.customerEmail")
    public Customer saveCustomer(CustomerRequest customerRequest) {

        Optional<Customer> existingCustomer = customerRepository.findByCustomerEmail(customerRequest.getCustomerEmail());

        if (existingCustomer.isPresent()) {
            throw new IllegalArgumentException("Customer with email " + customerRequest.getCustomerEmail() + " already exists");
        }


        Customer customer = Customer.builder()
                .customerEmail(customerRequest.getCustomerEmail())
                .customerLastName(customerRequest.getCustomerLastName())
                .customerFirstName(customerRequest.getCustomerFirstName())
                .customerAddress(customerRequest.getCustomerAddress())
                .customerPhone(customerRequest.getCustomerPhone())

                .build();
        return customerRepository.save(customer);
    }

    // Cache the result of this method with key = "allCustomers"
   // @Cacheable(value = "customers", key = "'allCustomers'")
    public List<Customer> getAllCustomers() {

        List<Customer> customers = customerRepository.findAll();
        return CollectionUtils.isEmpty(customers) ? Collections.emptyList() : customers;
    }

    // Cache the result of this method with key = #id
    //@Cacheable(value = "customers", key = "#id")
    public Customer getCustomerDetails(Long id) {
        logger.info("Fetching customer from DB, ID: {}", id);
        return customerRepository.findById(id).orElse(null);
    }

    // Evict the cache entry with key = #id when a customer is deleted
    //@CacheEvict(value = "customers", key = "#id")
    public String deleteCustomer(Long id) {
        Customer customer = getCustomerDetails(id);

        if (Objects.nonNull(customer)) {
            customerRepository.deleteById(id);

            return "Customer deleted successfully";
        } else {
            return "Customer not found";
        }
    }

    // Update the cache entry with key = #id when a customer is updated
    //@CachePut(value = "customers", key = "#id")
    public Customer updateCustomerDetails(Long id, Customer customerRequest) {
        Optional<Customer> existingCustomerOpt = customerRepository.findById(id);
        if (existingCustomerOpt.isPresent()) {
            Customer existingCustomer = existingCustomerOpt.get();
            existingCustomer.setCustomerFirstName(customerRequest.getCustomerFirstName());
            existingCustomer.setCustomerLastName(customerRequest.getCustomerLastName());
            existingCustomer.setCustomerEmail(customerRequest.getCustomerEmail());
            existingCustomer.setCustomerPhone(customerRequest.getCustomerPhone());
            existingCustomer.setCustomerAddress(customerRequest.getCustomerAddress());
            return customerRepository.save(existingCustomer);
        } else {
            return null;
        }
    }

    // Cache the result of this method with key = #customerId + '-' + #customerEmail
    //@Cacheable(value = "customers", key = "#customerId + '-' + #customerEmail")
    public Customer findCustomerByIdAndEmail(Long customerId, String customerEmail) {
        return customerRepository.findByCustomerIdAndCustomerEmail(customerId, customerEmail);
    }

    // Cache the result of this method with key = #accountId
    //@Cacheable(value = "customers", key = "#accountId")
//    public Customer findCustomerByAccountId(Long accountId) {
//        Optional<Customer> customer = customerRepository.findByAccountId(accountId);
//        return customer.orElse(null);
//    }
}