package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.dto.ResultadoBatalhaDTO;
import org.g1tf.model.Pokemon;
import org.g1tf.repository.PokemonRepository;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BatalhaService {


private final PokemonRepository pokemonRepository;

public ResultadoBatalhaDTO batalhar(
        Long pokemon1Id,
        Long pokemon2Id) {

    Pokemon pokemon1 =
            pokemonRepository
                    .findById(pokemon1Id)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Pokémon 1 não encontrado"));

    Pokemon pokemon2 =
            pokemonRepository
                    .findById(pokemon2Id)
                    .orElseThrow(() ->
                            new RuntimeException(
                                    "Pokémon 2 não encontrado"));

    int forca1 =
            pokemon1.getAtaque()
            + pokemon1.getDefesa()
            + pokemon1.getVelocidade()
            + pokemon1.getNivel();

    int forca2 =
            pokemon2.getAtaque()
            + pokemon2.getDefesa()
            + pokemon2.getVelocidade()
            + pokemon2.getNivel();

    String vencedor;

    if (forca1 > forca2) {

        vencedor = pokemon1.getNome();

    } else if (forca2 > forca1) {

        vencedor = pokemon2.getNome();

    } else {

        vencedor = "Empate";

    }

    return new ResultadoBatalhaDTO(
            vencedor,
            forca1,
            forca2
    );
}


}
