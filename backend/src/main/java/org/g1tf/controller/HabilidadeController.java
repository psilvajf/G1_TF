package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Habilidade;
import org.g1tf.service.HabilidadeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/habilidades")
@RequiredArgsConstructor
@CrossOrigin("*")
public class HabilidadeController {

    private final HabilidadeService service;

    @GetMapping
    public List<Habilidade> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Habilidade buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public Habilidade salvar(@RequestBody Habilidade habilidade) {
        return service.salvar(habilidade);
    }

    @PutMapping("/{id}")
    public Habilidade atualizar(
            @PathVariable Long id,
            @RequestBody Habilidade habilidade) {

        return service.atualizar(id, habilidade);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}