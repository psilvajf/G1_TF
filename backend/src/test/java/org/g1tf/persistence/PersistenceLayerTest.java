package org.g1tf.persistence;

import org.g1tf.model.Jogador;
import org.g1tf.model.TipoPokemon;
import org.g1tf.repository.JogadorRepository;
import org.g1tf.repository.TipoPokemonRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Teste de fumaca da camada de persistencia (parte do Everton).
 *
 * Valida que o mapeamento JPA/Hibernate das entidades esta consistente
 * e que as operacoes basicas de repositorio funcionam. Usa H2 em memoria
 * (ver src/test/resources/application.properties).
 */
@DataJpaTest
class PersistenceLayerTest {

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private TipoPokemonRepository tipoPokemonRepository;

    @Test
    void devePersistirEBuscarJogadorPorEmail() {
        Jogador jogador = new Jogador();
        jogador.setNome("Ash Ketchum");
        jogador.setEmail("ash@pokemon.com");
        jogador.setSenha("senha-segura");
        jogador.setApelido("AshK");

        jogadorRepository.save(jogador);

        assertThat(jogadorRepository.findByEmail("ash@pokemon.com")).isPresent();
        assertThat(jogadorRepository.existsByEmail("ash@pokemon.com")).isTrue();
        // @PrePersist deve ter preenchido a data de cadastro.
        assertThat(jogador.getDataCadastro()).isNotNull();
    }

    @Test
    void deveRespeitarUnicidadeDoTipo() {
        tipoPokemonRepository.save(new TipoPokemon(null, "fire", "Tipo Fogo"));

        assertThat(tipoPokemonRepository.existsByNome("fire")).isTrue();
        assertThat(tipoPokemonRepository.findByNome("fire")).isPresent();
    }
}
