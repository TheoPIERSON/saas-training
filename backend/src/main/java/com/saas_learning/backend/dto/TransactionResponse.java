package com.saas_learning.backend.dto;

import com.saas_learning.backend.entities.Transaction;

public record TransactionResponse(
        String date,
        String description,
        double montant,
        String categorie
) {
    public static TransactionResponse from(Transaction t) {
        return new TransactionResponse(
                t.getDate().toString(),
                t.getDescription(),
                t.getMontant(),
                t.getCategorie()
        );
    }
}
