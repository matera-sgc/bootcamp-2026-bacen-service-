package com.matera.bootcamp26.controller;

import com.matera.bootcamp26.model.dto.PagamentoResponseDTO;
import com.matera.bootcamp26.model.dto.PagamentoRequestDTO;
import com.matera.bootcamp26.model.service.PagamentoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/pagamentos")
public class PagamentoController {

    private final PagamentoService pagamentoService;

    public PagamentoController(PagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponseDTO> enviarPagamento(@RequestBody PagamentoRequestDTO req) {
        PagamentoResponseDTO pagamentoResponseDTO = pagamentoService.enviarPagamento(req);
        return ResponseEntity.status(HttpStatus.CREATED).body(pagamentoResponseDTO);
    }
}
