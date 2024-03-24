package com.abcbank.notifications.repository;

import com.abcbank.notifications.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocumentRepository extends JpaRepository<Document, Long> {
}
