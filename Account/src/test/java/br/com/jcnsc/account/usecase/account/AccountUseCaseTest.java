package br.com.jcnsc.account.usecase.account;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.Spy;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import br.com.jcnsc.account.application.http.mapper.AccountMapper;
import br.com.jcnsc.account.application.repository.AccountRepository;
import br.com.jcnsc.account.application.repository.CreditorRepository;
import br.com.jcnsc.account.application.repository.impl.AccountRepositoryImpl;
import br.com.jcnsc.account.domain.Account;
import br.com.jcnsc.account.domain.Situation;
import br.com.jcnsc.account.usecase.AccountUseCase;
import br.com.jcnsc.account.usecase.CreditorUseCase;
import br.com.jcnsc.account.usecase.data.AccountData;
import br.com.jcnsc.account.usecase.data.CreditorData;

@SpringBootTest
public class AccountUseCaseTest {

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private AccountRepositoryImpl accountRepositoryImpl;

    @Mock
    private CreditorRepository creditorRepository;

    @Mock
    private CreditorUseCase creditorUseCase;

    @Spy
    private AccountMapper accountMapper = Mappers.getMapper(AccountMapper .class);


    @InjectMocks
    private AccountUseCase accountUseCase;



    @Test
    void mustListAllAccounts(){
        Pageable pageable = PageRequest.of(0, 10);
        Page<Account> pg = new PageImpl<>(AccountData.accounts(), pageable, 10L);

        Mockito.when(accountRepository.findAll(pageable)).thenReturn(pg);
        var result  = accountUseCase.findAll(pageable);

        Assertions.assertEquals(10, result.getSize());
    }

    @Test
    void mustListAccountById(){
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(AccountData.account1()));
        var result  = accountUseCase.findById(1L);
        Assertions.assertEquals(1L, result.id());
    }

    @Test
    void mustInsertAnAccount(){

        var account11 = AccountData.account11();
        var accountRequest = AccountData.accountRequest();

        Mockito.when(creditorRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(CreditorData.creditor1()));
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account11);

        var accountModel = accountUseCase.insert(accountRequest);

        Assertions.assertEquals(11L, accountModel.id());
    }

    @Test
    void mustUpdateAnAccount(){
        var account1 = AccountData.account1();
        var accountRequest = AccountData.accountRequest();

        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        Mockito.when(accountRepository.save(Mockito.any(Account.class))).thenReturn(account1);

        var accountModel = accountUseCase.update(1L, accountRequest);

        Assertions.assertEquals(1L, accountModel.id());
    }

    @Test
    void mustUpdateStatusFromAnAccount(){

        var account1 = AccountData.account1();
        Mockito.when(accountRepository.findById(1L)).thenReturn(Optional.of(account1));
        var accountModel = accountUseCase.updateStatus(1L, Situation.PAGA);
        Assertions.assertEquals(1L, accountModel.id());
    }

    @Test
    void mustSumInPeriod(){

        LocalDate startDate = LocalDate.parse("2023-04-01");
        LocalDate endDate = LocalDate.parse("2024-02-01");

        var sum = AccountData.accounts().stream()
                .filter(a -> a.getPaymentDate().isAfter(startDate))
                .filter(a -> a.getPaymentDate().isBefore(endDate))
                .mapToDouble(a -> a.getValue().doubleValue())
                .sum();
       Assertions.assertEquals(118744.05, sum);
    }

    @Test
    void mustListAccountByDateAndDescription(){

        LocalDate dtStart = LocalDate.parse("2023-01-01");
        LocalDate dtEnd = LocalDate.parse("2023-12-30");
        String desc = "e";
        Pageable pg = Pageable.ofSize(10);

        Mockito.when(accountRepositoryImpl.findByExpireDateAndDescription(dtStart, dtEnd, desc)).thenReturn(AccountData.accountsFilter());
        var result  = accountUseCase.findByExpireDateAndDescription(pg, dtStart, dtEnd, desc);

        Assertions.assertEquals(3L, result.getContent().size());
    }

}
