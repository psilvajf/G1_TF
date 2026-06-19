package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Inventario - posse de itens por treinador.
 * Unico por (treinador, item) para permitir somar quantidade (RN16).
 */
@Entity
@Table(name = "inventario",
        uniqueConstraints = @UniqueConstraint(
                name = "uk_inventario_treinador_item",
                columnNames = {"treinador_id", "item_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Inventario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treinador_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventario_treinador"))
    private Treinador treinador;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "item_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_inventario_item"))
    private Item item;

    /** Nao pode ser negativa (RN15). */
    @Column(nullable = false)
    private Integer quantidade = 0;
}
