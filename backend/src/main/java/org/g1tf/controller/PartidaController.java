package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Partida;
import org.g1tf.service.PartidaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/partidas")
@RequiredArgsConstructor
@CrossOrigin("*")
public class PartidaController {

    private final PartidaService service;

    @GetMapping
    public List<Partida> listar() {

        return service.listar();

    }

    @GetMapping("/{id}")
    public Partida buscar(
            @PathVariable Long id) {

        return service.buscar(id);

    }

    @GetMapping("/treinador/{id}")
    public List<Partida> buscarPorTreinador(
            @PathVariable Long id) {

        return service.buscarPorTreinador(id);

    }

    @PostMapping
    public Partida salvar(
            @RequestBody Partida partida) {

        return service.salvar(partida);

    }

    @PutMapping("/{id}")
    public Partida atualizar(
            @PathVariable Long id,
            @RequestBody Partida partida) {

        return service.atualizar(id, partida);

    }

    @DeleteMapping("/{id}")
    public void excluir(
            @PathVariable Long id) {

        service.excluir(id);

    }
}