CREATE SEQUENCE whale_sighting_id_sequence START WITH 1;

CREATE TABLE whale_sighting
(
    id         INT DEFAULT nextval('whale_sighting_id_sequence') PRIMARY KEY,
    member_id  INT NOT NULL,
    FOREIGN KEY (member_id) REFERENCES member(id),
    date       date,
    latitude   bigint,
    longitude  bigint,
    approved   boolean

);