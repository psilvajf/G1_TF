package org.g1tf.repository;

import org.g1tf.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

/**
 * Repositorio de persistencia de Inventario.
 */
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    List<Inventario> findByTreinadorId(Long treinadorId);

    Optional<Inventario> findByTreinadorIdAndItemId(Long treinadorId, Long itemId);
}
