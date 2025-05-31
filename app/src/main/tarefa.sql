-- SQLite schema and sample data for Lista de Tarefa project

-- Drop table if exists
DROP TABLE IF EXISTS tarefas;

-- Create the tarefas (tasks) table
CREATE TABLE tarefas (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    descricao TEXT NOT NULL,
    concluido BOOLEAN NOT NULL DEFAULT 0
);

-- Insert sample data into tarefas table
INSERT INTO tarefas (descricao, concluido) VALUES ('Comprar leite', 1);
INSERT INTO tarefas (descricao, concluido) VALUES ('Estudar Kotlin', 0);
INSERT INTO tarefas (descricao, concluido) VALUES ('Fazer exercícios', 1);
INSERT INTO tarefas (descricao, concluido) VALUES ('Limpar a casa', 0);

-- Query to verify data
SELECT * FROM tarefas;
