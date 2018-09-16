CREATE TABLE if not exists user (
  id BIGINT NOT NULL AUTO_INCREMENT,
  firstname VARCHAR(60) NOT NULL,
  lastname VARCHAR(70) NOT NULL,
  birthday DATE NOT NULL,
  gender VARCHAR(10) NOT NULL,
  mobile VARCHAR(20),
  username VARCHAR(60) NOT NULL,
  password VARCHAR(100) NOT NULL,
  email VARCHAR(80) NOT NULL,
  account_non_expired SMALLINT,
  account_non_locked SMALLINT,
  credentials_non_expired SMALLINT,
  enabled SMALLINT,
  PRIMARY KEY(id));

ALTER TABLE user ADD UNIQUE (username);

CREATE TABLE if not exists role (
  id BIGINT NOT NULL AUTO_INCREMENT,
  code VARCHAR(60) NOT NULL,
  description VARCHAR(250),
  PRIMARY KEY(id));

CREATE TABLE if not exists permission (
  id BIGINT NOT NULL AUTO_INCREMENT,
  code VARCHAR(120) NOT NULL,
  description VARCHAR(250),
  PRIMARY KEY(id));

CREATE TABLE user_role (
  id BIGINT NOT NULL AUTO_INCREMENT,
  user_id BIGINT NOT NULL,
  role_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_role_user FOREIGN KEY (user_id) REFERENCES  user(id),
  CONSTRAINT fk_user_role FOREIGN KEY (role_id) REFERENCES  role(id)
);

CREATE TABLE role_permission (
  id BIGINT NOT NULL AUTO_INCREMENT,
  role_id BIGINT NOT NULL,
  permission_id BIGINT NOT NULL,
  PRIMARY KEY(id),
  CONSTRAINT fk_per_role FOREIGN KEY (role_id) REFERENCES  role(id),
  CONSTRAINT fk_role_per FOREIGN KEY (permission_id) REFERENCES  permission(id)
);

CREATE TABLE registration_token (
  id BIGINT NOT NULL AUTO_INCREMENT,
  registration_token VARCHAR(200) NOT NULL,
  username VARCHAR(60) NOT NULL,
  email VARCHAR(200) NOT NULL,
  expiry_date DATE NOT NULL,
  PRIMARY KEY (id)
);

## INSERT PERMISSION
INSERT INTO permission (id, code, description) VALUES (1, 'SUPER_ADMIN', 'Can add and delete some admin users');
INSERT INTO permission (id, code, description) VALUES (2, 'USER_MANAGEMENT', 'Can add moderators users, also can add sub Admin user');
INSERT INTO permission (id, code, description) VALUES (3, 'MODERATOR', 'Can moderate site');
INSERT INTO permission (id, code, description) VALUES (4, 'USER', 'Common user on site');
## INSERT ROLE
INSERT INTO role (id, code, description) VALUES (1, 'ADMIN', 'User with full access');
INSERT INTO role (id, code, description) VALUES (2, 'SUB_ADMIN', 'User with almost full access');
INSERT INTO role (id, code, description) VALUES (3, 'MODERATOR', 'User who moderate site');
INSERT INTO role (id, code, description) VALUES (4, 'USER', 'Common user');
## INSERT ROLE PERMISSION
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 1);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (1, 4);

INSERT INTO role_permission (role_id, permission_id) VALUES (2, 2);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (2, 4);

INSERT INTO role_permission (role_id, permission_id) VALUES (3, 3);
INSERT INTO role_permission (role_id, permission_id) VALUES (3, 4);

INSERT INTO role_permission (role_id, permission_id) VALUES (4, 4);







