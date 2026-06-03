package com.matera.bootcamp26.model.dto;

import java.math.BigDecimal;
import java.time.LocalDate;

public class ContaDTO {

    private Long id;
    private String nome;
    private Integer conta;
    private BigDecimal saldo;
    private LocalDate abertura;
    private String statusConta;
    private String tipoConta;

    public ContaDTO(Long id, String nome, Integer conta, BigDecimal saldo, LocalDate abertura, String statusConta, String tipoConta) {
        this.id = id;
        this.nome = nome;
        this.conta = conta;
        this.saldo = saldo;
        this.abertura = abertura;
        this.statusConta = statusConta;
        this.tipoConta = tipoConta;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(String statusConta) {
        this.statusConta = statusConta;
    }

    public String getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(String tipoConta) {
        this.tipoConta = tipoConta;
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
