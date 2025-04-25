package com.saas_learning.backend.services;

import com.saas_learning.backend.dto.TransactionRecord;
import com.saas_learning.backend.entities.Transaction;
import com.saas_learning.backend.entities.User;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class CsvImportService {

    public List<TransactionRecord> parseCsvFile(MultipartFile file) throws Exception {
        List<TransactionRecord> records = new ArrayList<>();

        try (
                InputStreamReader reader = new InputStreamReader(file.getInputStream(), StandardCharsets.ISO_8859_1); // Encodage CIC
                CSVReader csvReader = new CSVReaderBuilder(reader).withSkipLines(1).build() // Skip header
        ) {
            String[] line;
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

            while ((line = csvReader.readNext()) != null) {
                String rawDate = line[0];
                String rawMontant = line[2].replace(",", ".").replace(" ", ""); // Nettoyer les espaces et remplacer virgule
                String description = line[3];

                TransactionRecord record = new TransactionRecord();
                record.setDate(LocalDate.parse(rawDate, formatter).toString());
                record.setMontant(new BigDecimal(rawMontant));
                record.setDescription(description);
                record.setCategorie("Autre"); // Tu peux implémenter une logique de détection ici

                records.add(record);
            }
        }

        return records;
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
