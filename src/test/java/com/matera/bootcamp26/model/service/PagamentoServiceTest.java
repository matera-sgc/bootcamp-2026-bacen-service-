package com.matera.bootcamp26.model.service;

import com.matera.bootcamp26.exception.ContaException;
import com.matera.bootcamp26.model.dto.PagamentoRequestDTO;
import com.matera.bootcamp26.model.dto.PagamentoResponseDTO;
import com.matera.bootcamp26.model.entity.Conta;
import com.matera.bootcamp26.model.entity.Pagamento;
import com.matera.bootcamp26.model.repository.ContaRepository;
import com.matera.bootcamp26.model.repository.PagamentoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PagamentoServiceTest {

    @Mock
    private PagamentoRepository pagamentoRepository;
    @Mock
    private ContaRepository contaRepository;

    @InjectMocks
    private PagamentoService pagamentoService;

    @Test
    void deveRetornaErroAoInformarUmaContaDeOrigemQueNaoExiste() {
        //Given(Dado)
        PagamentoRequestDTO pagamentoRequestDTO = new PagamentoRequestDTO(
                123456, 654321, BigDecimal.valueOf(100)
        );
        when(contaRepository.findByNumConta(pagamentoRequestDTO.getOrigemConta())).thenReturn(java.util.Optional.empty());

        //When(Quando)
        ContaException exception = assertThrows(ContaException.class, () -> {
            pagamentoService.enviarPagamento(pagamentoRequestDTO);
        });

        //Then(Então)
        assertEquals("Conta de origem não encontrada", exception.getMessage());
    }

    @Test
    void deveRetornaErroAoInformarUmaContaDeDestinoQueNaoExiste() {
        //Given(Dado)
        PagamentoRequestDTO pagamentoRequestDTO = new PagamentoRequestDTO(
                123456, 654321, BigDecimal.valueOf(100)
        );
        when(
                contaRepository.findByNumConta(pagamentoRequestDTO.getOrigemConta())
        ).thenReturn(
                Optional.of(
                        Conta.builder()
                                .id(1L)
                                .numConta(pagamentoRequestDTO.getOrigemConta())
                                .saldo(BigDecimal.valueOf(1000))
                                .build()
                )
        );
        when(
                contaRepository.findByNumConta(pagamentoRequestDTO.getDestinoConta())
        ).thenReturn(
                Optional.empty()
        );

        //When(Quando)
        ContaException exception = assertThrows(ContaException.class, () -> {
            pagamentoService.enviarPagamento(pagamentoRequestDTO);
        });

        //Then(Então)
        assertEquals("Conta de destino não encontrada", exception.getMessage());
    }

    @Test
    void deveRetornaComSucessoOPagamento() {
        //Given(Dado)
        LocalDateTime dataPagamento = LocalDateTime.now();
        Conta contaOrigem = createConta(1L, 123456);
        Conta contaDestino = createConta(2L, 654321);

        PagamentoRequestDTO pagamentoRequestDTO = new PagamentoRequestDTO(
                123456, 654321, BigDecimal.valueOf(100)
        );
        Pagamento pagamento = createPagamento(
                contaOrigem, contaDestino, dataPagamento, pagamentoRequestDTO.getValor()
        );
        when(
                contaRepository.findByNumConta(pagamentoRequestDTO.getOrigemConta())
        ).thenReturn(
                Optional.of(
                        contaOrigem
                )
        );
        when(
                contaRepository.findByNumConta(pagamentoRequestDTO.getDestinoConta())
        ).thenReturn(
                Optional.of(
                        contaDestino
                )
        );
        when(pagamentoRepository.save(any())).thenReturn(
                pagamento
        );

        //When(Quando)
        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.enviarPagamento(pagamentoRequestDTO);

        //Then(Então)
        assertNotNull(pagamentoResponseDTO);
        assertEquals(pagamento.getId(), pagamentoResponseDTO.getId());
        assertEquals(pagamento.getOrigemConta().getNumConta(), pagamentoResponseDTO.getOrigemConta());
        assertEquals(pagamento.getDestinoConta().getNumConta(), pagamentoResponseDTO.getDestinoConta());
        assertEquals(pagamento.getDataPagamento(), pagamentoResponseDTO.getDataPagamento());
        assertEquals(pagamento.getValor(), pagamentoResponseDTO.getValor());

        verify(
                contaRepository,
                times(1)
        ).findByNumConta(pagamentoRequestDTO.getOrigemConta());
        verify(
                contaRepository,
                times(1)
        ).findByNumConta(pagamentoRequestDTO.getDestinoConta());
        verify(
                contaRepository,
                times(2)
        ).findByNumConta(any());
    }

    @Test
    void deveRetornaErro() {
        //Given(Dado)
        Conta contaOrigem = createConta(1L, 123456);

        PagamentoRequestDTO pagamentoRequestDTO = new PagamentoRequestDTO(
                123456, 123456, BigDecimal.valueOf(100)
        );
        when(
                contaRepository.findByNumConta(pagamentoRequestDTO.getOrigemConta())
        ).thenReturn(
                Optional.of(
                        contaOrigem
                )
        );
        when(
                contaRepository.findByNumConta(pagamentoRequestDTO.getDestinoConta())
        ).thenReturn(
                Optional.of(
                        contaOrigem
                )
        );

        //When(Quando)
        ContaException exception = assertThrows(ContaException.class, () -> {
            pagamentoService.enviarPagamento(pagamentoRequestDTO);
        });

        //Then(Então)
        assertEquals("Conta de origem e destino não podem ser a mesma", exception.getMessage());
    }

    private Pagamento createPagamento(
            Conta origem,
            Conta destino,
            LocalDateTime dataPagamento,
            BigDecimal valor) {
        Pagamento pagamento = new Pagamento(
                origem,
                destino,
                dataPagamento,
                valor
        );

        pagamento.setId(UUID.randomUUID());
        return pagamento;
    }

    private Conta createConta(Long idConta, Integer numConta) {
        return Conta.builder()
                .id(idConta)
                .numConta(numConta)
                .saldo(BigDecimal.valueOf(1000))
                .build();
    }
}