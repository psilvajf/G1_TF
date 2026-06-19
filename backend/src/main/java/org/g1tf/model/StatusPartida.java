package org.g1tf.model;

/**
 * Estados possiveis de uma Partida.
 * Mapeado como STRING na coluna partida.status (ver schema.sql).
 */
public enum StatusPartida {
    AGENDADA,
    EM_ANDAMENTO,
    FINALIZADA,
    CANCELADA
}
