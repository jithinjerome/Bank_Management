package com.bank.Branch;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "Branches")
public class Branch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String branchName;
}
