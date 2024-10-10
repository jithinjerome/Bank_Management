package com.bank.Branch;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BranchService {

    @Autowired
    private BranchRepository branchRepository;

    public ResponseEntity<List<Branch>> getAllBranches() {
        return new ResponseEntity<>(branchRepository.findAll(), HttpStatus.OK);
    }

    public ResponseEntity<?> addBranch(Branch branch) {
        return new ResponseEntity<>(branchRepository.save(branch), HttpStatus.OK);
    }

    public void deleteById(Long id) {
        branchRepository.deleteById(id);
    }
}
