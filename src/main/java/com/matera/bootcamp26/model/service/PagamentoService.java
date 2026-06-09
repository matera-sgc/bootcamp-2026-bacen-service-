package com.matera.bootcamp26.model.service;

import com.matera.bootcamp26.exception.ContaException;
import com.matera.bootcamp26.exception.PagamentoException;
import com.matera.bootcamp26.model.dto.PagamentoResponseDTO;
import com.matera.bootcamp26.model.dto.PagamentoRequestDTO;
import com.matera.bootcamp26.model.entity.Conta;
import com.matera.bootcamp26.model.entity.Pagamento;
import com.matera.bootcamp26.model.repository.ContaRepository;
import com.matera.bootcamp26.model.repository.PagamentoRepository;
import jakarta.annotation.Nonnull;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class PagamentoService {

    private final PagamentoRepository pagamentoRepository;
    private final ContaRepository contaRepository;

    public PagamentoService(PagamentoRepository pagamentoRepository, ContaRepository contaRepository) {
        this.pagamentoRepository = pagamentoRepository;
        this.contaRepository = contaRepository;
    }

    @Transactional
    public PagamentoResponseDTO enviarPagamento(PagamentoRequestDTO req) {

        BigDecimal valor = req.getValor();

        if (valor == null ||  valor.compareTo(BigDecimal.ZERO) <= 0) {
            throw new PagamentoException("Valor do pagamento deve ser maior que zero", HttpStatus.BAD_REQUEST);
        }

        Conta contaOrigem = contaRepository.findByNumConta(req.getOrigemConta())
                .orElseThrow(() -> new ContaException("Conta de origem não encontrada", HttpStatus.BAD_REQUEST));

        Conta contaDestino = contaRepository.findByNumConta(req.getDestinoConta())
                .orElseThrow(() -> new ContaException("Conta de destino não encontrada", HttpStatus.BAD_REQUEST));

        if (contaDestino.getId().equals(contaOrigem.getId())) {
            throw new ContaException("Conta de origem e destino não podem ser a mesma", HttpStatus.BAD_REQUEST);
        }

        contaOrigem.debito(valor);
        contaRepository.save(contaOrigem);

        contaDestino.credito(valor);
        contaRepository.save(contaDestino);

        Pagamento pagamento = new Pagamento(
                contaOrigem,
                contaDestino,
                LocalDateTime.now(),
                valor
        );

        Pagamento pagamentoSalvo = pagamentoRepository.save(pagamento);

        return PagamentoToDTO(pagamentoSalvo);
    }

    @Nonnull
    private static PagamentoResponseDTO PagamentoToDTO(Pagamento pagamentoSalvo) {
        return new PagamentoResponseDTO(
                pagamentoSalvo.getId(),
                pagamentoSalvo.getOrigemConta().getNumConta(),
                pagamentoSalvo.getDestinoConta().getNumConta(),
                pagamentoSalvo.getDataPagamento(),
                pagamentoSalvo.getValor());
    }
}
