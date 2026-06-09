package com.matera.bootcamp26.model.dto;

import java.math.BigDecimal;

public class PagamentoRequestDTO {

    private Integer origemConta;
    private Integer destinoConta;
    private BigDecimal valor;

    public PagamentoRequestDTO(Integer origemConta, Integer destinoConta, BigDecimal valor) {
        this.origemConta = origemConta;
        this.destinoConta = destinoConta;
        this.valor = valor;
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

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
