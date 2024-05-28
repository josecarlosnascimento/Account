package br.com.jcnsc.account.usecase.data;

import br.com.jcnsc.account.application.http.facade.AccountRequest;
import br.com.jcnsc.account.application.http.facade.AccountResponse;
import br.com.jcnsc.account.domain.Account;
import br.com.jcnsc.account.domain.Creditor;
import br.com.jcnsc.account.domain.Situation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class AccountData {

    public static List<Account> accounts() {
        Account account1 = new Account();
        account1.setId(1L);
        account1.setExpireDate(LocalDate.parse("2025-08-13"));
        account1.setPaymentDate(LocalDate.parse("2023-05-01"));
        account1.setValue(new BigDecimal("10092.46"));
        account1.setDescription("Compatible hybrid infrastructure");
        account1.setSituation(Situation.valueOf("PENDENTE"));

        Account account2 = new Account();
        account2.setId(2L);
        account2.setExpireDate(LocalDate.parse("2024-07-07"));
        account2.setPaymentDate(LocalDate.parse("2023-12-24"));
        account2.setValue(new BigDecimal("28948.07"));
        account2.setDescription("Advanced multi-tasking flexibility");
        account2.setSituation(Situation.valueOf("PAGA"));

        Account account3 = new Account();
        account3.setId(3L);
        account3.setExpireDate(LocalDate.parse("2025-07-11"));
        account3.setPaymentDate(LocalDate.parse("2023-02-20"));
        account3.setValue(new BigDecimal("27257.99"));
        account3.setDescription("Persevering foreground open system");
        account3.setSituation(Situation.valueOf("PAGA"));

        Account account4 = new Account();
        account4.setId(4L);
        account4.setExpireDate(LocalDate.parse("2024-05-24"));
        account4.setPaymentDate(LocalDate.parse("2023-08-04"));
        account4.setValue(new BigDecimal("38677.93"));
        account4.setDescription("Configurable exuding portal");
        account4.setSituation(Situation.valueOf("PENDENTE"));

        Account account5 = new Account();
        account5.setId(5L);
        account5.setExpireDate(LocalDate.parse("2025-11-22"));
        account5.setPaymentDate(LocalDate.parse("2023-07-04"));
        account5.setValue(new BigDecimal("13910.87"));
        account5.setDescription("Proactive user-facing framework");
        account5.setSituation(Situation.valueOf("PENDENTE"));

        Account account6 = new Account();
        account6.setId(6L);
        account6.setExpireDate(LocalDate.parse("2025-01-02"));
        account6.setPaymentDate(LocalDate.parse("2023-03-08"));
        account6.setValue(new BigDecimal("18153.56"));
        account6.setDescription("Object-based radical infrastructure");
        account6.setSituation(Situation.valueOf("PENDENTE"));

        Account account7 = new Account();
        account7.setId(7L);
        account7.setExpireDate(LocalDate.parse("2025-05-01"));
        account7.setPaymentDate(LocalDate.parse("2023-06-08"));
        account7.setValue(new BigDecimal("24674.97"));
        account7.setDescription("User-centric foreground success");
        account7.setSituation(Situation.valueOf("PAGA"));

        Account account8 = new Account();
        account8.setId(8L);
        account8.setExpireDate(LocalDate.parse("2025-04-09"));
        account8.setPaymentDate(LocalDate.parse("2023-12-04"));
        account8.setValue(new BigDecimal("2439.75"));
        account8.setDescription("Proactive secondary toolset");
        account8.setSituation(Situation.valueOf("PAGA"));

        Account account9 = new Account();
        account9.setId(9L);
        account9.setExpireDate(LocalDate.parse("2025-02-15"));
        account9.setPaymentDate(LocalDate.parse("2023-03-21"));
        account9.setValue(new BigDecimal("54671.10"));
        account9.setDescription("Extended coherent system engine");
        account9.setSituation(Situation.valueOf("PENDENTE"));

        Account account10 = new Account();
        account10.setId(10L);
        account10.setExpireDate(LocalDate.parse("2025-07-12"));
        account10.setPaymentDate(LocalDate.parse("2023-01-20"));
        account10.setValue(new BigDecimal("20255.93"));
        account10.setDescription("Persevering background info-mediaries");
        account10.setSituation(Situation.valueOf("PAGA"));

        return List.of(account1, account2, account3, account4, account5, account6, account7, account8, account9, account10);
    }

    public static List<Account> accountsFilter() {
        Account account1 = new Account();
        account1.setId(1L);
        account1.setExpireDate(LocalDate.parse("2025-08-13"));
        account1.setPaymentDate(LocalDate.parse("2023-05-01"));
        account1.setValue(new BigDecimal("10092.46"));
        account1.setDescription("Compatible hybrid infrastructure");
        account1.setSituation(Situation.valueOf("PENDENTE"));

        Account account2 = new Account();
        account2.setId(2L);
        account2.setExpireDate(LocalDate.parse("2024-07-07"));
        account2.setPaymentDate(LocalDate.parse("2023-12-24"));
        account2.setValue(new BigDecimal("28948.07"));
        account2.setDescription("Advanced multi-tasking flexibility");
        account2.setSituation(Situation.valueOf("PAGA"));

        Account account3 = new Account();
        account3.setId(3L);
        account3.setExpireDate(LocalDate.parse("2025-07-11"));
        account3.setPaymentDate(LocalDate.parse("2023-02-20"));
        account3.setValue(new BigDecimal("27257.99"));
        account3.setDescription("Persevering foreground open system");
        account3.setSituation(Situation.valueOf("PAGA"));

        return List.of(account1, account2, account3);

    }

    public static Account account1() {
        Account account1 = new Account();
        account1.setId(1L);
        account1.setExpireDate(LocalDate.parse("2025-08-13"));
        account1.setPaymentDate(LocalDate.parse("2023-05-01"));
        account1.setValue(new BigDecimal("10092.46"));
        account1.setDescription("Compatible hybrid infrastructure");
        account1.setSituation(Situation.valueOf("PENDENTE"));
        account1.setCreditor(CreditorData.creditor1());
        return account1;
    }

    public static Account account11() {
        Account account11 = new Account();
        account11.setId(11L);
        account11.setExpireDate(LocalDate.parse("2025-08-13"));
        account11.setPaymentDate(LocalDate.parse("2023-05-01"));
        account11.setValue(new BigDecimal("10092.46"));
        account11.setDescription("There is something here");
        account11.setSituation(Situation.valueOf("PENDENTE"));
        account11.setCreditor(CreditorData.creditor1());
        return account11;
    }

    public static AccountRequest accountRequest() {
        AccountRequest account = new AccountRequest(LocalDate.parse("2024-01-11"),
                LocalDate.parse("2023-05-01"),
                new BigDecimal("10092.46"),
                "There is something here",
                Situation.valueOf("PENDENTE"),
                CreditorData.creditorRequest1());

        return account;
    }
}
