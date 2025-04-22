package com.saas_learning.backend.controllers;


import com.saas_learning.backend.dto.TransactionRecord;
import com.saas_learning.backend.entities.Transaction;
import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.repositories.TransactionRepository;
import com.saas_learning.backend.services.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/transactions")
public class TransactionController {

    @Autowired
    private CsvImportService csvImportService;
    @Autowired
    private TransactionRepository transactionRepository;

    @PostMapping("/upload-csv")
    public List<Transaction> uploadTransactions(@RequestParam("file") MultipartFile file,
                                                @AuthenticationPrincipal User user) throws Exception {
        List<TransactionRecord> records = csvImportService.parseCsvFile(file);
        List<Transaction> entities = records.stream()
                .map(record -> csvImportService.toEntity(record, user))
                .collect(Collectors.toList());
        return transactionRepository.saveAll(entities);
    }
}