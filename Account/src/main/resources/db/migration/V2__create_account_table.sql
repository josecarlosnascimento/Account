create table contas (
id SERIAL,
descricao varchar(255),
data_vencimento date NOT NULL,
data_pagamento date,
valor numeric(38,2) NOT NULL,
situacao varchar (20) NOT NULL,
credor_id bigint
)