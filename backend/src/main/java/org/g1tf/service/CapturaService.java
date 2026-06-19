package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Pokemon;
import org.g1tf.repository.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class CapturaService {

```
private final PokemonRepository pokemonRepository;

public Pokemon capturarPokemon() {

    List<Pokemon> pokemons =
            pokemonRepository.findAll();

    if (pokemons.isEmpty()) {
        throw new RuntimeException(
                "Nenhum Pokémon cadastrado"
        );
    }

    Random random = new Random();

    int indice =
            random.nextInt(pokemons.size());

    return pokemons.get(indice);
}
```

}
