package org.g1tf.repository;

import org.g1tf.model.Partida;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de persistencia de Partida.
 */
public interface PartidaRepository extends JpaRepository<Partida, Long> {

    List<Partida> findByTreinadorDesafianteIdOrTreinadorAdversarioId(
            Long treinadorDesafianteId, Long treinadorAdversarioId);
}
