package com.bank.BankUser;

import com.bank.AccountType.Account;
import com.bank.AccountType.AccountRepository;
import com.bank.Branch.Branch;
import com.bank.Branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<?> register(Long branchId,Long accTypeId,User user) {
        User user1 = new User();
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        Optional<Account> accountOptional = accountRepository.findById(accTypeId);

        if(branchOptional.isPresent() && accountOptional.isPresent()){
            Account account = accountOptional.get();
            Branch branch = branchOptional.get();
            user1.setAccTypeId(accTypeId);
            user1.setAccType(account.getAccType());
            user1.setBranchId(branchId);
            user1.setBranchName(branch.getBranchName());
            user1.setName(user.getName());
            user1.setPlace(user.getPlace());
            user1.setNumber(user.getNumber());
            user1.setAccountNumber(generateUniqueNumber());
            userRepository.save(user1);
            return  new ResponseEntity<>(user1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>("Branch or Account Type is invalid!",HttpStatus.BAD_REQUEST);
        }

    }

    private String generateUniqueNumber() {
        String accountNumber;
        boolean exists;
        do{
            accountNumber = generateAccountNumber();
            exists = userRepository.existsByAccountNumber(accountNumber);

        }while(exists);
        return accountNumber;
    }

    private String generateAccountNumber() {
        long number = (long)(Math.random() * 9_000_000_000L) + 1_000_000_000L;
        return String.valueOf(number);
    }
}
