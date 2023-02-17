-- Admin password is `adminpassword` encrypted using: https://bcrypt-generator.com/

INSERT INTO member (username, password, role)
VALUES (
    'admin',
    '$2a$12$tqrRVTCuwI84fbFnrsxpIOwpkQMbjviM9eqml4D11RXQTvFr3oNWe',
    'ADMIN'
);
