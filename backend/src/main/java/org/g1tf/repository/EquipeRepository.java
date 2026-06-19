package org.g1tf.repository;

import org.g1tf.model.Equipe;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de persistencia de Equipe.
 */
public interface EquipeRepository extends JpaRepository<Equipe, Long> {

    List<Equipe> findByTreinadorId(Long treinadorId);
}
