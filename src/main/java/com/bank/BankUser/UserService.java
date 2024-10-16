package com.bank.BankUser;

import com.bank.AccountType.Account;
import com.bank.AccountType.AccountRepository;
import com.bank.Branch.Branch;
import com.bank.Branch.BranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BranchRepository branchRepository;

    @Autowired
    private AccountRepository accountRepository;


    public ResponseEntity<?> register(UserDTO userDTO,MultipartFile image) {
        User user1 = new User();
        Long branchId = userDTO.getBranchId();
        Long accTypeId = userDTO.getAccTypeId();
        String name = userDTO.getName();
        String place = userDTO.getPlace();
        String number = userDTO.getNumber();

        Optional<Branch> branchOptional = branchRepository.findById(branchId);
        Optional<Account> accountOptional = accountRepository.findById(accTypeId);

        if(branchOptional.isPresent() && accountOptional.isPresent()){
            Account account = accountOptional.get();
            Branch branch = branchOptional.get();
            user1.setAccTypeId(accTypeId);
            user1.setAccType(account.getAccType());
            user1.setBranchId(branchId);
            user1.setBranchName(branch.getBranchName());
            user1.setName(name);
            user1.setPlace(place);
            user1.setNumber(number);
            user1.setAccountNumber(generateUniqueNumber());

            String imageStatus;
            try{
                if(!image.isEmpty()){
                    user1.setImage(image.getBytes());
                    imageStatus = "Image Uploaded Successfully";
                }else{
                    imageStatus = "No Image Uploaded";
                    return new ResponseEntity<>("Image is required",HttpStatus.BAD_REQUEST);
                }

            }catch (IOException e){
                return new ResponseEntity<>("Error saving photo "+e.getMessage(),HttpStatus.BAD_REQUEST);
            }
            userRepository.save(user1);

            UserResponseDTO userResponseDTO = new UserResponseDTO(user1.getId(),
                    user1.getName(),
                    user1.getBranchId(),
                    user1.getBranchName(),
                    user1.getAccTypeId(),
                    user1.getAccType(),
                    user1.getPlace(),
                    user1.getNumber(),
                    user1.getAccountNumber(),
                    imageStatus);


            return  new ResponseEntity<>(userResponseDTO, HttpStatus.OK);
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
