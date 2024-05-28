package br.com.jcnsc.account.application.http.mapper;

import br.com.jcnsc.account.application.http.facade.AccountRequest;
import br.com.jcnsc.account.application.http.facade.AccountResponse;
import br.com.jcnsc.account.domain.Account;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.ValueMapping;

@Mapper(componentModel = "spring")
public interface AccountMapper {

    @ValueMapping(source = "situation", target = "situation")
    @Mapping(target = "id", ignore = true)
    Account toAccount(AccountRequest accountRequest);

    @ValueMapping(source = "situation", target = "situation")
    AccountRequest toAccountRequest(Account account);

    AccountResponse toAccountResponse(Account account);

}
