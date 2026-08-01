CREATE TABLE users (
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
    username VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    authority VARCHAR(100) NOT NULL
);

CREATE TABLE clients (
     id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
     client_id VARCHAR(100) NOT NULL UNIQUE,
     client_secret VARCHAR(255) NOT NULL,
     scope VARCHAR(255),
     auth_method VARCHAR(100),
     grant_types VARCHAR(100),
     redirect_uri VARCHAR(500)
);