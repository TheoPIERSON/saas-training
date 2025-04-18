package com.saas_learning.backend.repositories;

import com.saas_learning.backend.entities.Transaction;
import com.saas_learning.backend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findAllByUser(User user);
}

