package com.abcbank.notifications.service;

import com.abcbank.notifications.entity.Customer;
import com.abcbank.notifications.entity.NotificationHistory;
import com.abcbank.notifications.enums.NotificationType;
import com.abcbank.notifications.repository.NotificationHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for sending notifications to customers.
 */
@Service
public class NotificationService
{

    // Kafka topic to which notifications will be published
    private static final String TOPIC = "push-notifications";

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final NotificationHistoryRepository notificationHistoryRepository;


    @Autowired
    public NotificationService(KafkaTemplate<String, String> kafkaTemplate, NotificationHistoryRepository notificationHistoryRepository)
    {
        this.kafkaTemplate = kafkaTemplate;
        this.notificationHistoryRepository = notificationHistoryRepository;
    }


    public void sendNotification(List<Customer> customers, int gracePeriod, NotificationType notificationType)
    {
        LocalDateTime timestamp = LocalDateTime.now();

        for (Customer customer : customers)
        {
            if (!notificationHistoryRepository.existsByCustomerAndNotificationType(customer, notificationType))
            {
                String notificationMessage = constructNotification(customer, gracePeriod, notificationType);
                kafkaTemplate.send(TOPIC, notificationMessage);

                NotificationHistory notificationHistory = new NotificationHistory(customer, notificationType, timestamp);
                notificationHistoryRepository.save(notificationHistory);
            }
        }
    }


    public String constructNotification(Customer customer, int gracePeriod, NotificationType notificationType)
    {
        StringBuilder notificationContent = new StringBuilder();

        switch (notificationType)
        {
            case BEFORE_EXPIRY:
                notificationContent.append("Title: Document Expiry:\n\n");
                notificationContent.append("Description: Dear ").append(customer.getCustomerName());
                notificationContent.append(", we've noticed that your documents will soon expire. ");
                notificationContent.append("To keep your records up to date and continue enjoying uninterrupted services, ");
                notificationContent.append("please check your registered email address with EDB for details. ");
                notificationContent.append("For further assistance, you can contact us at Business.Banking@edb.gov.ae ");
                notificationContent.append("or call 600-55-1216. If you've already updated your documents with us, ");
                notificationContent.append("kindly disregard this message.");
                break;

            case AFTER_EXPIRY_GRACE_PERIOD:
                notificationContent.append("Title: Grace period\n\n");
                notificationContent.append("Description: Dear ").append(customer.getCustomerName());
                notificationContent.append(", according to our records, some of your documents have expired. ");
                notificationContent.append("To continue with uninterrupted services, please check your registered EDB email ");
                notificationContent.append("for document details. Failure to submit documents may result in disabling outward ");
                notificationContent.append("transactions within ").append(gracePeriod).append(" days. For assistance, contact us at Business.Banking@edb.gov.ae ");
                notificationContent.append("or 600-55-1216. If you’ve already updated your documents with us, ");
                notificationContent.append("kindly disregard this message.");
                break;

            case AFTER_GRACE_PERIOD_DEBIT_FREEZE:
                notificationContent.append("Title: Debit Freeze\n\n");
                notificationContent.append("Description: Dear ").append(customer.getCustomerName());
                notificationContent.append(", please note that your documents have expired. Consequently, ");
                notificationContent.append("all outward transactions and mobile app access has been disabled temporarily. ");
                notificationContent.append("To reactivate the services, kindly refer to the email sent to your registered email ");
                notificationContent.append("address with us for. For further assistance contact us on Business.Banking@edb.gov.ae ");
                notificationContent.append("and 600-55-1216. If you've already updated your documents with us, kindly disregard this message.");
                break;

            default:
                // Handle unknown notification type
                break;
        }

        return notificationContent.toString();
    }
}
