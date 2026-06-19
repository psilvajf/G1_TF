package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Pokemon;
import org.g1tf.service.CapturaService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/captura")
@RequiredArgsConstructor
@CrossOrigin("*")
public class CapturaController {

    private final CapturaService service;

    @PostMapping
    public Pokemon capturar() {

        return service.capturarPokemon();

    }
}