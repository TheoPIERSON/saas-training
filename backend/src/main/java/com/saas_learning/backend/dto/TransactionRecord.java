package com.saas_learning.backend.dto;
import com.opencsv.bean.CsvBindByName;
import lombok.Data;


@Data
public class TransactionRecord {

    @CsvBindByName(column = "Date")
    private String date; // à parser en LocalDate ensuite

    @CsvBindByName(column = "Description")
    private String description;

    @CsvBindByName(column = "Montant")
    private double montant;

    @CsvBindByName(column = "Catégorie")
    private String categorie;
}
