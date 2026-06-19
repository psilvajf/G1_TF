package org.g1tf.controller;

import lombok.RequiredArgsConstructor;
import org.g1tf.model.Jogador;
import org.g1tf.service.RankingService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ranking")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RankingController {

```
private final RankingService service;

@GetMapping
public List<Jogador> listarRanking() {

    return service.ranking();

}
```

}
