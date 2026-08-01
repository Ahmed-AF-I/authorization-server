insert into users (username, password, authority)
values ('bill',
        '$2a$12$XnlXDYhI8qHlODqzHV3zMufH/KPqz2qF7H./Oygf8hlu1jkKfARFm',
        'read');

insert into clients(client_id,
                                client_secret,
                                scope,
                                auth_method,
                                grant_types,
                                redirect_uri)
VALUES (
        'client',
    '$2a$12$ergMLPxIkvyfZoqqqGCHqOBBJ6WtBKhujA679FdTWLY5eLtdFUMtq',
        'openid',
        'client_secret_basic',
        'authorization_code',
        'https://springone.io/authorized'
       );