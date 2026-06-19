package org.g1tf.repository;

import org.g1tf.model.Jogador;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * Repositorio de persistencia de Jogador.
 * Metodos derivados auxiliam regras como RN01 (email unico).
 */
public interface JogadorRepository extends JpaRepository<Jogador, Long> {

    Optional<Jogador> findByEmail(String email);

    boolean existsByEmail(String email);
}
