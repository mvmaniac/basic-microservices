DROP TABLE IF EXISTS users;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS user_organizations;

CREATE  TABLE users (
  user_name VARCHAR(100) NOT NULL ,
  password VARCHAR(100) NOT NULL ,
  enabled boolean NOT NULL ,
  PRIMARY KEY (user_name)
);

CREATE TABLE user_roles (
  user_role_id SERIAL,
  user_name varchar(100) NOT NULL,
  role varchar(100) NOT NULL,
  PRIMARY KEY (user_role_id)
);

CREATE TABLE user_organizations (
  organization_id   VARCHAR(100)  NOT NULL,
  user_name         VARCHAR(100)   NOT NULL,
  PRIMARY KEY (user_name)
);