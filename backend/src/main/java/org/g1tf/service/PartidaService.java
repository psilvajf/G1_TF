package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Partida;
import org.g1tf.repository.PartidaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PartidaService {

    private final PartidaRepository repository;

    public List<Partida> listar() {
        return repository.findAll();
    }

    public Partida buscar(Long id) {
        return repository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Partida não encontrada"));
    }

    public Partida salvar(Partida partida) {

        partida.setDataInicio(LocalDateTime.now());

        return repository.save(partida);
    }

    public Partida atualizar(Long id, Partida partida) {

        Partida existente = buscar(id);

        existente.setStatus(partida.getStatus());
        existente.setPontuacaoDesafiante(
                partida.getPontuacaoDesafiante());

        existente.setPontuacaoAdversario(
                partida.getPontuacaoAdversario());

        existente.setVencedor(
                partida.getVencedor());

        existente.setDataFim(
                partida.getDataFim());

        return repository.save(existente);
    }

    public void excluir(Long id) {

        repository.deleteById(id);

    }

    public List<Partida> buscarPorTreinador(Long treinadorId) {

        return repository
                .findByTreinadorDesafianteIdOrTreinadorAdversarioId(
                        treinadorId,
                        treinadorId
                );

    }
}