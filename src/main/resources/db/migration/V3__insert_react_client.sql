INSERT INTO clients (
    client_id,
    client_secret,
    scope,
    auth_method,
    grant_types,
    redirect_uri
)
VALUES (
           'react-client',
           '',
           'openid',
           'none',
           'authorization_code',
           'http://127.0.0.1:3000/callback'
       );