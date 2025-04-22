package com.saas_learning.backend.controllers;

import com.saas_learning.backend.dto.TransactionRecord;
import com.saas_learning.backend.dto.TransactionResponse;
import com.saas_learning.backend.entities.Transaction;
import com.saas_learning.backend.entities.User;
import com.saas_learning.backend.repositories.TransactionRepository;
import com.saas_learning.backend.services.CsvImportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
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
    public ResponseEntity<?> uploadTransactions(@RequestParam("file") MultipartFile file,
                                                @AuthenticationPrincipal User user) {
        try {
            // Vérification du fichier
            if (file.isEmpty()) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Le fichier est vide.");
            }

            if (!file.getOriginalFilename().endsWith(".csv")) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body("Le fichier doit être au format CSV.");
            }

            // Parsing et enregistrement
            List<TransactionRecord> records = csvImportService.parseCsvFile(file);
            List<Transaction> entities = records.stream()
                    .map(record -> csvImportService.toEntity(record, user))
                    .collect(Collectors.toList());

            List<Transaction> saved = transactionRepository.saveAll(entities);

            // Mapping vers DTOs de retour
            List<TransactionResponse> response = saved.stream()
                    .map(tx -> new TransactionResponse(
                            tx.getDescription(),
                            tx.getDate().toString(),
                            tx.getMontant(),
                            tx.getCategorie()
                    )).collect(Collectors.toList());

            return ResponseEntity.ok(response);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Erreur lors de l'import : " + e.getMessage());
        }
    }
}
