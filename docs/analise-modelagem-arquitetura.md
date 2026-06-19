# Trabalho Final - Backend de Jogo com API REST

## Parte do integrante: Tiago - Analise, Modelagem e Arquitetura

## 1. Analise do projeto Frontend existente

O projeto frontend do grupo tem como tema o universo Pokemon e utiliza React para consultar informacoes da PokeAPI. As telas existentes trabalham com:

- Especies de Pokemon;
- Tipos;
- Habilidades;
- Itens;
- Cadeias de evolucao;
- Tela inicial e autenticacao simples.

O backend proposto deve deixar de ser apenas uma consulta informativa a API publica e passar a representar um jogo proprio, com dados persistidos em banco PostgreSQL, regras de negocio e endpoints REST. A PokeAPI continua servindo como referencia de boas praticas e inspiracao de dados, mas a API principal sera criada pelo grupo.

## 2. Tipo de jogo definido

O jogo sera um sistema de batalhas Pokemon simplificado, no qual jogadores cadastram treinadores, montam equipes com Pokemon, adicionam habilidades e itens, registram partidas e acumulam pontuacao/ranking.

Nome sugerido: **Pokemon Game API**.

Objetivo do jogo:

- Permitir que um jogador crie seu treinador;
- Cadastrar Pokemon disponiveis para o jogo;
- Montar equipes com ate 6 Pokemon;
- Associar habilidades e itens aos Pokemon;
- Registrar partidas entre jogadores;
- Calcular vencedor, pontos e ranking;
- Manter historico de partidas.

## 3. Requisitos funcionais

| Codigo | Requisito |
|---|---|
| RF01 | O sistema deve permitir cadastrar, listar, buscar, atualizar e remover jogadores. |
| RF02 | O sistema deve permitir cadastrar, listar, buscar, atualizar e remover treinadores. |
| RF03 | O sistema deve permitir cadastrar Pokemon com nome, especie, tipo, atributos e imagem. |
| RF04 | O sistema deve permitir cadastrar tipos Pokemon, como fire, water, electric, grass e outros. |
| RF05 | O sistema deve permitir cadastrar habilidades e associar habilidades a Pokemon. |
| RF06 | O sistema deve permitir cadastrar itens e adiciona-los ao inventario de um treinador. |
| RF07 | O sistema deve permitir criar equipes para um treinador. |
| RF08 | O sistema deve limitar cada equipe a no maximo 6 Pokemon. |
| RF09 | O sistema deve registrar partidas entre dois treinadores. |
| RF10 | O sistema deve calcular o vencedor da partida com base na pontuacao dos Pokemon, habilidades e itens. |
| RF11 | O sistema deve atualizar pontuacao e ranking dos jogadores apos uma partida finalizada. |
| RF12 | O sistema deve impedir exclusao de dados que estejam vinculados a partidas historicas, quando isso afetar a integridade. |
| RF13 | O sistema deve fornecer endpoint de proximo ID para cada entidade, conforme padrao solicitado em aula. |
| RF14 | O sistema deve retornar mensagens claras para erros de validacao e dados inexistentes. |

## 4. Requisitos nao funcionais

| Codigo | Requisito |
|---|---|
| RNF01 | O backend deve ser desenvolvido em Spring Boot. |
| RNF02 | O banco de dados deve ser PostgreSQL. |
| RNF03 | A persistencia deve utilizar JPA/Hibernate. |
| RNF04 | A API deve seguir organizacao em camadas: controller, service, repository, dto e model. |
| RNF05 | O sistema deve possuir testes unitarios para controllers e services. |
| RNF06 | Os endpoints devem retornar JSON. |
| RNF07 | O projeto deve possuir script SQL ou migracoes para criacao das tabelas. |
| RNF08 | O projeto deve possuir collection Postman/Insomnia ou frontend integrado para demonstracao. |

## 5. Entidades principais

### Jogador

Representa o usuario do sistema.

Campos:

- id;
- nome;
- email;
- senha;
- apelido;
- pontuacaoTotal;
- nivel;
- dataCadastro;
- ativo.

Relacionamentos:

- Um jogador pode ter um treinador.
- Um jogador participa indiretamente de partidas por meio do treinador.

### Treinador

Representa o personagem usado pelo jogador dentro do jogo.

Campos:

