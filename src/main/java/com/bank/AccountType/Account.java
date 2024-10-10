package com.bank.AccountType;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Account_Types")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "id")
    private Long id;
    @Column(name = "accType")
    private String accType;

}
