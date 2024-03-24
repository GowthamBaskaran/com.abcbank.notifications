package com.abcbank.notifications.service;

import com.abcbank.notifications.entity.Customer;
import org.springframework.stereotype.Service;

/**
 * Service class for sending notifications to customers.
 */
@Service
public class NotificationService {

    /**
     * Sends a notification to the specified customer.
     *
     * @param customer The customer to whom the notification will be sent.
     * @param message  The message content of the notification.
     */
    public void sendNotification(Customer customer, String message) {
        // Implement logic to send notification to the customer
        // Example: use Firebase Cloud Messaging (FCM) to send push notifications
        // You need to integrate Firebase SDK and set up FCM in your project
        // Example: firebaseClient.sendNotification(customer.getDeviceToken(), message);
        System.out.println("Sending notification to " + customer.getCustomerName() + ": " + message);
    }
}
