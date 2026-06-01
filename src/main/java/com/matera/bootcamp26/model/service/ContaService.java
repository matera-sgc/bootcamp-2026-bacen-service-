package com.matera.bootcamp26.model.service;

import com.matera.bootcamp26.exception.ContaException;
import com.matera.bootcamp26.model.dto.ContaDTO;
import com.matera.bootcamp26.model.entity.Conta;
import com.matera.bootcamp26.model.repository.ContaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ContaService {

    private final ContaRepository contaRepository;

    public ContaService(ContaRepository contaRepository) {
        this.contaRepository = contaRepository;
    }

    public ContaDTO getConta(Long id) {
        Conta conta = contaRepository.getConta(id);
        return entityToDTO(conta);
    }

    public List<ContaDTO> getContas() {
        List<Conta> contas = contaRepository.getContas();

        return contas.stream()
                .map((conta) -> entityToDTO(conta))
                .toList();
    }

    public ContaDTO criarConta(ContaDTO dto) {
        Conta conta = dtoToEntity(dto);
        if (dto.getAbertura() != null && dto.getAbertura().isAfter(LocalDate.now())) {
            throw new ContaException("Data de abertura não pode ser futura", HttpStatus.BAD_REQUEST);
        }
        Conta salva = contaRepository.salvarConta(conta);
        return entityToDTO(salva);
    }

    private ContaDTO entityToDTO(Conta conta) {
        return new ContaDTO(
            conta.getNome(),
            conta.getNumConta(),
            conta.getSaldo(),
            conta.getAbertura()
        );
    }

    private Conta dtoToEntity(ContaDTO dto) {
        Conta conta = new Conta();
        conta.setNome(dto.getNome());
        conta.setNumConta(dto.getConta());
        conta.setSaldo(dto.getSaldo());
        LocalDate abertura = dto.getAbertura() != null ? dto.getAbertura() : LocalDate.now();
        conta.setAbertura(abertura);
        return conta;
    }
}