- id;
- nome;
- regiao;
- experiencia;
- moedas;
- jogadorId.

Relacionamentos:

- Um treinador pertence a um jogador.
- Um treinador possui equipes.
- Um treinador possui inventario.
- Um treinador participa de partidas.

### TipoPokemon

Representa os tipos do universo Pokemon.

Campos:

- id;
- nome;
- descricao.

Relacionamentos:

- Um tipo pode estar associado a varios Pokemon.

### Pokemon

Representa um Pokemon disponivel no jogo.

Campos:

- id;
- nome;
- especie;
- descricao;
- nivel;
- vida;
- ataque;
- defesa;
- velocidade;
- imagemUrl;
- lendario;
- tipoId.

Relacionamentos:

- Um Pokemon pertence a um tipo principal.
- Um Pokemon pode ter varias habilidades.
- Um Pokemon pode estar em equipes.

### Habilidade

Representa uma habilidade que pode ser usada em batalha.

Campos:

- id;
- nome;
- descricao;
- poder;
- custoEnergia;
- tipoEfeito.

Relacionamentos:

- Uma habilidade pode ser associada a varios Pokemon.
- Um Pokemon pode possuir varias habilidades.


### PokemonHabilidade

Tabela associativa entre Pokemon e Habilidade.

Campos:

- id;
- pokemonId;
- habilidadeId;
- principal.

Relacionamentos:

- Liga um Pokemon a varias habilidades.
- Liga uma habilidade a varios Pokemon.
### Item

Representa itens do jogo, como pocoes, pokebolas e itens de melhoria.

Campos:

- id;
- nome;
- descricao;
- categoria;
- efeito;
- valor;

Relacionamentos:

- Um item pode aparecer em varios inventarios.

### Inventario

Representa a posse de itens por treinador.

Campos:

- id;
- treinadorId;
- itemId;
- quantidade.

Relacionamentos:

- Um treinador possui varios itens.
- Um item pode estar no inventario de varios treinadores.

### Equipe

Representa o conjunto de Pokemon usado por um treinador.

Campos:

- id;
- nome;
- treinadorId;
- ativa;
- dataCriacao.

Relacionamentos:

- Uma equipe pertence a um treinador.
- Uma equipe possui ate 6 Pokemon.

### EquipePokemon

Tabela associativa entre equipe e Pokemon.

Campos:

- id;
- equipeId;
- pokemonId;
- ordemNaEquipe;
- apelido;

Relacionamentos:

- Liga uma equipe a varios Pokemon.

### Partida

Representa uma batalha entre dois treinadores.

Campos:

- id;
- treinadorDesafianteId;
- treinadorAdversarioId;
- equipeDesafianteId;
- equipeAdversariaId;
- status;
- pontuacaoDesafiante;
- pontuacaoAdversario;
- vencedorId;
- dataInicio;
- dataFim.

Relacionamentos:

- Uma partida possui dois treinadores.
- Uma partida utiliza duas equipes.
- Uma partida pode ter um vencedor.

## 6. Regras de negocio

| Codigo | Regra |
|---|---|
| RN01 | Um jogador deve possuir email unico. |
| RN02 | Um treinador deve estar vinculado a um jogador existente. |
| RN03 | Um jogador ativo pode ter apenas um treinador principal. |
| RN04 | O nome do Pokemon nao pode ser vazio. |
| RN05 | Vida, ataque, defesa e velocidade devem ser maiores que zero. |
| RN06 | Cada equipe pode possuir no maximo 6 Pokemon. |
| RN07 | Uma equipe nao pode ser criada sem treinador. |
| RN08 | Uma partida deve ter dois treinadores diferentes. |
| RN09 | Uma partida deve utilizar equipes existentes e pertencentes aos treinadores informados. |
| RN10 | Uma partida finalizada nao pode ser alterada, exceto por regra administrativa. |
| RN11 | O vencedor deve ser calculado pela maior pontuacao final. |
| RN12 | Em caso de empate, a partida pode ser marcada como EMPATE ou vencida pelo treinador com maior velocidade media da equipe. |
| RN13 | Ao finalizar uma partida, o vencedor recebe pontos e o perdedor recebe pontuacao menor de participacao. |
| RN14 | Nao deve ser permitido remover Pokemon, treinador ou equipe vinculados a partidas finalizadas. |
| RN15 | A quantidade de item no inventario nao pode ser negativa. |
| RN16 | Ao usar um item em uma partida, a quantidade deve ser reduzida do inventario. |

