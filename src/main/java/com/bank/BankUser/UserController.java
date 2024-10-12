package com.bank.BankUser;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/user")
public class UserController {

    @Autowired
    private UserService userService;


    @PostMapping(path = "/register")
    public ResponseEntity<?> register(@RequestParam Long branchId,@RequestParam Long accTypeId, @RequestBody User user){
        return userService.register(branchId,accTypeId,user);
    }

}
