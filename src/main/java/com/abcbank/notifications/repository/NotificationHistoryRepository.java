package com.abcbank.notifications.repository;

import com.abcbank.notifications.entity.Customer;
import com.abcbank.notifications.entity.NotificationHistory;
import com.abcbank.notifications.enums.NotificationType;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationHistoryRepository extends JpaRepository<NotificationHistory, Long>
{
    boolean existsByCustomerAndNotificationType(Customer customer, NotificationType notificationType);
}
