DROP TABLE IF EXISTS special_route;

CREATE TABLE special_route (
  service_name      VARCHAR(100) PRIMARY KEY NOT NULL,
  active            VARCHAR(1) NOT NULL,
  endpoint          VARCHAR(100) NOT NULL,
  weight            INT
);
