# DER - Pokemon Game API

O diagrama abaixo representa a modelagem inicial do banco de dados para o backend do jogo.

```mermaid
erDiagram
    JOGADOR ||--|| TREINADOR : possui
    TREINADOR ||--o{ EQUIPE : cria
    TREINADOR ||--o{ INVENTARIO : possui
    TIPO_POKEMON ||--o{ POKEMON : classifica
    POKEMON ||--o{ POKEMON_HABILIDADE : possui
    HABILIDADE ||--o{ POKEMON_HABILIDADE : relacionada
    EQUIPE ||--o{ EQUIPE_POKEMON : contem
    POKEMON ||--o{ EQUIPE_POKEMON : participa
    ITEM ||--o{ INVENTARIO : armazenado
    TREINADOR ||--o{ PARTIDA : desafiante
    TREINADOR ||--o{ PARTIDA : adversario
    EQUIPE ||--o{ PARTIDA : equipe_desafiante
    EQUIPE ||--o{ PARTIDA : equipe_adversaria

    JOGADOR {
        bigint id PK
        varchar nome
        varchar email UK
        varchar senha
        varchar apelido
        int pontuacao_total
        int nivel
        timestamp data_cadastro
        boolean ativo
    }

    TREINADOR {
        bigint id PK
        varchar nome
        varchar regiao
        int experiencia
        int moedas
        bigint jogador_id FK
    }

    TIPO_POKEMON {
        bigint id PK
        varchar nome UK
        varchar descricao
    }

    POKEMON {
        bigint id PK
        varchar nome
        varchar especie
        text descricao
        int nivel
        int vida
        int ataque
        int defesa
        int velocidade
        varchar imagem_url
        boolean lendario
        bigint tipo_id FK
    }

    HABILIDADE {
        bigint id PK
        varchar nome
        text descricao
        int poder
        int custo_energia
        varchar tipo_efeito
    }


    POKEMON_HABILIDADE {
        bigint id PK
        bigint pokemon_id FK
        bigint habilidade_id FK
        boolean principal
    }
    ITEM {
        bigint id PK
        varchar nome
        text descricao
        varchar categoria
        varchar efeito
        decimal valor
    }

    INVENTARIO {
        bigint id PK
        bigint treinador_id FK
        bigint item_id FK
        int quantidade
    }

    EQUIPE {
        bigint id PK
        varchar nome
        bigint treinador_id FK
        boolean ativa
        timestamp data_criacao
    }

    EQUIPE_POKEMON {
        bigint id PK
        bigint equipe_id FK
        bigint pokemon_id FK
        int ordem_na_equipe
        varchar apelido
    }

    PARTIDA {
        bigint id PK
        bigint treinador_desafiante_id FK
        bigint treinador_adversario_id FK
        bigint equipe_desafiante_id FK
        bigint equipe_adversaria_id FK
        varchar status
        int pontuacao_desafiante
        int pontuacao_adversario
        bigint vencedor_id FK
        timestamp data_inicio
        timestamp data_fim
    }
```

## Relacionamentos principais

| Relacionamento | Cardinalidade | Descricao |
|---|---:|---|
| Jogador - Treinador | 1:1 | Cada jogador ativo possui um treinador principal. |
| Treinador - Equipe | 1:N | Um treinador pode criar varias equipes. |
| Treinador - Inventario | 1:N | Um treinador possui varios itens em seu inventario. |
| TipoPokemon - Pokemon | 1:N | Um tipo classifica varios Pokemon. |
| Pokemon - PokemonHabilidade - Habilidade | N:N | A tabela PokemonHabilidade resolve a relacao muitos-para-muitos entre Pokemon e Habilidade. |
| Equipe - Pokemon | N:N | Uma equipe possui ate 6 Pokemon, ligados pela tabela EquipePokemon. |
| Treinador - Partida | 1:N | Um treinador pode participar de varias partidas. |
| Equipe - Partida | 1:N | Uma equipe pode ser usada em varias partidas historicas. |

