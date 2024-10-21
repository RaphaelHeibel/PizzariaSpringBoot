CREATE TABLE IF NOT EXISTS usuarios(
    id bigint not null auto_increment,
    login VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,

    primary Key(id)
);