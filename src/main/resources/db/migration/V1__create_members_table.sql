CREATE SEQUENCE member_id_sequence START WITH 1;

CREATE TABLE member
(
    id        INT DEFAULT nextval('member_id_sequence') PRIMARY KEY,
    username  TEXT NOT NULL UNIQUE,
    password  TEXT,
    role      TEXT
);