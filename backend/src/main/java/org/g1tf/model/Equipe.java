package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Equipe - conjunto de ate 6 Pokemon usado por um treinador.
 */
@Entity
@Table(name = "equipe")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Equipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    /** Dono da equipe (RN10: obrigatorio). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treinador_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_equipe_treinador"))
    private Treinador treinador;

    @Column(nullable = false)
    private Boolean ativa = true;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDateTime dataCriacao;

    /** Pokemon da equipe (maximo 6 - RN11). */
    @OneToMany(mappedBy = "equipe", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EquipePokemon> pokemons = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        if (this.dataCriacao == null) {
            this.dataCriacao = LocalDateTime.now();
        }
    }
}
