CREATE TABLE IF NOT EXISTS cliente (
    codigo SERIAL PRIMARY KEY,
    nome VARCHAR(120) NOT NULL,
    telefone VARCHAR(30),
    email VARCHAR(120)
);

