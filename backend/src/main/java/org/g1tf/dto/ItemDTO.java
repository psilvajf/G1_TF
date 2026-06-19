package org.g1tf.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class ItemDTO {

    private Long id;
    private String nome;
    private String descricao;
    private String categoria;
    private String efeito;
    private BigDecimal valor;

}