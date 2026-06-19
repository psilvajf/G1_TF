package org.g1tf.service;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Jogador;
import org.g1tf.repository.JogadorRepository;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RankingService {

```
private final JogadorRepository repository;

public List<Jogador> ranking() {

    List<Jogador> jogadores =
            repository.findAll();

    jogadores.sort(
            Comparator.comparing(
                    Jogador::getPontuacaoTotal
            ).reversed()
    );

    return jogadores;
}
```

}
