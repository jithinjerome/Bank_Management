package com.bank.AccountType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/accType")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping(path = "/getAllType")
    public ResponseEntity<List<Account>> getAllTypes(){
        return accountService.getAllTypes();
    }

    @PostMapping(path = "/addType")
    public ResponseEntity<?> addType(@RequestBody Account account){
        return accountService.addType(account);
    }

    @PostMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id){
         accountService.deleteById(id);
    }

    @GetMapping(path = "/accountType/{accTypeId}")
    public ResponseEntity<List<AccountTypeDTO>> getByAccountType(@PathVariable Long accTypeId){
        return accountService.getByAccountType(accTypeId);
    }

}
