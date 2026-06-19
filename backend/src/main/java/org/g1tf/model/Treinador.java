package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Treinador - personagem do jogador no jogo. Relacao 1:1 com Jogador.
 */
@Entity
@Table(name = "treinador",
        uniqueConstraints = @UniqueConstraint(name = "uk_treinador_jogador", columnNames = "jogador_id"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Treinador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 60)
    private String regiao;

    @Column(nullable = false)
    private Integer experiencia = 0;

    @Column(nullable = false)
    private Integer moedas = 0;

    /** Relacao 1:1 com Jogador (lado dono da FK). */
    @OneToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "jogador_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_treinador_jogador"))
    private Jogador jogador;
}
