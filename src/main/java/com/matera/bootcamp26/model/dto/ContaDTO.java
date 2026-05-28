package com.matera.bootcamp26.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaDTO {

    private String nome;
    private Integer conta;
    private BigDecimal saldo;
    private LocalDate abertura;

    public ContaDTO(String nome, Integer conta, BigDecimal saldo, LocalDate abertura) {
        this.nome = nome;
        this.conta = conta;
        this.saldo = saldo;
        this.abertura = abertura;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getConta() {
        return conta;
    }

    public void setConta(Integer conta) {
        this.conta = conta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public LocalDate getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDate abertura) {
        this.abertura = abertura;
    }

    @Override
    public String toString() {
        return "ContaDTO{" +
                "nome='" + nome + '\'' +
                ", conta=" + conta +
                ", saldo=" + saldo +
                ", abertura=" + abertura +
                '}';
    }
}
