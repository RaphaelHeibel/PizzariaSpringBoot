

CREATE TABLE IF NOT EXISTS PIZZA(
    id bigint not null auto_increment,
    nome VARCHAR(255) NOT NULL,
    preco DOUBLE PRECISION NOT NULL,
    disponivel BOOLEAN NOT NULL,
    tamanho VARCHAR(50) NOT NULL,
    sabor VARCHAR(50) NOT NULL,

    primary Key(id)
);