package com.abcbank.notifications.service;

import com.abcbank.notifications.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationSchedulerService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private NotificationService notificationService;

    // Method to be executed by the scheduler
    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    public void sendDocumentExpiryNotifications() {
        LocalDate today = LocalDate.now();

        // Get customers whose documents are expiring today
        List<Customer> customersToNotify = customerService.getCustomersWithExpiringDocuments(today);

        for (Customer customer : customersToNotify) {
            // Send notification to the customer
            String notificationMessage = generateNotificationMessage(customer);
            notificationService.sendNotification(customer, notificationMessage);
        }
    }

    private String generateNotificationMessage(Customer customer) {
        // Generate notification message based on the customer's document expiry
        // You can customize the message based on your requirements
        // Example: "Dear Customer, your document [document name] is expiring today. Please renew it."
        // Note: You may need to retrieve the relevant document information from the customer object
        return "Notification message for customer: " + customer.getCustomerName();
    }
}
