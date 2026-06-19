package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.TipoPokemon;
import org.g1tf.repository.TipoPokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipoPokemonService {

    private final TipoPokemonRepository repository;

    public List<TipoPokemon> listar() {
        return repository.findAll();
    }

    public TipoPokemon buscar(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public TipoPokemon salvar(TipoPokemon tipo) {
        return repository.save(tipo);
    }

    public TipoPokemon atualizar(Long id, TipoPokemon tipo) {
        tipo.setId(id);
        return repository.save(tipo);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}