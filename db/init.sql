-- DROP TABLE IF EXISTS folha_pagamento CASCADE;
-- DROP TABLE IF EXISTS dependente CASCADE;
-- DROP TABLE IF EXISTS funcionario CASCADE;
DROP TABLE IF EXISTS departamento CASCADE;

CREATE TABLE IF NOT EXISTS departamento (
                              id SERIAL PRIMARY KEY,
                              nome VARCHAR(100) NOT NULL
);

CREATE TABLE IF NOT EXISTS funcionario (
                             cpf VARCHAR(11) NOT NULL PRIMARY KEY,
                             nome VARCHAR(100),
                             nascimento DATE NOT NULL CHECK (nascimento <= CURRENT_DATE),
                             salario_bruto NUMERIC(15,2) CHECK (salario_bruto > 0),
                             id_departamento INT NOT NULL,
                             CONSTRAINT fk_departamento_funcionario
                                 FOREIGN KEY (id_departamento)
                                     REFERENCES departamento(id)
);

CREATE TABLE IF NOT EXISTS dependente (
                            cpf VARCHAR(11) NOT NULL PRIMARY KEY,
                            nome VARCHAR(100),
                            nascimento DATE NOT NULL CHECK (nascimento <= CURRENT_DATE),
                            parentesco VARCHAR(10),
                            cpf_funcionario VARCHAR(11) NOT NULL,
                            CONSTRAINT fk_funcionario_dependente
                                FOREIGN KEY (cpf_funcionario)
                                    REFERENCES funcionario(cpf)
);

CREATE TABLE IF NOT EXISTS folha_pagamento (
                                 id SERIAL PRIMARY KEY,
                                 cpf_funcionario VARCHAR(11),
                                 data DATE NOT NULL CHECK(data <= CURRENT_DATE),
                                 inss NUMERIC(15,2) CHECK (inss >= 0),
                                 ir NUMERIC(15,2) CHECK (ir >= 0),
                                 liquido NUMERIC(15,2) CHECK (liquido >= 0),
                                 CONSTRAINT fk_funcionario_folha_pagamento
                                     FOREIGN KEY (cpf_funcionario)
                                         REFERENCES funcionario(cpf)
);

-- INSERT INTO departamento (nome) VALUES
                                   -- ('Recursos Humanos'),
                                    --('Tecnologia da Informação'),
                                    --('Financeiro'),
                                    --('Operações');