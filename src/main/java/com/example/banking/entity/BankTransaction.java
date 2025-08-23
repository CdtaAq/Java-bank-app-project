package com.example.banking.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class BankTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bankTransactionId;

    // for transfers and withdrawals (sender)
    private Long bankTransactionFromAccount;

    // for deposits and transfers (receiver)
    private Long bankTransactionToAccount;

    private Double bankTransactionAmount;

    private String bankTransactionType; // Deposit, Withdrawal, Transfer
}
