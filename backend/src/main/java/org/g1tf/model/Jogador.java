package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Jogador - dados de acesso e ranking do usuario.
 * Relacao 1:1 com Treinador.
 */
@Entity
@Table(name = "jogador",
        uniqueConstraints = @UniqueConstraint(name = "uk_jogador_email", columnNames = "email"))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Jogador {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 120)
    private String nome;

    @Column(nullable = false, length = 150)
    private String email;

    @Column(nullable = false, length = 255)
    private String senha;

    @Column(nullable = false, length = 60)
    private String apelido;

    @Column(name = "pontuacao_total", nullable = false)
    private Integer pontuacaoTotal = 0;

    @Column(nullable = false)
    private Integer nivel = 1;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(nullable = false)
    private Boolean ativo = true;

    /** Gera a data de cadastro automaticamente na persistencia. */
    @PrePersist
    public void prePersist() {
        if (this.dataCadastro == null) {
            this.dataCadastro = LocalDateTime.now();
        }
    }
}
