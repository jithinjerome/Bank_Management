package com.bank.AccountType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<List<Account>> getAllTypes() {
        return  new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> addType(Account account) {
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }
}
