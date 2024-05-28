package br.com.jcnsc.account.usecase;

import br.com.jcnsc.account.application.http.exception.DataInvalidaException;
import br.com.jcnsc.account.application.http.exception.DataNulaException;
import br.com.jcnsc.account.application.http.exception.NotFoundException;
import br.com.jcnsc.account.application.http.facade.AccountRequest;
import br.com.jcnsc.account.application.http.facade.AccountResponse;
import br.com.jcnsc.account.application.http.mapper.AccountMapper;
import br.com.jcnsc.account.application.repository.AccountRepository;
import br.com.jcnsc.account.application.repository.CreditorRepository;
import br.com.jcnsc.account.application.repository.impl.AccountRepositoryImpl;
import br.com.jcnsc.account.domain.Account;
import br.com.jcnsc.account.domain.Situation;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.stream.Collectors;

@Service
public class AccountUseCase {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRepositoryImpl accountRepositoryImpl;

    @Autowired
    private CreditorRepository creditorRepository;

    @Autowired
    private AccountMapper accountMapper;

    public Page<AccountResponse> findAll(Pageable pageable){
        var accounts = accountRepository.findAll(pageable);
        var accountResponses = accounts.stream()
                .map(account -> accountMapper.toAccountResponse(account))
                .collect(Collectors.toList());
        return new PageImpl<AccountResponse>(accountResponses, pageable, accountResponses.size());
    }

    public AccountResponse findById(Long id){

        var account = accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("Conta não existe."));

        var accountResponse = accountMapper.toAccountResponse(account);

        return accountResponse;
    }

    public AccountResponse insert(AccountRequest accountRequest){

        var creditor = creditorRepository.findById(accountRequest.creditor().id())
        		.orElseThrow(() -> new NotFoundException("Credor não existe."));
        
        var account = accountMapper.toAccount(accountRequest);
        account.setCreditor(creditor);
        var accountModel = accountRepository.save(account);
        var accountResponse = accountMapper.toAccountResponse(accountModel);

        return accountResponse;
    }

    public AccountResponse update(Long id,  AccountRequest accountRequest)  {

        var currentAccount = accountRepository.findById(id)
        		.orElseThrow(() -> new NotFoundException("Conta não existe."));

            BeanUtils.copyProperties(accountRequest, currentAccount);

            Account accountModel =  accountRepository.save(currentAccount);
            var accountResponse = accountMapper.toAccountResponse(accountModel);
            return accountResponse;
    }

    @Transactional
    public AccountResponse updateStatus(Long id, Situation situation)  {

        var currentAccount = accountRepository.findById(id)
        		.orElseThrow(() -> new NotFoundException("Conta não existe."));

            if(situation.name().equals(Situation.PAGA.name())){
                accountRepository.updateToPaga(id);
            }else {
                accountRepository.updateToPendente(id);
            }

            return accountMapper.toAccountResponse(currentAccount);
    }

    public BigDecimal sumValuePaid(LocalDate dtIni, LocalDate dtFim){
        return accountRepository.sumValuePaid(dtIni, dtFim);
    }

    public Page<AccountResponse>  findByExpireDateAndDescription(Pageable pageable,
                                                                 LocalDate startDate,
                                                                 LocalDate endDate,
                                                                 String description){

        if(startDate == null || endDate == null){
            throw new DataNulaException("Preencha a data de inicio e de fim.");
        } else if (startDate.isAfter(endDate)) {
            throw new DataInvalidaException("A data fim não pode ser menor que a data fim.");
        }

        var accounts = accountRepositoryImpl.findByExpireDateAndDescription(startDate, endDate, description);

        var accountResponses = accounts.stream()
                .map(account -> accountMapper.toAccountResponse(account))
                .collect(Collectors.toList());

        return new PageImpl<AccountResponse>(accountResponses, pageable, accountResponses.size());
    }
}
