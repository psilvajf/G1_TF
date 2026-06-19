package org.g1tf.repository;

import org.g1tf.model.TipoPokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de persistencia de TipoPokemon.
 */
public interface TipoPokemonRepository extends JpaRepository<TipoPokemon, Long> {

    Optional<TipoPokemon> findByNome(String nome);

    boolean existsByNome(String nome);
}
