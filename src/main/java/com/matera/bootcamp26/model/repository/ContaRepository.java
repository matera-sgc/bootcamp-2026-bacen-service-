package com.matera.bootcamp26.model.repository;

import com.matera.bootcamp26.model.entity.Conta;
import net.datafaker.Faker;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Component
public class ContaRepository {

    public Conta getConta(Long id) {
        Conta conta = new Conta();
        Faker faker = new Faker();
        conta.setId(id);
        conta.setNumConta(faker.number().randomDigit());
        conta.setNome(faker.name().firstName());
        conta.setSaldo(BigDecimal.valueOf(faker.number().positive()));
        conta.setAbertura(LocalDate.now());

        return conta;
    }

    public List<Conta> getContas() {
        List<Conta> contas = new ArrayList<>();
        for (long i = 0; i<10; i++) {
            contas.add(
                    getConta(i)
            );
        }

        return contas;
    }

}
