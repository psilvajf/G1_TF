package org.g1tf.repository;

import org.g1tf.model.PokemonHabilidade;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de persistencia da associativa PokemonHabilidade.
 */
public interface PokemonHabilidadeRepository extends JpaRepository<PokemonHabilidade, Long> {

    List<PokemonHabilidade> findByPokemonId(Long pokemonId);

    boolean existsByPokemonIdAndHabilidadeId(Long pokemonId, Long habilidadeId);
}
