package br.com.jcnsc.account.usecase.data;

import java.util.List;

import br.com.jcnsc.account.application.http.facade.CreditorRequest;
import br.com.jcnsc.account.domain.Creditor;

public class CreditorData {

    public static List<Creditor> creditors() {
        Creditor creditor1 = new Creditor();
        creditor1.setId(1L);

        Creditor creditor2 = new Creditor();
        creditor2.setId(2L);

        Creditor creditor3 = new Creditor();
        creditor3.setId(3L);

        Creditor creditor4 = new Creditor();
        creditor4.setId(4L);

        Creditor creditor5 = new Creditor();
        creditor5.setId(5L);


        return List.of(creditor1, creditor2, creditor3, creditor4, creditor5);
    }

    public static Creditor creditor1() {
        Creditor creditor1 = new Creditor();
        creditor1.setId(1L);

        return creditor1;
    }

    public static CreditorRequest creditorRequest1() {
        return new CreditorRequest(1L, "Johanes Pitt", "10199928277");
    }
}
