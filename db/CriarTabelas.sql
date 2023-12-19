-- Tabela Aluno
CREATE TABLE IF NOT EXISTS Aluno (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    idade INTEGER NOT NULL,
    curso TEXT NOT NULL
);

-- Tabela Professor
CREATE TABLE IF NOT EXISTS Professor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    materia TEXT NOT NULL
);

-- Tabela Aula
CREATE TABLE IF NOT EXISTS Aula (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dia TEXT NOT NULL,
    horario TEXT NOT NULL,
    materia TEXT NOT NULL,
    professor_id INTEGER,
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);

-- Tabela Calendario
CREATE TABLE IF NOT EXISTS Calendario (
    id INTEGER PRIMARY KEY AUTOINCREMENT
    -- Adicione outras colunas conforme necessário
);

-- Tabela Turma
CREATE TABLE IF NOT EXISTS Turma (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL
);

-- Tabela que representa a relação entre Turma e Aluno (muitos para muitos)
CREATE TABLE IF NOT EXISTS Turma_Aluno (
    turma_id INTEGER,
    aluno_id INTEGER,
    PRIMARY KEY (turma_id, aluno_id),
    FOREIGN KEY (turma_id) REFERENCES Turma(id),
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id)
);

-- Tabela que representa a relação entre Turma e Professor (muitos para muitos)
CREATE TABLE IF NOT EXISTS Turma_Professor (
    turma_id INTEGER,
    professor_id INTEGER,
    PRIMARY KEY (turma_id, professor_id),
    FOREIGN KEY (turma_id) REFERENCES Turma(id),
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);

-- Tabela que representa a relação entre Turma e Aula (muitos para muitos)
CREATE TABLE IF NOT EXISTS Turma_Aula (
    turma_id INTEGER,
    aula_id INTEGER,
    PRIMARY KEY (turma_id, aula_id),
    FOREIGN KEY (turma_id) REFERENCES Turma(id),
    FOREIGN KEY (aula_id) REFERENCES Aula(id)
);
