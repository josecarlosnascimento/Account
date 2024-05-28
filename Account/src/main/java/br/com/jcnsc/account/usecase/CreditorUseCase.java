package br.com.jcnsc.account.usecase;

import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.jcnsc.account.application.http.exception.NotFoundException;
import br.com.jcnsc.account.application.http.facade.CreditorRequest;
import br.com.jcnsc.account.application.http.facade.CreditorResponse;
import br.com.jcnsc.account.application.http.mapper.CreditorMapper;
import br.com.jcnsc.account.application.repository.CreditorRepository;
import br.com.jcnsc.account.domain.Creditor;

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
    	
      var currentCreditor = creditorRepository.findById(id)
		.orElseThrow(() -> new NotFoundException("Conta não existe."));
      
      	BeanUtils.copyProperties(creditorRequest, currentCreditor);

        Creditor creditorModel =  creditorRepository.save(currentCreditor);
        return creditorMapper.toCreditorResponse(creditorModel);
    }
    
}
