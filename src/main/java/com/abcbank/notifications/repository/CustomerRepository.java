package com.abcbank.notifications.repository;

import com.abcbank.notifications.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

/**
 * Repository interface for Customer entities.
 */
@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>
{
    @Query("SELECT c FROM Customer c WHERE DATE(c.expiryTime) = :specificDate")
    List<Customer> getDocumentsByDateDifference(@Param("specificDate") LocalDate expiryDate);
}
