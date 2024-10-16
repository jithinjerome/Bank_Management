package com.bank.BankUser;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "Name")
    private String name;
    @Column(name = "branchId")
    private Long branchId;
    @Column(name = "branchName")
    private String branchName;
    @Column(name = "accTypeId")
    private Long accTypeId;
    @Column(name = "accType")
    private String accType;
    @Column(name = "Place")
    private String place;
    @Column(name = "Number")
    private String number;
    @Column(name = "accountNumber")
    private String accountNumber;
    @Column(name = "image" ,columnDefinition = "bytea")
    private byte[] image;
}
