# Modelagem das Entidades

## Jogador

Responsabilidade: armazenar os dados de acesso e ranking do usuario.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Nome completo. |
| email | String | Sim | Deve ser unico. |
| senha | String | Sim | Deve ser armazenada de forma segura. |
| apelido | String | Sim | Nome exibido no ranking. |
| pontuacaoTotal | Integer | Sim | Inicia em 0. |
| nivel | Integer | Sim | Inicia em 1. |
| dataCadastro | LocalDateTime | Sim | Gerada automaticamente. |
| ativo | Boolean | Sim | Controle de exclusao logica. |

## Treinador

Responsabilidade: representar o personagem do jogador no jogo.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Nome do treinador. |
| regiao | String | Sim | Kanto, Johto, Hoenn etc. |
| experiencia | Integer | Sim | Inicia em 0. |
| moedas | Integer | Sim | Moeda interna do jogo. |
| jogador | Jogador | Sim | Relacao 1:1. |

## TipoPokemon

Responsabilidade: classificar Pokemon por tipo.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Exemplo: fire, water, electric. |
| descricao | String | Nao | Texto explicativo. |

## Pokemon

Responsabilidade: representar os Pokemon disponiveis no jogo.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Nome do Pokemon. |
| especie | String | Sim | Especie base. |
| descricao | String | Nao | Descricao para exibicao. |
| nivel | Integer | Sim | Nivel do Pokemon. |
| vida | Integer | Sim | Atributo usado em batalha. |
| ataque | Integer | Sim | Atributo usado em batalha. |
| defesa | Integer | Sim | Atributo usado em batalha. |
| velocidade | Integer | Sim | Atributo usado em desempate. |
| imagemUrl | String | Nao | URL da imagem/sprite. |
| lendario | Boolean | Sim | Identifica Pokemon lendario. |
| tipo | TipoPokemon | Sim | Tipo principal. |
| habilidades | List<PokemonHabilidade> | Nao | Relacao feita pela tabela associativa PokemonHabilidade. |

## Habilidade

Responsabilidade: definir acoes ou bonus usados por Pokemon.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Nome da habilidade. |
| descricao | String | Nao | Explicacao da habilidade. |
| poder | Integer | Sim | Valor somado ao calculo de batalha. |
| custoEnergia | Integer | Sim | Custo para uso futuro. |
| tipoEfeito | String | Sim | ATAQUE, DEFESA, CURA, BONUS. |


## PokemonHabilidade

Responsabilidade: resolver o relacionamento muitos-para-muitos entre Pokemon e Habilidade.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| pokemon | Pokemon | Sim | Pokemon relacionado. |
| habilidade | Habilidade | Sim | Habilidade relacionada. |
| principal | Boolean | Sim | Indica se e a habilidade principal do Pokemon. |
## Item

Responsabilidade: representar recursos usados pelo treinador.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Nome do item. |
| descricao | String | Nao | Descricao do item. |
| categoria | String | Sim | POKEBOLA, POCAO, BONUS etc. |
| efeito | String | Sim | Efeito aplicado. |
| valor | BigDecimal | Sim | Preco do item. |

## Inventario

Responsabilidade: controlar os itens de cada treinador.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| treinador | Treinador | Sim | Dono do item. |
| item | Item | Sim | Item armazenado. |
| quantidade | Integer | Sim | Nao pode ser negativa. |

## Equipe

Responsabilidade: agrupar Pokemon para batalhas.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| nome | String | Sim | Nome da equipe. |
| treinador | Treinador | Sim | Dono da equipe. |
| ativa | Boolean | Sim | Indica equipe principal. |
| dataCriacao | LocalDateTime | Sim | Gerada automaticamente. |
| pokemons | List<EquipePokemon> | Nao | Maximo 6. |

## EquipePokemon

Responsabilidade: controlar os Pokemon dentro de uma equipe.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| equipe | Equipe | Sim | Equipe relacionada. |
| pokemon | Pokemon | Sim | Pokemon escolhido. |
| ordemNaEquipe | Integer | Sim | Posicao de 1 a 6. |
| apelido | String | Nao | Nome personalizado. |

## Partida

Responsabilidade: registrar batalhas, pontuacoes e vencedor.

| Campo | Tipo sugerido | Obrigatorio | Observacao |
|---|---|---:|---|
| id | Long | Sim | Chave primaria. |
| treinadorDesafiante | Treinador | Sim | Primeiro treinador. |
| treinadorAdversario | Treinador | Sim | Segundo treinador. |
| equipeDesafiante | Equipe | Sim | Equipe do desafiante. |
| equipeAdversaria | Equipe | Sim | Equipe do adversario. |
| status | String/Enum | Sim | AGENDADA, EM_ANDAMENTO, FINALIZADA, CANCELADA. |
| pontuacaoDesafiante | Integer | Sim | Calculada pelo service. |
| pontuacaoAdversario | Integer | Sim | Calculada pelo service. |
| vencedor | Treinador | Nao | Pode ser nulo em empate/cancelamento. |
| dataInicio | LocalDateTime | Sim | Criada ao iniciar. |
| dataFim | LocalDateTime | Nao | Preenchida ao finalizar. |

