CREATE TABLE EXPLORERS(
    ID SERIAL primary key,
    FIRST_NAME text not null,
    LAST_NAME text not null,
    EMAIL text not null unique,
    ROLE text,
    STATUS text not null,
    CREATED_BY text not null,
    CREATED_AT TIMESTAMP not null,
    UPDATED_BY text not null,
    UPDATED_AT TIMESTAMP not null
);

CREATE TABLE ROLES(
    ID SERIAL primary key,
    ROLE text not null,
    STATUS text not null,
    CREATED_BY text not null,
    CREATED_AT TIMESTAMP not null,
    UPDATED_BY text not null,
    UPDATED_AT TIMESTAMP not null
);
--Create three default roles to start with
INSERT INTO ROLES (ROLE, STATUS, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT) VALUES ('ADMIN', 'ACTIVE', 'ADMIN', CURRENT_TIMESTAMP, 'ADMIN', CURRENT_TIMESTAMP);
INSERT INTO ROLES (ROLE, STATUS, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT) VALUES ('OWNER', 'ACTIVE', 'ADMIN', CURRENT_TIMESTAMP, 'ADMIN', CURRENT_TIMESTAMP);
INSERT INTO ROLES (ROLE, STATUS, CREATED_BY, CREATED_AT, UPDATED_BY, UPDATED_AT) VALUES ('USER', 'ACTIVE', 'ADMIN', CURRENT_TIMESTAMP, 'ADMIN', CURRENT_TIMESTAMP);

CREATE TABLE TEAMS(
    ID SERIAL primary key,
    NAME text not null unique,
    STATUS text not null,
    CREATED_BY text not null,
    CREATED_AT TIMESTAMP not null,
    UPDATED_BY text not null,
    UPDATED_AT TIMESTAMP not null
);

CREATE TABLE EXPLORER_TEAM(
    TEAM_ID integer not null references TEAMS(ID),
    EXPLORER_ID integer not null references EXPLORERS(ID),
    STATUS text not null,
    CREATED_BY text not null,
    CREATED_AT TIMESTAMP not null,
    UPDATED_BY text not null,
    UPDATED_AT TIMESTAMP not null
);

--Dummy users for creating other users
INSERT INTO "tafel-explorers".explorers(
	 first_name, last_name, email, role, status, created_by, created_at, updated_by, updated_at)
	VALUES ('ADMIN', 'JOBS' , 'admin@jobs.com', 'ADMIN', 'ACTIVE', 'ADMIN', CURRENT_TIMESTAMP, 'ADMIN', CURRENT_TIMESTAMP);

INSERT INTO "tafel-explorers".explorers(
	 first_name, last_name, email, role, status, created_by, created_at, updated_by, updated_at)
	VALUES ('SHELDON', 'COOP' , 'shel@coop.com', 'ADMIN', 'ACTIVE', 'ADMIN', CURRENT_TIMESTAMP, 'ADMIN', CURRENT_TIMESTAMP);

INSERT INTO "tafel-explorers".teams(
	name, status, created_by, created_at, updated_by, updated_at)
	VALUES ('MASTER-SHIP', 'ACTIVE', 'ADMIN',  CURRENT_TIMESTAMP, 'ADMIN', CURRENT_TIMESTAMP);