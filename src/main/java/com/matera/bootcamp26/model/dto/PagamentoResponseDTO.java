package com.matera.bootcamp26.model.dto;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class PagamentoResponseDTO {

    private UUID id;
    private Integer origemConta;
    private Integer destinoConta;
    private LocalDateTime dataPagamento;
    private BigDecimal valor;

    public PagamentoResponseDTO(UUID id, Integer origemConta, Integer destinoConta, LocalDateTime dataPagamento, BigDecimal valor) {
        this.id = id;
        this.origemConta = origemConta;
        this.destinoConta = destinoConta;
        this.dataPagamento = dataPagamento;
        this.valor = valor;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Integer getOrigemConta() {
        return origemConta;
    }

    public void setOrigemConta(Integer origemConta) {
        this.origemConta = origemConta;
    }

    public Integer getDestinoConta() {
        return destinoConta;
    }

    public void setDestinoConta(Integer destinoConta) {
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
}
