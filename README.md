# Edimca

This project was generated with springboot version 2.6.4

## Development in localhost

Deploy to a development environment via an IDE. Consume the services through the url `http://localhost:8080`,
this component implements JSON Web Token.

Note: add web client url in properties file (Ej:http://localhost:4200, client angular)

## Add user and role in the table EDIM_USER and EDIM_ROLE, run scripts

INSERT INTO edim_user (username, password, enabled) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',true);
INSERT INTO edim_role (user_id_fk, authority) VALUES (1,'ROLE_ADMIN');

## Login
username:admin
password:12345
