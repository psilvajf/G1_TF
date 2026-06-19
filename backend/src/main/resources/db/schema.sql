-- =====================================================================
-- Pokemon Game API  -  Script de criacao do banco (PostgreSQL)
-- Parte do integrante: Everton - Banco de Dados e Persistencia
-- Modelagem base: docs/der-pokemon.md e docs/modelagem-entidades.md (Tiago)
--
-- As CONSTRAINTS deste script implementam, em nivel de banco, as
-- Regras de Negocio (RN) definidas em docs/regras-negocio.md.
-- =====================================================================

-- Para recriar o banco do zero, descomente as linhas abaixo:
-- DROP SCHEMA public CASCADE;
-- CREATE SCHEMA public;

-- ---------------------------------------------------------------------
-- JOGADOR
-- ---------------------------------------------------------------------
CREATE TABLE jogador (
    id              BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome            VARCHAR(120) NOT NULL,
    email           VARCHAR(150) NOT NULL,
    senha           VARCHAR(255) NOT NULL,
    apelido         VARCHAR(60)  NOT NULL,
    pontuacao_total INTEGER      NOT NULL DEFAULT 0,
    nivel           INTEGER      NOT NULL DEFAULT 1,
    data_cadastro   TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    ativo           BOOLEAN      NOT NULL DEFAULT TRUE,
    CONSTRAINT uk_jogador_email      UNIQUE (email),                 -- RN01
    CONSTRAINT ck_jogador_nome       CHECK (length(trim(nome)) > 0), -- RN02
    CONSTRAINT ck_jogador_pontuacao  CHECK (pontuacao_total >= 0),
    CONSTRAINT ck_jogador_nivel      CHECK (nivel >= 1)
);

-- ---------------------------------------------------------------------
-- TREINADOR  (1:1 com Jogador)
-- ---------------------------------------------------------------------
CREATE TABLE treinador (
    id           BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(120) NOT NULL,
    regiao       VARCHAR(60)  NOT NULL,
    experiencia  INTEGER      NOT NULL DEFAULT 0,
    moedas       INTEGER      NOT NULL DEFAULT 0,
    jogador_id   BIGINT       NOT NULL,
    CONSTRAINT fk_treinador_jogador FOREIGN KEY (jogador_id)
        REFERENCES jogador (id) ON DELETE RESTRICT,
    CONSTRAINT uk_treinador_jogador UNIQUE (jogador_id),             -- RN03/RN04 (1:1)
    CONSTRAINT ck_treinador_experiencia CHECK (experiencia >= 0),
    CONSTRAINT ck_treinador_moedas      CHECK (moedas >= 0)
);

-- ---------------------------------------------------------------------
-- TIPO_POKEMON
-- ---------------------------------------------------------------------
CREATE TABLE tipo_pokemon (
    id        BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome      VARCHAR(40)  NOT NULL,
    descricao VARCHAR(255),
    CONSTRAINT uk_tipo_nome UNIQUE (nome)                            -- RN05
);

-- ---------------------------------------------------------------------
-- POKEMON
-- ---------------------------------------------------------------------
CREATE TABLE pokemon (
    id          BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome        VARCHAR(80)  NOT NULL,
    especie     VARCHAR(80)  NOT NULL,
    descricao   TEXT,
    nivel       INTEGER      NOT NULL DEFAULT 1,
    vida        INTEGER      NOT NULL,
    ataque      INTEGER      NOT NULL,
    defesa      INTEGER      NOT NULL,
    velocidade  INTEGER      NOT NULL,
    imagem_url  VARCHAR(255),
    lendario    BOOLEAN      NOT NULL DEFAULT FALSE,
    tipo_id     BIGINT       NOT NULL,                               -- RN08
    CONSTRAINT fk_pokemon_tipo FOREIGN KEY (tipo_id)
        REFERENCES tipo_pokemon (id) ON DELETE RESTRICT,
    CONSTRAINT ck_pokemon_nome  CHECK (length(trim(nome)) > 0),      -- RN04 (Tiago)
    CONSTRAINT ck_pokemon_stats CHECK (                             -- RN09
        vida > 0 AND ataque > 0 AND defesa > 0 AND velocidade > 0
    ),
    CONSTRAINT ck_pokemon_nivel CHECK (nivel >= 1)
);
CREATE INDEX ix_pokemon_tipo ON pokemon (tipo_id);

