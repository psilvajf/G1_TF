package org.g1tf.repository;

import org.g1tf.model.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de persistencia de Pokemon.
 */
public interface PokemonRepository extends JpaRepository<Pokemon, Long> {

    List<Pokemon> findByTipoId(Long tipoId);
}
