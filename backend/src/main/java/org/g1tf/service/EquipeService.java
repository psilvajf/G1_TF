package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Equipe;
import org.g1tf.repository.EquipeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EquipeService {

    private final EquipeRepository repository;

    public List<Equipe> listar() {

        return repository.findAll();

    }

    public Equipe buscar(Long id) {

        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Equipe não encontrada"));

    }

    public Equipe salvar(Equipe equipe) {

        return repository.save(equipe);

    }

    public Equipe atualizar(Long id, Equipe equipe) {

        equipe.setId(id);

        return repository.save(equipe);

    }

    public void excluir(Long id) {

        repository.deleteById(id);

    }

    public List<Equipe> buscarPorTreinador(Long treinadorId) {

        return repository.findByTreinadorId(
                treinadorId
        );

    }
}