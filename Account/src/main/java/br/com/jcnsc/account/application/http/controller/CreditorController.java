package br.com.jcnsc.account.application.http.controller;


import br.com.jcnsc.account.application.http.facade.CreditorRequest;
import br.com.jcnsc.account.application.http.facade.CreditorResponse;

import br.com.jcnsc.account.usecase.CreditorUseCase;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/creditor")
public class CreditorController {

    @Autowired
    private CreditorUseCase creditorUseCase;

    @GetMapping
    public Page<CreditorResponse> findAll(Pageable pageable){
        return creditorUseCase.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CreditorResponse> findById(@PathVariable Long id){
        return ResponseEntity.ok(creditorUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<CreditorResponse> insert(@Valid @RequestBody CreditorRequest creditorRequest){
       return ResponseEntity.ok(creditorUseCase.insert(creditorRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CreditorResponse> update(@PathVariable Long id,
                                                   @RequestBody CreditorRequest creditorRequest){
        return ResponseEntity.ok(creditorUseCase.update(id, creditorRequest));
    }
}
