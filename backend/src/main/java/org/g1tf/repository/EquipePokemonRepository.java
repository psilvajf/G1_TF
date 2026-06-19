package org.g1tf.repository;

import org.g1tf.model.EquipePokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Repositorio de persistencia da associativa EquipePokemon.
 */
public interface EquipePokemonRepository extends JpaRepository<EquipePokemon, Long> {

    List<EquipePokemon> findByEquipeId(Long equipeId);

    long countByEquipeId(Long equipeId);
}
