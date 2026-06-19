package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * PokemonHabilidade - tabela associativa N:N entre Pokemon e Habilidade.
 */
@Entity
@Table(name = "pokemon_habilidade",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_ph_pokemon_habilidade",
                columnNames = {"pokemon_id", "habilidade_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PokemonHabilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pokemon_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ph_pokemon"))
    private Pokemon pokemon;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "habilidade_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ph_habilidade"))
    private Habilidade habilidade;

    /** Indica se e a habilidade principal do Pokemon. */
    @Column(nullable = false)
    private Boolean principal = false;
}
