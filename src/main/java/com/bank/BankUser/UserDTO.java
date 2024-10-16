package com.bank.BankUser;

import lombok.Data;

@Data
public class UserDTO {

    private String name;
    private Long branchId;
    private Long accTypeId;
    private String place;
    private String number;
}
