package br.com.jcnsc.account.application.http.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.jcnsc.account.application.http.facade.AccountRequest;
import br.com.jcnsc.account.application.http.facade.AccountResponse;
import br.com.jcnsc.account.application.http.facade.SumResponse;
import br.com.jcnsc.account.domain.Situation;
import br.com.jcnsc.account.usecase.AccountUseCase;
import br.com.jcnsc.account.usecase.CSVUseCase;
import br.com.jcnsc.account.utils.CSVHelper;
import jakarta.validation.Valid;
import jakarta.websocket.server.PathParam;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountUseCase accountUseCase;

    @Autowired
    private CSVUseCase fileUseCase;

    @GetMapping
    public Page<AccountResponse> findAll(Pageable pageable){
        return accountUseCase.findAll(pageable);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AccountResponse> findById(@PathVariable("id") Long id){
        return ResponseEntity.ok(accountUseCase.findById(id));
    }

    @PostMapping
    public ResponseEntity<AccountResponse> insert(@Valid @RequestBody AccountRequest accountRequest){
        return ResponseEntity.ok(accountUseCase.insert(accountRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AccountResponse> update(@PathVariable("id") Long id,
                                                  @RequestBody AccountRequest accountRequest){
        return ResponseEntity.ok(accountUseCase.update(id, accountRequest));
    }

    @PutMapping("/{id}/situation")
    public ResponseEntity<AccountResponse> updateStatus(@PathVariable("id") Long id,
                                                        @PathParam("situation") Situation situation){
       return ResponseEntity.ok(accountUseCase.updateStatus(id, situation));
    }

    @GetMapping("/sum")
    public ResponseEntity<SumResponse> updateStatus(@PathParam("startDate") LocalDate startDate,
                                                    @PathParam("startEnd") LocalDate startEnd){
        var result = accountUseCase.sumValuePaid(startDate, startEnd);
        return ResponseEntity.ok(new SumResponse(startDate, startEnd, result));
    }

    @GetMapping("/detail")
    public Page<AccountResponse> findByExpireDateAndDescription(Pageable pageable,
                                                                        @PathParam("startDate") LocalDate startDate,
                                                                        @PathParam("endDate")  LocalDate endDate,
                                                                        @PathParam("description") String description){
        return accountUseCase.findByExpireDateAndDescription(pageable, startDate, endDate, description);
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        if (CSVHelper.hasCSVFormat(file)) {
            try {
                fileUseCase.save(file);
                return ResponseEntity.status(HttpStatus.OK).body("Arquivo importado com sucesso.");
            } catch (Exception e) {
                return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body("Falha ao importar arquivo.");
            }
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Falha ao importar arquivo.");
    }
}
