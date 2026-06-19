package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * TipoPokemon - classifica Pokemon por tipo (fire, water, electric...).
 */
@Entity
@Table(name = "tipo_pokemon",
        uniqueConstraints = @UniqueConstraint(name = "uk_tipo_nome", columnNames = "nome"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TipoPokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 40)
    private String nome;

    @Column(length = 255)
    private String descricao;
}
