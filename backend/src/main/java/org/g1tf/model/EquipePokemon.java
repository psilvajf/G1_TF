package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * EquipePokemon - tabela associativa entre Equipe e Pokemon.
 * Cada posicao (ordem 1..6) e unica dentro da equipe (RN12/RN13).
 */
@Entity
@Table(name = "equipe_pokemon",
        uniqueConstraints = {
                @UniqueConstraint(name = "uk_ep_equipe_ordem",
                        columnNames = {"equipe_id", "ordem_na_equipe"}),
                @UniqueConstraint(name = "uk_ep_equipe_pokemon",
                        columnNames = {"equipe_id", "pokemon_id"})
        })
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EquipePokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipe_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ep_equipe"))
    private Equipe equipe;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "pokemon_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_ep_pokemon"))
    private Pokemon pokemon;

    /** Posicao de 1 a 6 (RN12). */
    @Column(name = "ordem_na_equipe", nullable = false)
    private Integer ordemNaEquipe;

    @Column(length = 60)
    private String apelido;
}
