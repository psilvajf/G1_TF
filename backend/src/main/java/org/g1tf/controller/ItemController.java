package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Item;
import org.g1tf.service.ItemService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/itens")
@RequiredArgsConstructor
@CrossOrigin("*")
public class ItemController {

    private final ItemService service;

    @GetMapping
    public List<Item> listar() {
        return service.listar();
    }

    @GetMapping("/{id}")
    public Item buscar(@PathVariable Long id) {
        return service.buscar(id);
    }

    @PostMapping
    public Item salvar(@RequestBody Item item) {
        return service.salvar(item);
    }

    @PutMapping("/{id}")
    public Item atualizar(
            @PathVariable Long id,
            @RequestBody Item item) {

        return service.atualizar(id, item);
    }

    @DeleteMapping("/{id}")
    public void excluir(@PathVariable Long id) {
        service.excluir(id);
    }
}