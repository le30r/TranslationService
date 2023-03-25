CREATE TABLE REQUEST
(
    id BIGINT primary key auto_increment,
    input VARCHAR(MAX) not null,
    output VARCHAR(MAX) not null,
    timestamp BIGINT not null,
    ip_address varchar(15)
);

CREATE TABLE PARAMETER(
    request_id BIGINT NOT NULL,
    parameter_name VARCHAR(255) NOT NULL,
    parameter_value VARCHAR(255) NOT NULL,
    PRIMARY KEY (request_id, parameter_name),
    FOREIGN KEY (request_id) REFERENCES REQUEST(id)
);

CREATE TABLE WORD (
     id BIGINT NOT NULL,
     request_id BIGINT NOT NULL,
     word VARCHAR(255) NOT NULL,
     translation VARCHAR(255) NOT NULL,
     PRIMARY KEY (request_id, id),
     FOREIGN KEY (request_id) REFERENCES REQUEST(id)
);