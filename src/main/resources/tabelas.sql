CREATE TABLE IF NOT EXISTS empresa (
    id      SERIAL NOT NULL PRIMARY KEY,
    cnpj    BIGINT NOT NULL,
    nome    VARCHAR(255) NOT NULL UNIQUE
);

CREATE TABLE IF NOT EXISTS ativo (
    id         SERIAL NOT NULL PRIMARY KEY,
    dtype      VARCHAR(31) NOT NULL,
    codigo     VARCHAR(255) NOT NULL UNIQUE,
    setor      VARCHAR(255),
    tipo       VARCHAR(255),
    empresa_id BIGINT NOT NULL REFERENCES empresa
);

CREATE TABLE IF NOT EXISTS usuario (
    id              SERIAL NOT NULL PRIMARY KEY,
    cpf             VARCHAR(255) NOT NULL,
    data_nascimento TIMESTAMP,
    email           VARCHAR(255) NOT NULL UNIQUE,
    nome            VARCHAR(255) NOT NULL,
    password        VARCHAR(255) NOT NULL UNIQUE,
    status          BOOLEAN
);

CREATE TABLE IF NOT EXISTS carteira (
    id         SERIAL NOT NULL PRIMARY KEY,
    status     BOOLEAN,
    usuario_id INTEGER NOT NULL REFERENCES usuario
);

CREATE TABLE IF NOT EXISTS carteira_ativo (
    id             SERIAL NOT NULL PRIMARY KEY,
    data_transacao TIMESTAMP,
    operacao       VARCHAR(255),
    quantidade     INTEGER,
    valor          DOUBLE PRECISION,
    ativo_id       INTEGER NOT NULL REFERENCES ativo,
    carteira_id    INTEGER NOT NULL REFERENCES carteira
);

CREATE TABLE IF NOT EXISTS dados_fundamentalista (
     id                SERIAL NOT NULL PRIMARY KEY,
     data_atualizacao  TIMESTAMP,
     dy                DOUBLE PRECISION,
     ev_ebit           DOUBLE PRECISION,
     ev_ebitda         DOUBLE PRECISION,
     liquidez_corrente DOUBLE PRECISION,
     marge_ebit        DOUBLE PRECISION,
     marge_liq         DOUBLE PRECISION,
     p_acl             DOUBLE PRECISION,
     p_ativo           DOUBLE PRECISION,
     p_cap_giro        DOUBLE PRECISION,
     p_ebit            DOUBLE PRECISION,
     pl                DOUBLE PRECISION,
     p_vp              DOUBLE PRECISION,
     psr               DOUBLE PRECISION,
     roe               DOUBLE PRECISION,
     roic              DOUBLE PRECISION,
     status            BOOLEAN,
     ativo_id          INTEGER NOT NULL REFERENCES ativo
);

CREATE TABLE IF NOT EXISTS role (
    role_id   SERIAL NOT NULL PRIMARY KEY,
    descricao VARCHAR(255),
    role_name VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS usuario_role (
    id      INTEGER NOT NULL REFERENCES usuario,
    role_id INTEGER NOT NULL REFERENCES role,
    PRIMARY KEY (id, role_id)
);