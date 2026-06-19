package org.g1tf;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Ponto de entrada da Pokemon Game API.
 *
 * Esta classe inicializa o contexto Spring Boot, que faz a varredura
 * dos pacotes org.g1tf.* (model, repository, service, api...).
 */
@SpringBootApplication
public class PokemonGameApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(PokemonGameApiApplication.class, args);
    }
}
