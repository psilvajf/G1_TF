package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Equipe;
import org.g1tf.service.EquipeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/equipes")
@RequiredArgsConstructor
@CrossOrigin("*")
public class EquipeController {

    private final EquipeService service;

    @GetMapping
    public List<Equipe> listar() {

        return service.listar();

    }

    @GetMapping("/{id}")
    public Equipe buscar(
            @PathVariable Long id) {

        return service.buscar(id);

    }

    @GetMapping("/treinador/{id}")
    public List<Equipe> buscarPorTreinador(
            @PathVariable Long id) {

        return service.buscarPorTreinador(id);

    }

    @PostMapping
    public Equipe salvar(
            @RequestBody Equipe equipe) {

        return service.salvar(equipe);

    }

    @PutMapping("/{id}")
    public Equipe atualizar(
            @PathVariable Long id,
            @RequestBody Equipe equipe) {

        return service.atualizar(id, equipe);

    }

    @DeleteMapping("/{id}")
    public void excluir(
            @PathVariable Long id) {

        service.excluir(id);

    }
}