package com.abcbank.notifications.service;

import com.abcbank.notifications.entity.Customer;
import com.abcbank.notifications.enums.NotificationType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class NotificationSchedulerService
{

    @Autowired
    private CustomerService customerService;

    @Autowired
    private NotificationService notificationService;


    // Method to be executed by the scheduler
    @Scheduled(cron = "0 0 0 * * *") // Runs every day at midnight
    public void sendDocumentExpiryNotifications()
    {
        sendDocumentExpiryNotificationsBefore();
        sendDocumentExpiryNotificationsAfter();
        sendDocumentExpiryNotificationsAfterGracePeriod();
    }


    private void sendDocumentExpiryNotificationsAfterGracePeriod()
    {
        // Calculate the current date
        LocalDate currentDate = LocalDate.now();
        LocalDate tMinus90 = currentDate.minusDays(90); // T-90
        // Query customers whose documents are about to expire within each interval
        List<Customer> customersTMinus90 = customerService.getCustomersWithExpiringDocuments(tMinus90);
        notificationService.sendNotification(customersTMinus90, 90, NotificationType.AFTER_GRACE_PERIOD_DEBIT_FREEZE);
    }


    private void sendDocumentExpiryNotificationsAfter()
    {
        // Calculate the current date
        LocalDate currentDate = LocalDate.now();

        LocalDate tMinus60 = currentDate.minusDays(60); // T-60
        LocalDate tMinus30 = currentDate.minusDays(30); // T-30
        LocalDate tMinus1 = currentDate.minusDays(1); // T-1

        List<Customer> customersTMinus60 = customerService.getCustomersWithExpiringDocuments(tMinus60);
        List<Customer> customersTMinus30 = customerService.getCustomersWithExpiringDocuments(tMinus30);
        List<Customer> customersTMinus1 = customerService.getCustomersWithExpiringDocuments(tMinus1);

        notificationService.sendNotification(customersTMinus60, 60, NotificationType.AFTER_EXPIRY_GRACE_PERIOD);
        notificationService.sendNotification(customersTMinus30, 30, NotificationType.AFTER_EXPIRY_GRACE_PERIOD);
        notificationService.sendNotification(customersTMinus1, 1, NotificationType.AFTER_EXPIRY_GRACE_PERIOD);

    }


    private void sendDocumentExpiryNotificationsBefore()
    {
        // Calculate the current date
        LocalDate currentDate = LocalDate.now();
        LocalDate expiryDate = currentDate; // T
        LocalDate tPlus30 = currentDate.plusDays(30); // T +30
        LocalDate tPlus60 = currentDate.plusDays(60); // T +30

        List<Customer> customersExpiryDate = customerService.getCustomersWithExpiringDocuments(expiryDate);
        List<Customer> customersTPlus30 = customerService.getCustomersWithExpiringDocuments(tPlus30);
        List<Customer> customersTPlus60 = customerService.getCustomersWithExpiringDocuments(tPlus60);
        notificationService.sendNotification(customersExpiryDate, 0, NotificationType.AFTER_GRACE_PERIOD_DEBIT_FREEZE);
        notificationService.sendNotification(customersTPlus30, 0, NotificationType.AFTER_GRACE_PERIOD_DEBIT_FREEZE);
        notificationService.sendNotification(customersTPlus60, 0, NotificationType.AFTER_GRACE_PERIOD_DEBIT_FREEZE);
    }
}
