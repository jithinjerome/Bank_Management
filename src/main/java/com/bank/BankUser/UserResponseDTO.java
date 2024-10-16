package com.bank.BankUser;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {

    private Long id;
    private String name;
    private Long branchId;
    private String branchName;
    private Long accTypeId;
    private String accType;
    private String place;
    private String number;
    private String accountNumber;
    private String imageStatus;

}
