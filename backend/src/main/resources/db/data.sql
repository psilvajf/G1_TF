-- =====================================================================
-- Pokemon Game API  -  Carga inicial de dados (seed)
-- Parte do integrante: Everton - Banco de Dados e Persistencia
--
-- Dados de exemplo para validar o banco e dar base de demonstracao.
-- Pode ser executado apos o schema.sql.
-- =====================================================================

-- ----------------------- TIPOS ---------------------------------------
INSERT INTO tipo_pokemon (nome, descricao) VALUES
    ('fire',     'Pokemon do tipo Fogo'),
    ('water',    'Pokemon do tipo Agua'),
    ('grass',    'Pokemon do tipo Planta'),
    ('electric', 'Pokemon do tipo Eletrico'),
    ('normal',   'Pokemon do tipo Normal'),
    ('psychic',  'Pokemon do tipo Psiquico');

-- ----------------------- HABILIDADES ---------------------------------
INSERT INTO habilidade (nome, descricao, poder, custo_energia, tipo_efeito) VALUES
    ('Lanca-Chamas', 'Ataque de fogo de alto poder',        90, 15, 'ATAQUE'),
    ('Jato de Agua',  'Ataque de agua pressurizada',         80, 12, 'ATAQUE'),
    ('Chicote de Cipo','Ataque de planta a curta distancia', 45,  8, 'ATAQUE'),
    ('Choque do Trovao','Descarga eletrica paralisante',     90, 15, 'ATAQUE'),
    ('Protecao',      'Aumenta a defesa por um turno',        0, 10, 'DEFESA'),
    ('Recuperar',     'Restaura parte da vida',              50, 20, 'CURA'),
    ('Foco',          'Bonus de precisao e ataque',          20,  5, 'BONUS');

-- ----------------------- ITENS ---------------------------------------
INSERT INTO item (nome, descricao, categoria, efeito, valor) VALUES
    ('Pokebola',      'Captura Pokemon selvagens',     'POKEBOLA', 'CAPTURA',        50.00),
    ('Pocao',         'Restaura 20 de vida',           'POCAO',    'CURA_20',        100.00),
    ('Super Pocao',   'Restaura 50 de vida',           'POCAO',    'CURA_50',        300.00),
    ('Doce Raro',     'Aumenta o nivel do Pokemon',    'BONUS',    'NIVEL_MAIS_1',   800.00),
    ('Pedra do Fogo', 'Evolui Pokemon do tipo fogo',   'BONUS',    'EVOLUCAO_FOGO', 1000.00);

-- ----------------------- POKEMONS ------------------------------------
-- tipo_id referencia a ordem inserida acima:
-- 1=fire 2=water 3=grass 4=electric 5=normal 6=psychic
INSERT INTO pokemon (nome, especie, descricao, nivel, vida, ataque, defesa, velocidade, imagem_url, lendario, tipo_id) VALUES
    ('Charmander', 'Charmander', 'Pokemon lagarto de fogo',        5,  39, 52, 43, 65, 'https://img.pokemondb.net/sprites/charmander.png', FALSE, 1),
    ('Charizard',  'Charizard',  'Evolucao final de Charmander',  36, 78, 84, 78,100, 'https://img.pokemondb.net/sprites/charizard.png',  FALSE, 1),
    ('Squirtle',   'Squirtle',   'Pokemon tartaruga de agua',      5,  44, 48, 65, 43, 'https://img.pokemondb.net/sprites/squirtle.png',   FALSE, 2),
    ('Bulbasaur',  'Bulbasaur',  'Pokemon semente de planta',      5,  45, 49, 49, 45, 'https://img.pokemondb.net/sprites/bulbasaur.png',  FALSE, 3),
    ('Pikachu',    'Pikachu',    'Pokemon rato eletrico',          5,  35, 55, 40, 90, 'https://img.pokemondb.net/sprites/pikachu.png',    FALSE, 4),
    ('Mewtwo',     'Mewtwo',     'Pokemon psiquico lendario',     70,106,110, 90,130, 'https://img.pokemondb.net/sprites/mewtwo.png',     TRUE,  6);

-- ------------------- POKEMON_HABILIDADE ------------------------------
-- pokemon_id: 1=Charmander 2=Charizard 3=Squirtle 4=Bulbasaur 5=Pikachu 6=Mewtwo
-- habilidade_id: 1=Lanca-Chamas 2=Jato de Agua 3=Chicote 4=Choque 5=Protecao 6=Recuperar 7=Foco
INSERT INTO pokemon_habilidade (pokemon_id, habilidade_id, principal) VALUES
    (1, 1, TRUE),
    (2, 1, TRUE),
    (2, 5, FALSE),
    (3, 2, TRUE),
    (4, 3, TRUE),
    (5, 4, TRUE),
    (5, 7, FALSE),
    (6, 6, TRUE),
    (6, 7, FALSE);

-- ----------------------- JOGADORES / TREINADORES ---------------------
INSERT INTO jogador (nome, email, senha, apelido, pontuacao_total, nivel) VALUES
    ('Ash Ketchum',   'ash@pokemon.com',   '$2a$10$exemploHashSenha000000000000000000000000000000', 'AshK', 0, 1),
    ('Gary Oak',      'gary@pokemon.com',  '$2a$10$exemploHashSenha111111111111111111111111111111', 'GaryO',0, 1);

INSERT INTO treinador (nome, regiao, experiencia, moedas, jogador_id) VALUES
    ('Ash',  'Kanto', 0, 500, 1),
    ('Gary', 'Kanto', 0, 500, 2);

-- ----------------------- INVENTARIO ----------------------------------
INSERT INTO inventario (treinador_id, item_id, quantidade) VALUES
    (1, 1, 10),   -- Ash: 10 Pokebolas
    (1, 2,  5),   -- Ash: 5 Pocoes
    (2, 1,  8),   -- Gary: 8 Pokebolas
    (2, 3,  2);   -- Gary: 2 Super Pocoes

-- ----------------------- EQUIPES -------------------------------------
INSERT INTO equipe (nome, treinador_id, ativa) VALUES
    ('Equipe Ash',  1, TRUE),
    ('Equipe Gary', 2, TRUE);

INSERT INTO equipe_pokemon (equipe_id, pokemon_id, ordem_na_equipe, apelido) VALUES
    (1, 5, 1, 'Pikachu'),     -- Ash usa Pikachu
    (1, 1, 2, NULL),          -- Ash usa Charmander
    (2, 3, 1, NULL),          -- Gary usa Squirtle
    (2, 2, 2, 'Char');        -- Gary usa Charizard

-- =====================================================================
-- Fim do seed.
-- =====================================================================
