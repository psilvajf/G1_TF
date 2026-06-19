package org.g1tf.repository;

import org.g1tf.model.Habilidade;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de persistencia de Habilidade.
 */
public interface HabilidadeRepository extends JpaRepository<Habilidade, Long> {
}
