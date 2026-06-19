package org.g1tf.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/**
 * Partida - registra batalhas, pontuacoes e vencedor.
 * RN19: os dois treinadores devem ser diferentes (validado por CHECK no banco).
 */
@Entity
@Table(name = "partida")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Partida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treinador_desafiante_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_partida_desafiante"))
    private Treinador treinadorDesafiante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "treinador_adversario_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_partida_adversario"))
    private Treinador treinadorAdversario;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipe_desafiante_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_partida_equipe_desaf"))
    private Equipe equipeDesafiante;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "equipe_adversaria_id", nullable = false,
            foreignKey = @ForeignKey(name = "fk_partida_equipe_adver"))
    private Equipe equipeAdversaria;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private StatusPartida status = StatusPartida.AGENDADA;

    @Column(name = "pontuacao_desafiante", nullable = false)
    private Integer pontuacaoDesafiante = 0;

    @Column(name = "pontuacao_adversario", nullable = false)
    private Integer pontuacaoAdversario = 0;

    /** Pode ser nulo em empate/cancelamento. */
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vencedor_id",
            foreignKey = @ForeignKey(name = "fk_partida_vencedor"))
    private Treinador vencedor;

    @Column(name = "data_inicio", nullable = false, updatable = false)
    private LocalDateTime dataInicio;

    @Column(name = "data_fim")
    private LocalDateTime dataFim;

    @PrePersist
    public void prePersist() {
        if (this.dataInicio == null) {
            this.dataInicio = LocalDateTime.now();
        }
    }
}
