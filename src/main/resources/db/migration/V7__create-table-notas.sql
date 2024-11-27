CREATE TABLE notas(
    id int not null primary key auto_increment,
    matricula_id int,
    disciplina_id int,
    nota decimal(10,2),
    data_lancamento date
)