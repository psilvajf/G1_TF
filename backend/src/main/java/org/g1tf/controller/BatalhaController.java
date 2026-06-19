package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.dto.BatalhaDTO;
import org.g1tf.dto.ResultadoBatalhaDTO;
import org.g1tf.service.BatalhaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/batalha")
@RequiredArgsConstructor
@CrossOrigin("*")
public class BatalhaController {

    private final BatalhaService service;

    @PostMapping
    public ResultadoBatalhaDTO batalhar(
            @RequestBody BatalhaDTO dto){

        return service.batalhar(
                dto.getPokemon1(),
                dto.getPokemon2()
        );
    }
}