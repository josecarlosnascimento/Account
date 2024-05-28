package br.com.jcnsc.account.usecase;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import br.com.jcnsc.account.application.http.facade.AccountRequest;
import br.com.jcnsc.account.application.http.mapper.AccountMapper;
import br.com.jcnsc.account.application.repository.AccountRepository;
import br.com.jcnsc.account.utils.CSVHelper;

@Service
public class CSVUseCase {

  @Autowired
  private AccountRepository accountRepository;

  @Autowired
  private AccountMapper accountMapper;

  @Autowired
  private CSVHelper CSVHelper;

  public void save(MultipartFile file) {
    try {
      List<AccountRequest> contasReq = CSVHelper.csvToAccount(file.getInputStream());

     contasReq.stream()
              .map(account -> accountMapper.toAccount(account))
              .forEach(account -> accountRepository.save(account));


    } catch (IOException e) {
      throw new RuntimeException("Falha ao gravar dados via CSV: " + e.getMessage());
    }
  }
}