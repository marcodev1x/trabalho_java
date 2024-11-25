CREATE TABLE matriculas(
    id int not null primary key auto_increment,
    turma_id int,
    aluno_id int,
    FOREIGN KEY (turma_id) REFERENCES turmas(id),
    FOREIGN KEY (aluno_id) REFERENCES alunos(id)
)