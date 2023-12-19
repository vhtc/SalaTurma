PRAGMA foreign_keys=OFF;
BEGIN TRANSACTION;
CREATE TABLE Turma_Aluno (
    turma_id INT,
    aluno_id INT,
    PRIMARY KEY (turma_id, aluno_id),
    FOREIGN KEY (turma_id) REFERENCES Turma(id),
    FOREIGN KEY (aluno_id) REFERENCES Aluno(id)
);
CREATE TABLE Turma_Professor (
    turma_id INT,
    professor_id INT,
    PRIMARY KEY (turma_id, professor_id),
    FOREIGN KEY (turma_id) REFERENCES Turma(id),
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);
CREATE TABLE Turma_Aula (
    turma_id INT,
    aula_id INT,
    PRIMARY KEY (turma_id, aula_id),
    FOREIGN KEY (turma_id) REFERENCES Turma(id),
    FOREIGN KEY (aula_id) REFERENCES Aula(id)
);
CREATE TABLE Aluno (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    idade INTEGER NOT NULL,
    curso TEXT NOT NULL
);
INSERT INTO Aluno VALUES(3,'Gleidson',21,'Desenvolvimento de Sistemas');
CREATE TABLE Professor (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    nome TEXT NOT NULL,
    "disciplina" TEXT NOT NULL
);
INSERT INTO Professor VALUES(1,'Luiz Antonio','Teste de Sistemas');
CREATE TABLE Aula (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    dia TEXT NOT NULL,
    horario TEXT NOT NULL,
    materia TEXT NOT NULL,
    professor_id INTEGER,
    FOREIGN KEY (professor_id) REFERENCES Professor(id)
);
CREATE TABLE Calendario (
    id INTEGER PRIMARY KEY AUTOINCREMENT
    -- Adicione outras colunas conforme necessário
);
CREATE TABLE Turma (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    codigo TEXT NOT NULL
);
DELETE FROM sqlite_sequence;
INSERT INTO sqlite_sequence VALUES('Aluno',3);
INSERT INTO sqlite_sequence VALUES('Professor',1);
COMMIT;