-- ---------------------------------------------------------------------
-- HABILIDADE
-- ---------------------------------------------------------------------
CREATE TABLE habilidade (
    id           BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(80)  NOT NULL,
    descricao    TEXT,
    poder        INTEGER      NOT NULL DEFAULT 0,
    custo_energia INTEGER     NOT NULL DEFAULT 0,
    tipo_efeito  VARCHAR(20)  NOT NULL,
    CONSTRAINT ck_habilidade_nome  CHECK (length(trim(nome)) > 0),  -- RN06
    CONSTRAINT ck_habilidade_poder CHECK (poder >= 0),              -- RN06
    CONSTRAINT ck_habilidade_custo CHECK (custo_energia >= 0),
    CONSTRAINT ck_habilidade_efeito CHECK
        (tipo_efeito IN ('ATAQUE', 'DEFESA', 'CURA', 'BONUS'))
);

-- ---------------------------------------------------------------------
-- POKEMON_HABILIDADE  (N:N entre Pokemon e Habilidade)
-- ---------------------------------------------------------------------
CREATE TABLE pokemon_habilidade (
    id            BIGINT  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    pokemon_id    BIGINT  NOT NULL,
    habilidade_id BIGINT  NOT NULL,
    principal     BOOLEAN NOT NULL DEFAULT FALSE,
    CONSTRAINT fk_ph_pokemon FOREIGN KEY (pokemon_id)
        REFERENCES pokemon (id) ON DELETE CASCADE,
    CONSTRAINT fk_ph_habilidade FOREIGN KEY (habilidade_id)
        REFERENCES habilidade (id) ON DELETE RESTRICT,
    CONSTRAINT uk_ph_pokemon_habilidade UNIQUE (pokemon_id, habilidade_id)
);
CREATE INDEX ix_ph_pokemon    ON pokemon_habilidade (pokemon_id);
CREATE INDEX ix_ph_habilidade ON pokemon_habilidade (habilidade_id);

-- ---------------------------------------------------------------------
-- ITEM
-- ---------------------------------------------------------------------
CREATE TABLE item (
    id        BIGINT        GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome      VARCHAR(80)   NOT NULL,
    descricao TEXT,
    categoria VARCHAR(30)   NOT NULL,
    efeito    VARCHAR(120)  NOT NULL,
    valor     NUMERIC(10,2) NOT NULL DEFAULT 0,
    CONSTRAINT ck_item_nome  CHECK (length(trim(nome)) > 0),
    CONSTRAINT ck_item_valor CHECK (valor >= 0)                     -- RN07
);

-- ---------------------------------------------------------------------
-- INVENTARIO  (itens por treinador)
-- ---------------------------------------------------------------------
CREATE TABLE inventario (
    id          BIGINT  GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    treinador_id BIGINT NOT NULL,
    item_id     BIGINT  NOT NULL,
    quantidade  INTEGER NOT NULL DEFAULT 0,
    CONSTRAINT fk_inventario_treinador FOREIGN KEY (treinador_id)
        REFERENCES treinador (id) ON DELETE CASCADE,
    CONSTRAINT fk_inventario_item FOREIGN KEY (item_id)
        REFERENCES item (id) ON DELETE RESTRICT,                    -- RN18
    CONSTRAINT uk_inventario_treinador_item UNIQUE (treinador_id, item_id), -- RN16
    CONSTRAINT ck_inventario_quantidade CHECK (quantidade >= 0)     -- RN15
);
CREATE INDEX ix_inventario_treinador ON inventario (treinador_id);

-- ---------------------------------------------------------------------
-- EQUIPE
-- ---------------------------------------------------------------------
CREATE TABLE equipe (
    id           BIGINT       GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    nome         VARCHAR(80)  NOT NULL,
    treinador_id BIGINT       NOT NULL,                             -- RN10
    ativa        BOOLEAN      NOT NULL DEFAULT TRUE,
    data_criacao TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_equipe_treinador FOREIGN KEY (treinador_id)
        REFERENCES treinador (id) ON DELETE RESTRICT
);
CREATE INDEX ix_equipe_treinador ON equipe (treinador_id);

