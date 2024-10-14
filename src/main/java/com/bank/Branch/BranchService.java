package com.bank.Branch;

import com.bank.AccountType.Account;
import com.bank.AccountType.AccountRepository;
import com.bank.AccountType.AccountTypeDTO;
import com.bank.BankUser.User;
import com.bank.BankUser.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccountRepository accountRepository;

    public ResponseEntity<List<Branch>> getAllBranches() {
        return new ResponseEntity<>(branchRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> addBranch(Branch branch) {
        return new ResponseEntity<>(branchRepository.save(branch), HttpStatus.OK);
    }

    public void deleteById(Long id) {
        branchRepository.deleteById(id);
    }

    public ResponseEntity<List<AccountTypeDTO>> getByBranchName(Long branchId) {
        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        List<AccountTypeDTO> branchNames = new ArrayList<>();

        if(branchOptional.isPresent())
        {
            List<User> userList = userRepository.findByBranchId(branchId);
            if(!userList.isEmpty()){
                Branch branch = branchOptional.get();
                for(User users: userList){
                    Optional<Account> accountOptional = accountRepository.findById(users.getAccTypeId());
                    if(accountOptional.isPresent()){
                        Account account = accountOptional.get();
                        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
                        accountTypeDTO.setName(users.getName());
                        accountTypeDTO.setAccType(account.getAccType());
                        accountTypeDTO.setBranchName(branch.getBranchName());
                        branchNames.add(accountTypeDTO);
                    }
                }
                return new ResponseEntity<>(branchNames,HttpStatus.OK);
            }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
