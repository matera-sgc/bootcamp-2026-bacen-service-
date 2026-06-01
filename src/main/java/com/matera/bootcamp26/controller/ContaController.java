package com.matera.bootcamp26.controller;

import com.matera.bootcamp26.model.dto.ContaDTO;
import com.matera.bootcamp26.model.service.ContaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/contas")
public class ContaController {

    private final ContaService contaService;

    public ContaController(ContaService contaService) {
        this.contaService = contaService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaDTO> getConta(@PathVariable("id") Long id) {
        ContaDTO contaDTO = contaService.getConta(id);

        return ResponseEntity.ok(contaDTO);
    }

    @GetMapping
    public ResponseEntity<List<ContaDTO>> getContas() {
        List<ContaDTO> contaDTOS = contaService.getContas();
        return ResponseEntity.ok(contaDTOS);
    }

    @PostMapping
    public ResponseEntity<ContaDTO> criarConta(@RequestBody ContaDTO contaDTO) {
        ContaDTO criada = contaService.criarConta(contaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(criada);
    }

}
