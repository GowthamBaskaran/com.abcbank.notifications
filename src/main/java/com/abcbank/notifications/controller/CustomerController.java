package com.abcbank.notifications.controller;

import com.abcbank.notifications.entity.Customer;
import com.abcbank.notifications.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;

/**
 * Controller class for handling HTTP requests related to customers.
 */
@RestController
@RequestMapping("/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    /**
     * Retrieves a list of all customers.
     *
     * @return ResponseEntity with a list of Customer objects and HTTP status 200 OK.
     */
    @GetMapping
    public List<Customer> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId ID of the customer to retrieve.
     * @return ResponseEntity with the retrieved Customer object and HTTP status 200 OK if found,
     *         or HTTP status 404 Not Found if the customer is not found.
     */
    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getCustomerById(@PathVariable Long customerId) {
        Optional<Customer> customerOptional = customerService.getCustomerById(customerId);
        if (customerOptional.isPresent()) {
            Customer customer = customerOptional.get();
            // Remove documents from customer to prevent infinite recursion
            customer.setDocuments(null);
            return new ResponseEntity<>(customer, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
