package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Habilidade - acoes ou bonus usados por Pokemon em batalha.
 */
@Entity
@Table(name = "habilidade")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Habilidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(columnDefinition = "text")
    private String descricao;

    @Column(nullable = false)
    private Integer poder = 0;

    @Column(name = "custo_energia", nullable = false)
    private Integer custoEnergia = 0;

    /** ATAQUE, DEFESA, CURA ou BONUS. */
    @Column(name = "tipo_efeito", nullable = false, length = 20)
    private String tipoEfeito;
}
