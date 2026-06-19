package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

/**
 * Item - recursos usados pelo treinador (pokebolas, pocoes, bonus...).
 */
@Entity
@Table(name = "item")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(columnDefinition = "text")
    private String descricao;

    /** POKEBOLA, POCAO, BONUS etc. */
    @Column(nullable = false, length = 30)
    private String categoria;

    @Column(nullable = false, length = 120)
    private String efeito;

    @Column(nullable = false, precision = 10, scale = 2)
    private BigDecimal valor = BigDecimal.ZERO;
}
