package org.g1tf.repository;

import org.g1tf.model.Treinador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de persistencia de Treinador.
 */
public interface TreinadorRepository extends JpaRepository<Treinador, Long> {

    Optional<Treinador> findByJogadorId(Long jogadorId);

    boolean existsByJogadorId(Long jogadorId);
}
