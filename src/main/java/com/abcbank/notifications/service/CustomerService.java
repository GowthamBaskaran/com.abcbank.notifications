package com.abcbank.notifications.service;

import com.abcbank.notifications.entity.Customer;
import com.abcbank.notifications.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Service class for performing operations related to customers.
 */
@Service
public class CustomerService
{

    @Autowired
    private CustomerRepository customerRepository;


    /**
     * Retrieves a list of all customers.
     *
     * @return List of Customer objects.
     */
    public List<Customer> getAllCustomers()
    {
        return customerRepository.findAll();
    }


    /**
     * Retrieves a customer by their ID.
     *
     * @param customerId ID of the customer to retrieve.
     * @return Optional containing the retrieved Customer object if found, empty Optional otherwise.
     */
    public Optional<Customer> getCustomerById(Long customerId)
    {
        return customerRepository.findById(customerId);
    }


    /**
     * Retrieves a list of customers with documents expiring on the specified date.
     *
     * @param date The date for which to retrieve customers with expiring documents.
     * @return List of Customer objects with documents expiring on the specified date.
     */
    public List<Customer> getCustomersWithExpiringDocuments(LocalDate date)
    {
        // Implement logic to fetch customers whose documents are expiring on the given date
        // You may need to query the database using customerRepository
        // Example: customerRepository.findCustomersWithExpiringDocuments(date);
        return customerRepository.getDocumentsByDateDifference(date);
    }
}
