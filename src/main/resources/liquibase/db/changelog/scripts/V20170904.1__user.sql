CREATE TABLE wir_user (
    id SERIAL PRIMARY KEY,
    name VARCHAR(60) NOT NULL,
    birthday DATE,
    gender VARCHAR(10),
    mobile VARCHAR(20),
    username VARCHAR(60),
    password VARCHAR(100),
    email VARCHAR(80) NOT NULL,
    account_non_expired BOOLEAN,
    account_non_locked BOOLEAN,
    credentials_non_expired BOOLEAN,
    enabled BOOLEAN,
    image_url VARCHAR(150),
    email_verified BOOLEAN,
    provider VARCHAR(50) NOT NULL,
    provider_id VARCHAR(20)
);

ALTER TABLE wir_user ADD UNIQUE (email);

CREATE TABLE wir_role (
    id SERIAL PRIMARY KEY,
    code VARCHAR(60) NOT NULL,
    description VARCHAR(250)
);

CREATE TABLE wir_permission (
    id SERIAL PRIMARY KEY,
    code VARCHAR(120) NOT NULL,
    description VARCHAR(250)
);

CREATE TABLE wir_user_role
(
    id      SERIAL PRIMARY KEY,
    user_id SERIAL NOT NULL references wir_user(id),
    role_id SERIAL NOT NULL references wir_role(id)
);

CREATE TABLE wir_role_permission
(
    id            SERIAL PRIMARY KEY,
    role_id       SERIAL NOT NULL references wir_role(id),
    permission_id SERIAL NOT NULL references wir_permission(id)
);

CREATE TABLE wir_registration_token (
    id SERIAL PRIMARY KEY,
    registration_token VARCHAR(200) NOT NULL,
    username VARCHAR(60) NOT NULL,
    email VARCHAR(200) NOT NULL,
    expiry_date DATE NOT NULL
);

-- INSERT PERMISSION
INSERT INTO wir_permission (id, code, description) VALUES (1, 'SUPER_ADMIN', 'Can add and delete some admin users');
INSERT INTO wir_permission (id, code, description) VALUES (2, 'USER_MANAGEMENT', 'Can add moderators users, also can add sub Admin user');
INSERT INTO wir_permission (id, code, description) VALUES (3, 'MODERATOR', 'Can moderate site');
INSERT INTO wir_permission (id, code, description) VALUES (4, 'USER', 'Common user on site');
-- INSERT ROLE
INSERT INTO wir_role (id, code, description) VALUES (1, 'ADMIN', 'User with full access');
INSERT INTO wir_role (id, code, description) VALUES (2, 'SUB_ADMIN', 'User with almost full access');
INSERT INTO wir_role (id, code, description) VALUES (3, 'MODERATOR', 'User who moderate site');
INSERT INTO wir_role (id, code, description) VALUES (4, 'USER', 'Common user');
-- INSERT ROLE PERMISSION
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (1, 4);

INSERT INTO wir_role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (2, 3);
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (2, 4);

INSERT INTO wir_role_permission (role_id, permission_id) VALUES (3, 3);
INSERT INTO wir_role_permission (role_id, permission_id) VALUES (3, 4);

INSERT INTO wir_role_permission (role_id, permission_id) VALUES (4, 4);

