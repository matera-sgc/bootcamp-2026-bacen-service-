package com.matera.bootcamp26.model.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "PAGAMENTO")
public class Pagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "ORIGEM_CONTA", referencedColumnName = "ID", nullable = false)
    private Conta origemConta;

    @ManyToOne
    @JoinColumn(name = "DESTINO_CONTA", referencedColumnName = "ID", nullable = false)
    private Conta destinoConta;

    @Column(name = "DATA_PAGAMENTO", nullable = false)
    private LocalDateTime dataPagamento;

    @Column(name = "VALOR_PAGAMENTO", nullable = false)
    private BigDecimal valor;

    public Pagamento(Conta origemConta, Conta destinoConta, LocalDateTime dataPagamento, BigDecimal valor) {
        this.origemConta = origemConta;
        this.destinoConta = destinoConta;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
    }

    public Pagamento() {
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Conta getOrigemConta() {
        return origemConta;
    }

    public void setOrigemConta(Conta origemConta) {
        this.origemConta = origemConta;
    }

    public Conta getDestinoConta() {
        return destinoConta;
    }

    public void setDestinoConta(Conta destinoConta) {
        this.destinoConta = destinoConta;
    }

    public LocalDateTime getDataPagamento() {
        return dataPagamento;
    }

    public void setDataPagamento(LocalDateTime dataPagamento) {
        this.dataPagamento = dataPagamento;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    @Override
    public String toString() {
        return "Pagamento{" +
                "id=" + id +
                ", origemConta=" + origemConta +
                ", destinoConta=" + destinoConta +
                ", dataPagamento=" + dataPagamento +
                ", valorPagamento=" + valor +
                '}';
    }
}
