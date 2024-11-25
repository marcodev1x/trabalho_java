CREATE TABLE disciplinas(
    id int primary key not null auto_increment,
    nome varchar(100),
    codigo varchar(20),
    curso_id int,
    professor_id int,
    FOREIGN KEY (curso_id) REFERENCES cursos(id),
    FOREIGN KEY (professor_id) REFERENCES professores(id)
)