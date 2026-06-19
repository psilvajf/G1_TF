# Regras de Negocio - Pokemon Game API

## Cadastro e integridade

| Codigo | Regra |
|---|---|
| RN01 | O email do jogador deve ser unico. |
| RN02 | Nome, email e senha do jogador sao obrigatorios. |
| RN03 | Um treinador so pode ser cadastrado para um jogador existente. |
| RN04 | Um jogador ativo deve possuir no maximo um treinador principal. |
| RN05 | Tipos Pokemon nao devem ter nomes duplicados. |
| RN06 | Habilidades nao devem ter nome vazio e poder negativo. |
| RN07 | Itens nao devem ter valor negativo. |

## Pokemon e equipes

| Codigo | Regra |
|---|---|
| RN08 | Um Pokemon deve possuir tipo principal. |
| RN09 | Vida, ataque, defesa e velocidade devem ser maiores que zero. |
| RN10 | Uma equipe deve pertencer a um treinador existente. |
| RN11 | Uma equipe pode possuir no maximo 6 Pokemon. |
| RN12 | A ordem do Pokemon na equipe deve estar entre 1 e 6. |
| RN13 | Nao deve existir dois Pokemon na mesma posicao dentro da mesma equipe. |
| RN14 | Uma equipe usada em partida finalizada nao deve ser removida fisicamente. |

## Inventario

| Codigo | Regra |
|---|---|
| RN15 | A quantidade de itens no inventario nao pode ser negativa. |
| RN16 | Ao adicionar item ja existente no inventario, o sistema deve somar a quantidade. |
| RN17 | Ao usar item, o sistema deve verificar se ha quantidade suficiente. |
| RN18 | Item vinculado a historico de uso nao deve ser removido fisicamente. |

## Partidas e ranking

| Codigo | Regra |
|---|---|
| RN19 | Uma partida deve conter dois treinadores diferentes. |
| RN20 | Cada treinador deve usar uma equipe que pertence a ele. |
| RN21 | Partida finalizada nao pode ser finalizada novamente. |
| RN22 | O vencedor deve ser definido pela maior pontuacao calculada. |
| RN23 | Em empate, o sistema pode registrar empate ou aplicar velocidade media como criterio de desempate. |
| RN24 | Ao finalizar uma partida, a pontuacao total dos jogadores deve ser atualizada. |
| RN25 | Vencedor recebe 30 pontos, empate concede 10 pontos para cada jogador e derrota concede 5 pontos. |
| RN26 | Partida cancelada nao altera ranking. |

## Validacoes de API

| Codigo | Regra |
|---|---|
| RN27 | Buscar ID inexistente deve retornar erro 404. |
| RN28 | Dados invalidos devem retornar erro 400 com mensagem clara. |
| RN29 | Operacoes que violem regra de negocio devem retornar erro 409 ou 400. |
| RN30 | Listagens devem retornar lista vazia quando nao houver registros, nao erro. |


