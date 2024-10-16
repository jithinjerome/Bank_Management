package com.bank.BankUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(path = "/register", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> register(@RequestPart("userDTO") UserDTO userDTO,
                                      @RequestPart("image")MultipartFile image){
        try{
            return userService.register(userDTO,image);
        }catch (Exception e){
            return new ResponseEntity<>("Error during registration "+e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
