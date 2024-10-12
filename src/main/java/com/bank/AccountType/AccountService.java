package com.bank.AccountType;

import com.bank.BankUser.User;
import com.bank.BankUser.UserRepository;
import com.bank.Branch.Branch;
import com.bank.Branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    public ResponseEntity<List<Account>> getAllTypes() {
        return  new ResponseEntity<>(accountRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> addType(Account account) {
        return new ResponseEntity<>(accountRepository.save(account), HttpStatus.OK);
    }

    public void deleteById(Long id) {
        accountRepository.deleteById(id);
    }

    public ResponseEntity<List<AccountTypeDTO>> getByAccountType(Long accTypeId) {
        Optional<Account> accountOptional = accountRepository.findById(accTypeId);
        List<AccountTypeDTO> accountTypes = new ArrayList<>();

        if(accountOptional.isPresent()){
            List<User> userList = userRepository.findByAccTypeId(accTypeId);
            if(!userList.isEmpty()){
                Account account = accountOptional.get();
                for(User users: userList){
                    Optional<Branch> branchOptional = branchRepository.findById(users.getBranchId());
                    if(branchOptional.isPresent()){
                        Branch branch = branchOptional.get();
                        AccountTypeDTO accountTypeDTO = new AccountTypeDTO();
                        accountTypeDTO.setAccType(account.getAccType());
                        accountTypeDTO.setName(users.getName());
                        accountTypeDTO.setBranchName(branch.getBranchName());
                        accountTypes.add(accountTypeDTO);
                    }
                    //Account account = accountOptional.get();

                }
                return new ResponseEntity<>(accountTypes,HttpStatus.OK);

            }
            return new ResponseEntity<>(new ArrayList<>(),HttpStatus.NO_CONTENT);

        }
        return new ResponseEntity<>(new ArrayList<>(),HttpStatus.BAD_REQUEST);
    }
}
