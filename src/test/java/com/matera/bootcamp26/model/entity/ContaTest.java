package com.matera.bootcamp26.model.entity;

import com.matera.bootcamp26.exception.ContaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;


class ContaTest {

    @Test
    @DisplayName("Deve alterar o saldo com sucesso ao chamar a função crédito")
    void deveAlterarOSaldoComSucesso() {
        Conta conta = new Conta();
        conta.setSaldo(BigDecimal.valueOf(1000));

        conta.credito(BigDecimal.valueOf(500));

        assertEquals(BigDecimal.valueOf(1500), conta.getSaldo());
    }

    @Test
    void deveApresentarErroAoInformarValorNegativoAoChamarFuncaoCredito() {
        Conta conta = new Conta();
        conta.setSaldo(BigDecimal.valueOf(1000));
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            conta.credito(BigDecimal.valueOf(-500));
        });
        assertEquals("Nao é permitido crédito de valor negativo ou zero", exception.getMessage());
    }

}