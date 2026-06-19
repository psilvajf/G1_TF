package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Item;
import org.g1tf.repository.ItemRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ItemService {

    private final ItemRepository repository;

    public List<Item> listar() {
        return repository.findAll();
    }

    public Item buscar(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Item salvar(Item item) {
        return repository.save(item);
    }

    public Item atualizar(Long id, Item item) {
        item.setId(id);
        return repository.save(item);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}