-- ---------------------------------------------------------------------
-- EQUIPE_POKEMON  (N:N entre Equipe e Pokemon, ate 6 por equipe)
-- ---------------------------------------------------------------------
CREATE TABLE equipe_pokemon (
    id              BIGINT      GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    equipe_id       BIGINT      NOT NULL,
    pokemon_id      BIGINT      NOT NULL,
    ordem_na_equipe INTEGER     NOT NULL,
    apelido         VARCHAR(60),
    CONSTRAINT fk_ep_equipe FOREIGN KEY (equipe_id)
        REFERENCES equipe (id) ON DELETE CASCADE,
    CONSTRAINT fk_ep_pokemon FOREIGN KEY (pokemon_id)
        REFERENCES pokemon (id) ON DELETE RESTRICT,                 -- RN14
    CONSTRAINT ck_ep_ordem CHECK (ordem_na_equipe BETWEEN 1 AND 6), -- RN12
    CONSTRAINT uk_ep_equipe_ordem   UNIQUE (equipe_id, ordem_na_equipe), -- RN13
    CONSTRAINT uk_ep_equipe_pokemon UNIQUE (equipe_id, pokemon_id)
);
CREATE INDEX ix_ep_equipe  ON equipe_pokemon (equipe_id);
CREATE INDEX ix_ep_pokemon ON equipe_pokemon (pokemon_id);

-- RN11: uma equipe pode possuir no maximo 6 Pokemon.
-- Reforco a nivel de banco via trigger (alem da validacao no service).
CREATE OR REPLACE FUNCTION fn_valida_max_pokemon_equipe()
RETURNS TRIGGER AS $$
BEGIN
    IF (SELECT COUNT(*) FROM equipe_pokemon
        WHERE equipe_id = NEW.equipe_id) >= 6 THEN
        RAISE EXCEPTION 'RN11: uma equipe pode possuir no maximo 6 Pokemon';
    END IF;
    RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER tg_max_pokemon_equipe
    BEFORE INSERT ON equipe_pokemon
    FOR EACH ROW EXECUTE FUNCTION fn_valida_max_pokemon_equipe();

-- ---------------------------------------------------------------------
-- PARTIDA  (historico de batalhas)
-- ---------------------------------------------------------------------
CREATE TABLE partida (
    id                      BIGINT    GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    treinador_desafiante_id BIGINT    NOT NULL,
    treinador_adversario_id BIGINT    NOT NULL,
    equipe_desafiante_id    BIGINT    NOT NULL,
    equipe_adversaria_id    BIGINT    NOT NULL,
    status                  VARCHAR(20) NOT NULL DEFAULT 'AGENDADA',
    pontuacao_desafiante    INTEGER   NOT NULL DEFAULT 0,
    pontuacao_adversario    INTEGER   NOT NULL DEFAULT 0,
    vencedor_id             BIGINT,
    data_inicio             TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    data_fim                TIMESTAMP,
    CONSTRAINT fk_partida_desafiante FOREIGN KEY (treinador_desafiante_id)
        REFERENCES treinador (id) ON DELETE RESTRICT,               -- RN14
    CONSTRAINT fk_partida_adversario FOREIGN KEY (treinador_adversario_id)
        REFERENCES treinador (id) ON DELETE RESTRICT,
    CONSTRAINT fk_partida_equipe_desaf FOREIGN KEY (equipe_desafiante_id)
        REFERENCES equipe (id) ON DELETE RESTRICT,
    CONSTRAINT fk_partida_equipe_adver FOREIGN KEY (equipe_adversaria_id)
        REFERENCES equipe (id) ON DELETE RESTRICT,
    CONSTRAINT fk_partida_vencedor FOREIGN KEY (vencedor_id)
        REFERENCES treinador (id) ON DELETE RESTRICT,
    CONSTRAINT ck_partida_treinadores CHECK
        (treinador_desafiante_id <> treinador_adversario_id),      -- RN19
    CONSTRAINT ck_partida_status CHECK
        (status IN ('AGENDADA', 'EM_ANDAMENTO', 'FINALIZADA', 'CANCELADA'))
);
CREATE INDEX ix_partida_desafiante ON partida (treinador_desafiante_id);
CREATE INDEX ix_partida_adversario ON partida (treinador_adversario_id);

-- =====================================================================
-- Fim do schema.
-- =====================================================================
