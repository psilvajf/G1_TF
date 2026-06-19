package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.TipoPokemon;
import org.g1tf.service.TipoPokemonService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tipos")
@RequiredArgsConstructor
@CrossOrigin("*")
public class TipoPokemonController {

    private final TipoPokemonService service;

    @GetMapping
    public List<TipoPokemon> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public TipoPokemon buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public TipoPokemon salvar(@RequestBody TipoPokemon tipo) {
        return service.salvar(tipo);
    }

    @PutMapping("/{id}")
    public TipoPokemon atualizar(
            @PathVariable Long id,
            @RequestBody TipoPokemon tipo) {

        return service.atualizar(id, tipo);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}