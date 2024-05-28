create table contas (
id SERIAL,
descricao varchar(255),
data_vencimento date,
data_pagamento date,
valor numeric(38,2),
situacao varchar (20),
credor_id bigint
)