package br.com.jcnsc.account.application.http.mapper;

import br.com.jcnsc.account.application.http.facade.CreditorRequest;
import br.com.jcnsc.account.application.http.facade.CreditorResponse;
import br.com.jcnsc.account.domain.Creditor;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CreditorMapper {

    @Mapping(target = "name", source = "name")
    @Mapping(target = "cpfCnpj", source = "cpfCnpj")
    Creditor toCreditor(CreditorRequest creditorRequest);

    CreditorRequest toCreditorRequest(Creditor creditor);

    CreditorResponse toCreditorResponse(Creditor creditor);

}
