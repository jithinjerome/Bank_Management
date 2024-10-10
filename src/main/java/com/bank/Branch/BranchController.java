package com.bank.Branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/branch")
public class BranchController {

    @Autowired
    private BranchService branchService;

    @GetMapping(path = "/allBranches")
    public ResponseEntity<List<Branch>> getAllBranches(){
        return branchService.getAllBranches();
    }

    @PostMapping(path = "/addBranch")
    public ResponseEntity<?> addBranch(@RequestBody Branch branch){
        return branchService.addBranch(branch);
    }

    @PostMapping(path = "/delete/{id}")
    public void deleteById(@PathVariable Long id)
    {
        branchService.deleteById(id);
    }
}
