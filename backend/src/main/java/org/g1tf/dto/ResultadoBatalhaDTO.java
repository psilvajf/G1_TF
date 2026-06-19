package org.g1tf.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ResultadoBatalhaDTO {

    private String vencedor;
    private Integer danoPokemon1;
    private Integer danoPokemon2;

}