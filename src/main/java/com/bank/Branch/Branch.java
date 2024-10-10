package com.bank.Branch;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name = "branchName")
    private String branchName;
    @Column(name = "Phone")
    private String phn;
}
