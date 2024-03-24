package com.abcbank.notifications.controller;

import com.abcbank.notifications.entity.Document;
import com.abcbank.notifications.service.DocumentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/documents")
public class DocumentController {

    @Autowired
    private DocumentService documentService;

    public List<Document> getAllDocuments() {
        return documentService.getAllDocuments();
    }
}