## 7. Calculo sugerido para partida

Pontuacao de um Pokemon:

```text
pontosPokemon = vida + ataque * 2 + defesa + velocidade + somaPoderHabilidades
```

Pontuacao da equipe:

```text
pontosEquipe = soma(pontosPokemon de todos os Pokemon da equipe) + bonusItens
```

Resultado:

- Se pontosEquipeDesafiante > pontosEquipeAdversaria, vence o desafiante.
- Se pontosEquipeAdversaria > pontosEquipeDesafiante, vence o adversario.
- Se empatar, aplicar criterio de velocidade media ou registrar empate.

Pontuacao do ranking:

- Vitoria: +30 pontos;
- Empate: +10 pontos para cada treinador;
- Derrota: +5 pontos;
- Partida cancelada: 0 ponto.

## 8. Endpoints REST esperados

O padrao deve ser repetido para cada entidade principal:

```text
GET /jogadores
GET /jogadores/{id}
GET /jogadores/next-id
POST /jogadores
POST /jogadores/update
POST /jogadores/{id}/delete
POST /jogadores/delete-all
```

Entidades com CRUD:

- `/jogadores`
- `/treinadores`
- `/tipos`
- `/pokemons`
- `/habilidades`
- `/itens`
- `/inventarios`
- `/equipes`
- `/partidas`

Endpoints especificos sugeridos:

```text
POST /equipes/{id}/pokemons
POST /equipes/{id}/pokemons/{pokemonId}/delete
GET /treinadores/{id}/inventario
POST /partidas/{id}/finalizar
GET /ranking
GET /pokemons/tipo/{tipoId}
```

## 9. Campos importantes para Frontend e Backend

### PokemonResponseDTO

- id;
- nome;
- especie;
- descricao;
- nivel;
- vida;
- ataque;
- defesa;
- velocidade;
- imagemUrl;
- lendario;
- tipo;
- habilidades.

### EquipeResponseDTO

- id;
- nome;
- treinadorId;
- treinadorNome;
- ativa;
- pokemons;
- quantidadePokemons;
- dataCriacao.

### PartidaResponseDTO

- id;
- treinadorDesafiante;
- treinadorAdversario;
- equipeDesafiante;
- equipeAdversaria;
- status;
- pontuacaoDesafiante;
- pontuacaoAdversario;
- vencedor;
- dataInicio;
- dataFim.

## 10. Estrutura recomendada do backend

```text
src/main/java/org/g1tf
|-- api
|   |-- JogadorController.java
|   |-- TreinadorController.java
|   |-- PokemonController.java
|   |-- TipoPokemonController.java
|   |-- HabilidadeController.java
|   |-- ItemController.java
|   |-- EquipeController.java
|   |-- PartidaController.java
|-- dto
|   |-- request
|   |-- response
|-- model
|   |-- Jogador.java
|   |-- Treinador.java
|   |-- Pokemon.java
|   |-- TipoPokemon.java
|   |-- Habilidade.java
|   |-- PokemonHabilidade.java
|   |-- Item.java
|   |-- Inventario.java
|   |-- Equipe.java
|   |-- EquipePokemon.java
|   |-- Partida.java
|-- repository
|-- service
|-- exception
```

## 11. Testes unitarios obrigatorios

Testes de controllers:

- Deve listar entidades com status 200;
- Deve buscar por ID existente com status 200;
- Deve retornar 404 quando ID nao existir;
- Deve criar entidade com status 201 ou 200;
- Deve rejeitar dados invalidos com status 400;
- Deve remover entidade com sucesso.

Testes de services:

- Validar email unico do jogador;
- Validar limite de 6 Pokemon por equipe;
- Validar partida com treinadores diferentes;
- Validar calculo de pontuacao;
- Validar atualizacao do ranking apos partida finalizada;
- Validar bloqueio de exclusao de entidade vinculada a partida.

## 12. Entregaveis da parte do Tiago

- Documento de requisitos: este documento.
- DER: arquivo `der-pokemon.md`.
- Modelagem das entidades: arquivo `modelagem-entidades.md`.
- Regras de negocio definidas: secao 6 deste documento e arquivo `regras-negocio.md`.


