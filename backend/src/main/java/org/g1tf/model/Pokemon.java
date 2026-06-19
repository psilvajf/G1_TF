package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Pokemon - representa os Pokemon disponiveis no jogo.
 */
@Entity
@Table(name = "pokemon")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Pokemon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 80)
    private String nome;

    @Column(nullable = false, length = 80)
    private String especie;

    @Column(columnDefinition = "text")
    private String descricao;

    @Column(nullable = false)
    private Integer nivel = 1;

    @Column(nullable = false)
    private Integer vida;

    @Column(nullable = false)
    private Integer ataque;

    @Column(nullable = false)
    private Integer defesa;

    @Column(nullable = false)
    private Integer velocidade;

    @Column(name = "imagem_url", length = 255)
    private String imagemUrl;

    @Column(nullable = false)
    private Boolean lendario = false;

    /** Tipo principal do Pokemon (RN08: obrigatorio). */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tipo_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_pokemon_tipo"))
    private TipoPokemon tipo;

    /** Habilidades do Pokemon (tabela associativa PokemonHabilidade). */
    @OneToMany(mappedBy = "pokemon", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<PokemonHabilidade> habilidades = new ArrayList<>();
}
