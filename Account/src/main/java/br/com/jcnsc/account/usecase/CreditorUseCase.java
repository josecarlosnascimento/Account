package br.com.jcnsc.account.usecase;

import br.com.jcnsc.account.application.http.exception.NotFoundException;
import br.com.jcnsc.account.application.http.facade.AccountResponse;
import br.com.jcnsc.account.application.http.facade.CreditorRequest;
import br.com.jcnsc.account.application.http.facade.CreditorResponse;
import br.com.jcnsc.account.application.http.mapper.CreditorMapper;
import br.com.jcnsc.account.application.repository.CreditorRepository;
import br.com.jcnsc.account.domain.Creditor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CreditorUseCase {

    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private CreditorMapper creditorMapper;

    public Page<CreditorResponse> findAll(Pageable pageable){

        var creditors = creditorRepository.findAll(pageable);
        var creditorResponse = creditors.stream()
                .map(creditor -> creditorMapper.toCreditorResponse(creditor))
                .collect(Collectors.toList());

        return new PageImpl<CreditorResponse>(creditorResponse, pageable, creditorResponse.size());
    }

    public CreditorResponse findById(Long id){

        var creditor = creditorRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Credor não existe."));

        var creditorResponse = creditorMapper.toCreditorResponse(creditor);

        return creditorResponse;
    }

    public CreditorResponse insert(CreditorRequest creditorRequest){

        Creditor creditor = creditorMapper.toCreditor(creditorRequest);
        Creditor creditorModel =  creditorRepository.save(creditor);
        CreditorResponse creditorResponse = creditorMapper.toCreditorResponse(creditorModel);


        return creditorResponse;
    }

    public CreditorResponse update(Long id, CreditorRequest creditorRequest){

        var creditor = creditorMapper.toCreditor(creditorRequest);
        Creditor creditorModel =  creditorRepository.save(creditor);
        var creditorResponse = creditorMapper.toCreditorResponse(creditorModel);


        return creditorResponse;
    }
}
