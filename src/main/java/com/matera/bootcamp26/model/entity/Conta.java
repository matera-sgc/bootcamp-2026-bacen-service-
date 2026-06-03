package com.matera.bootcamp26.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CONTA")
public class Conta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "NOME", nullable = false)
    private String nome;

    @Column(name = "NUMERO_CONTA", nullable = false, unique = true)
    private Integer numConta;

    @Column(name = "SALDO", nullable = false)
    private BigDecimal saldo;

    @Column(name = "ABERTURA", nullable = false)
    private LocalDate abertura;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "TIPO_CONTA", nullable = false)
    private TipoConta tipoConta;

    @Enumerated(EnumType.STRING)
    @Column(name = "STATUS_CONTA", nullable = false)
    private StatusConta statusConta;

    public Conta(Long id, String nome, Integer numConta, BigDecimal saldo, LocalDate abertura, TipoConta tipoConta, StatusConta statusConta) {
        this.id = id;
        this.nome = nome;
        this.numConta = numConta;
        this.saldo = saldo;
        this.abertura = abertura;
        this.tipoConta = tipoConta;
        this.statusConta = statusConta;
    }

    public Conta() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Integer getNumConta() {
        return numConta;
    }

    public void setNumConta(Integer numConta) {
        this.numConta = numConta;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void setSaldo(BigDecimal saldo) {
        this.saldo = saldo;
    }

    public void credito(BigDecimal valor) {
        this.saldo.add(valor);
    }

    public LocalDate getAbertura() {
        return abertura;
    }

    public void setAbertura(LocalDate abertura) {
        this.abertura = abertura;
    }

    public TipoConta getTipoConta() {
        return tipoConta;
    }

    public void setTipoConta(TipoConta tipoConta) {
        this.tipoConta = tipoConta;
    }

    public StatusConta getStatusConta() {
        return statusConta;
    }

    public void setStatusConta(StatusConta statusConta) {
        this.statusConta = statusConta;
    }
}
