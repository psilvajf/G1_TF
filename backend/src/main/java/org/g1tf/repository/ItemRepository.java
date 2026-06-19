package org.g1tf.repository;

import org.g1tf.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio de persistencia de Item.
 */
public interface ItemRepository extends JpaRepository<Item, Long> {
}
