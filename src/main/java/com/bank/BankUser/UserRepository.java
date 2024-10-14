package com.bank.BankUser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserRepository extends JpaRepository<User , Long> {

    List<User> findByAccTypeId(Long accTypeId);

    List<User> findByBranchId(Long branchId);
}
