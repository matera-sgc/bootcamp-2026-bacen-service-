package com.matera.bootcamp26.controller;

import com.matera.bootcamp26.model.dto.ContaDTO;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;

@RestController
@RequestMapping("/api/v1/hello_world")
public class HelloWorld {

    @GetMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ContaDTO> hello() {
        ContaDTO contaDTO = new ContaDTO(
                "Tharlyson", 231231, BigDecimal.valueOf(5000000), LocalDate.now()
        );

        return ResponseEntity.ok(contaDTO);
    }

    @GetMapping(value = "/xml", produces = MediaType.APPLICATION_XML_VALUE)
    public ResponseEntity<ContaDTO> helloXML() {
        ContaDTO contaDTO = new ContaDTO(
                "Tharlyson", 231231, BigDecimal.valueOf(5000000), LocalDate.now()
        );

        return ResponseEntity.ok(contaDTO);
    }

}
