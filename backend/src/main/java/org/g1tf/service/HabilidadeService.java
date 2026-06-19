package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Habilidade;
import org.g1tf.repository.HabilidadeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabilidadeService {

    private final HabilidadeRepository repository;

    public List<Habilidade> listar() {
        return repository.findAll();
    }

    public Habilidade buscar(Long id) {
        return repository.findById(id).orElseThrow();
    }

    public Habilidade salvar(Habilidade habilidade) {
        return repository.save(habilidade);
    }

    public Habilidade atualizar(Long id, Habilidade habilidade) {
        habilidade.setId(id);
        return repository.save(habilidade);
    }

    public void excluir(Long id) {
        repository.deleteById(id);
    }
}