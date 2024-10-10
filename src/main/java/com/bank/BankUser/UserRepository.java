package com.bank.BankUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

public interface UserRepository extends JpaRepository<User , Long> {

}
