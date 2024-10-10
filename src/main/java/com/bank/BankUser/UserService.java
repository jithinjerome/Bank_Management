package com.bank.BankUser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public ResponseEntity<?> register(User user) {
        userRepository.save(user);
        return  new ResponseEntity<>("Registered Successfully", HttpStatus.OK);
    }
}
