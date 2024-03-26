package com.abcbank.notifications.entity;

import com.abcbank.notifications.enums.NotificationType;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class NotificationHistory
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false)
    private Customer customer;

    @Enumerated(EnumType.STRING)
    private NotificationType notificationType;

    private LocalDateTime timestamp;


    public NotificationHistory()
    {
    }


    public NotificationHistory(Customer customer, NotificationType notificationType, LocalDateTime timestamp)
    {
        this.customer = customer;
        this.notificationType = notificationType;
        this.timestamp = timestamp;
    }


    public Long getId()
    {
        return id;
    }


    public void setId(Long id)
    {
        this.id = id;
    }


    public Customer getCustomer()
    {
        return customer;
    }


    public void setCustomer(Customer customer)
    {
        this.customer = customer;
    }


    public NotificationType getNotificationType()
    {
        return notificationType;
    }


    public void setNotificationType(NotificationType notificationType)
    {
        this.notificationType = notificationType;
    }


    public LocalDateTime getTimestamp()
    {
        return timestamp;
    }


    public void setTimestamp(LocalDateTime timestamp)
    {
        this.timestamp = timestamp;
    }
}
