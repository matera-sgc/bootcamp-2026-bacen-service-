package com.matera.bootcamp26;

import com.matera.bootcamp26.model.entity.Conta;
import com.matera.bootcamp26.model.service.ContaService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.logging.Logger;

@Slf4j
@SpringBootApplication
@RequiredArgsConstructor
public class Bootcamp26Application {

	private final ContaService contaService;

	private static final Logger logger = Logger.getLogger(Bootcamp26Application.class.getName());

	public static void main(String[] args) {
		SpringApplication.run(Bootcamp26Application.class, args);
	}

	@PostConstruct
	void postConstruct() {
		logger.info("Bootcamp esta UP... Sem lombok");
		log.info("Bootcamp esta UP... Com lombok");
		try {
			String nulo = null;
			nulo.toUpperCase();
		} catch (Exception e) {
			log.error("Error", e);
		}
		contaService.getContas();
	}
}
