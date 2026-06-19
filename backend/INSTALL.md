# Pokémon Game API — Instalação e Configuração do Banco

**Parte do integrante: Everton — Banco de Dados e Persistência**

Este documento descreve como criar o banco PostgreSQL, carregar os dados
iniciais e configurar a camada de persistência (JPA/Hibernate) do backend
Spring Boot.

---

## 1. Pré-requisitos

| Ferramenta | Versão recomendada |
|---|---|
| Java JDK | 17+ |
| Maven | 3.8+ (ou use o `mvnw` do projeto) |
| PostgreSQL | 14+ |

Verifique:

```bash
java -version
mvn -version
psql --version
```

---

## 2. Criar o banco de dados

Acesse o PostgreSQL com um usuário administrador (ex.: `postgres`) e crie o banco:

```sql
CREATE DATABASE pokemon_game;
```

> Opcional — criar um usuário dedicado para a aplicação:
> ```sql
> CREATE USER pokemon_app WITH PASSWORD 'pokemon_app';
> GRANT ALL PRIVILEGES ON DATABASE pokemon_game TO pokemon_app;
> ```

---

## 3. Criar as tabelas (schema) e carregar o seed

Os scripts ficam em `backend/src/main/resources/db/`.

```bash
# 1) Estrutura (tabelas, PKs, FKs, constraints, trigger)
psql -U postgres -d pokemon_game -f backend/src/main/resources/db/schema.sql

# 2) Dados de exemplo (tipos, pokémons, habilidades, itens, treinadores)
psql -U postgres -d pokemon_game -f backend/src/main/resources/db/data.sql
```

No Windows (PowerShell), informe o host se necessário:

```powershell
psql -h localhost -U postgres -d pokemon_game -f "backend/src/main/resources/db/schema.sql"
psql -h localhost -U postgres -d pokemon_game -f "backend/src/main/resources/db/data.sql"
```

Verifique:

```sql
\dt
SELECT nome FROM tipo_pokemon;
SELECT nome, especie FROM pokemon;
```

---

## 4. Configurar a conexão da aplicação

A configuração fica em `backend/src/main/resources/application.properties`.
Os valores aceitam variáveis de ambiente (com *fallback* para o padrão local):

| Propriedade | Variável de ambiente | Padrão |
|---|---|---|
| URL JDBC | `DB_URL` | `jdbc:postgresql://localhost:5432/pokemon_game` |
| Usuário | `DB_USERNAME` | `postgres` |
| Senha | `DB_PASSWORD` | `postgres` |

Exemplo definindo via ambiente (PowerShell):

```powershell
$env:DB_URL = "jdbc:postgresql://localhost:5432/pokemon_game"
$env:DB_USERNAME = "postgres"
$env:DB_PASSWORD = "minhaSenha"
```

> **Importante:** não comite senhas reais no repositório. Use variáveis de
> ambiente ou um arquivo `.env` local (já ignorado pelo `.gitignore`).

### Estratégia de schema (Hibernate)

Por padrão, `spring.jpa.hibernate.ddl-auto=validate`: **o script SQL é a fonte
oficial do schema** e o Hibernate apenas valida se o mapeamento das entidades
corresponde às tabelas. Isso garante que o banco entregue e o código estejam
sincronizados.

**Quick start alternativo** (a aplicação cria e popula o banco sozinha ao subir):
no `application.properties`, troque `ddl-auto` para `none` e descomente:

```properties
spring.sql.init.mode=always
spring.sql.init.schema-locations=classpath:db/schema.sql
spring.sql.init.data-locations=classpath:db/data.sql
```

---

## 5. Compilar e executar

```bash
cd backend

# Compilar e rodar os testes (camada de persistência usa H2 em memória)
mvn clean test

# Subir a aplicação
mvn spring-boot:run
```

A API sobe em `http://localhost:8080`.

---

## 6. Estrutura entregue (camada de persistência)

```
backend/
├── pom.xml                          # Spring Boot + JPA + PostgreSQL + Lombok
├── INSTALL.md                       # este documento
└── src
    ├── main
    │   ├── java/org/g1tf
    │   │   ├── PokemonGameApiApplication.java
    │   │   ├── model/               # 11 entidades JPA + enum StatusPartida
    │   │   └── repository/          # 11 repositories Spring Data JPA
    │   └── resources
    │       ├── application.properties
    │       └── db
    │           ├── schema.sql       # DDL + constraints (RNs) + trigger
    │           └── data.sql         # carga inicial (seed)
    └── test
        ├── java/org/g1tf/persistence/PersistenceLayerTest.java
        └── resources/application.properties   # perfil de teste (H2)
```

> As camadas `service`, `dto`, `api` (controllers) e `exception` serão
> desenvolvidas pelo Pedro e pelo Andrew sobre estas entidades e repositórios.

---

## 7. Regras de negócio garantidas no banco

O `schema.sql` implementa em nível de banco várias regras de `docs/regras-negocio.md`:

| Regra | Como é garantida |
|---|---|
| RN01 — email único | `UNIQUE (email)` em `jogador` |
| RN03/RN04 — 1 treinador por jogador | `UNIQUE (jogador_id)` em `treinador` |
| RN05 — tipo sem nome duplicado | `UNIQUE (nome)` em `tipo_pokemon` |
| RN06 — habilidade poder ≥ 0 e nome não vazio | `CHECK` em `habilidade` |
| RN07 — item sem valor negativo | `CHECK (valor >= 0)` em `item` |
| RN08 — Pokémon com tipo obrigatório | `tipo_id NOT NULL` + FK |
| RN09 — vida/ataque/defesa/velocidade > 0 | `CHECK` em `pokemon` |
| RN11 — máx. 6 Pokémon por equipe | `TRIGGER tg_max_pokemon_equipe` |
| RN12/RN13 — ordem 1..6 e única na equipe | `CHECK` + `UNIQUE` em `equipe_pokemon` |
| RN15 — quantidade de item ≥ 0 | `CHECK` em `inventario` |
| RN16 — somar item já existente | `UNIQUE (treinador_id, item_id)` |
| RN14/RN18 — não remover dados de histórico | `FK ... ON DELETE RESTRICT` |
| RN19 — dois treinadores diferentes na partida | `CHECK (desafiante <> adversario)` |

As demais regras (cálculo de pontuação, ranking, fluxo de partida) são de
responsabilidade da camada de *service*.
