package com.matera.bootcamp26.model.service;

import com.matera.bootcamp26.model.dto.ContaDTO;
import com.matera.bootcamp26.model.entity.Conta;
import com.matera.bootcamp26.model.repository.ContaRepository;
import org.springframework.stereotype.Service;

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

    private ContaDTO entityToDTO(Conta conta) {
        return new ContaDTO(
            conta.getNome(),
            conta.getNumConta(),
            conta.getSaldo(),
            conta.getAbertura()
        );
    }
}
