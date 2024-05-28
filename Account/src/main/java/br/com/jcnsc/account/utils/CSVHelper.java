package br.com.jcnsc.account.utils;

import br.com.jcnsc.account.application.http.facade.AccountRequest;
import br.com.jcnsc.account.application.http.facade.CreditorRequest;
import br.com.jcnsc.account.application.repository.CreditorRepository;
import br.com.jcnsc.account.domain.Situation;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class CSVHelper {

	public static String TYPE = "text/csv";

	@Autowired
	private CreditorRepository creditorRepository;

	public static boolean hasCSVFormat(MultipartFile file) {
		if (!TYPE.equals(file.getContentType())) {
			return false;
		}
		return true;
	}

	public List<AccountRequest> csvToAccount(InputStream is) {
		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
				CSVParser csvParser = new CSVParser(fileReader,
						CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) {

			List<AccountRequest> contas = new ArrayList<AccountRequest>();
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {

				if (creditorRepository.existsById(Long.parseLong(csvRecord.get("credor")))) {

					AccountRequest conta = new AccountRequest(LocalDate.parse(csvRecord.get("data_vencimento")),
							LocalDate.parse(csvRecord.get("data_pagamento")), new BigDecimal(csvRecord.get("valor")),
							csvRecord.get("descricao"), Situation.valueOf(csvRecord.get("situacao")),
							new CreditorRequest(Long.parseLong(csvRecord.get("credor")), null, null));

					contas.add(conta);
				}
			}
			return contas;
		} catch (IOException e) {
			throw new RuntimeException("Falha ao ler arquivo CSV: " + e.getMessage());
		}
	}
}