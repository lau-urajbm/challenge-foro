create table usuarios(
    id bigint not null auto_increment,
    nombre varchar(100) not null,
    correo_electronico varchar(100) not null unique,
    contraseña varchar(100) not null,

    primary key(id)

);

create table topicos(
    id bigint not null auto_increment,
    titulo varchar(100) not null unique,
    mensaje varchar(100) not null unique,
    fecha_creacion datetime not null,
    status varchar(100) not null,
    autor_id bigint not null,
    curso varchar(100) not null,

    constraint fk_topicos_autor_id foreign key(autor_id) references usuarios(id),

    primary key(id)

);

create table respuestas(
    id bigint not null auto_increment,
    topico_id bigint not null,
    mensaje varchar(100) not null,
    fecha_creacion datetime not null,
    autor_id bigint not null,
    solucion varchar(100),

     constraint fk_respuestas_topico_id foreign key(topico_id) references topicos(id),
     constraint fk_respuestas_autor_id foreign key(autor_id) references usuarios(id),
    primary key(id)

);

