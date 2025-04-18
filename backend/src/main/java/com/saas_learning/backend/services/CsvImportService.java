package com.saas_learning.backend.services;

import com.opencsv.bean.CsvToBeanBuilder;
import com.saas_learning.backend.dto.TransactionRecord;
import com.saas_learning.backend.entities.Transaction;
import com.saas_learning.backend.entities.User;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.List;

@Service
public class CsvImportService {

    public List<TransactionRecord> parseCsvFile(MultipartFile file) throws Exception {
        return new CsvToBeanBuilder<TransactionRecord>(
                new InputStreamReader(file.getInputStream()))
                .withType(TransactionRecord.class)
                .withIgnoreLeadingWhiteSpace(true)
                .build()
                .parse();
    }

    public Transaction toEntity(TransactionRecord record, User user) {
        return Transaction.builder()
                .description(record.getDescription())
                .montant(record.getMontant())
                .date(LocalDate.parse(record.getDate()))
                .categorie(record.getCategorie())
                .user(user)
                .build();
    }

}
