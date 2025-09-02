package com.example.bank.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankTransactionId;

    private Long bankTransactionFromAccount;
    private Long bankTransactionToAccount;
    private Double amount;
    private LocalDateTime transactionDate;

    // Constructors
    public BankTransaction() {
        this.transactionDate = LocalDateTime.now();
    }

    // Getters & Setters
    public Long getBankTransactionId() { return bankTransactionId; }
    public void setBankTransactionId(Long bankTransactionId) { this.bankTransactionId = bankTransactionId; }

    public Long getBankTransactionFromAccount() { return bankTransactionFromAccount; }
    public void setBankTransactionFromAccount(Long bankTransactionFromAccount) { this.bankTransactionFromAccount = bankTransactionFromAccount; }

    public Long getBankTransactionToAccount() { return bankTransactionToAccount; }
    public void setBankTransactionToAccount(Long bankTransactionToAccount) { this.bankTransactionToAccount = bankTransactionToAccount; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getTransactionDate() { return transactionDate; }
    public void setTransactionDate(LocalDateTime transactionDate) { this.transactionDate = transactionDate; }
}
