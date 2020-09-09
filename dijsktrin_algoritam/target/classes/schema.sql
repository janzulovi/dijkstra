drop table if exists createpath;

create table createpath(
    id int auto_increment primary key,
    incidencematrix varchar(255),
    edgeweight varchar(255),
    firstvertex varchar(10),
    lastvertex varchar(10)
);
