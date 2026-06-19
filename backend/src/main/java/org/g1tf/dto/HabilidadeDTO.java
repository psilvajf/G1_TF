package org.g1tf.dto;

import lombok.Data;

@Data
public class HabilidadeDTO {

    private Long id;
    private String nome;
    private String descricao;
    private Integer poder;
    private Integer custoEnergia;
    private String tipoEfeito;

}