# Kruger

This project was generated with springboot version 2.6.7

## Development in localhost

Deploy to a development environment via an IDE. Consume the services through the url `http://localhost:8080`,
this component implements security oauth2.


## Add first employee to assing user and role in date base, run scripts

INSERT INTO krug_employee (emp_id_pr, emp_address, emp_birth_date, emp_cedula, emp_email, emp_last_names, emp_names, emp_vaccination, emp_telephone) VALUES (1, '',null, '01928374653', 'juan@gmail.com', 'Perez', 'Juan', false, '');
INSERT INTO krug_user (username, password, enabled, emp_id_fk ) VALUES ('admin','$2a$10$DOMDxjYyfZ/e7RcBfUpzqeaCs8pLgcizuiQWXPkU35nOhZlFcE9MS',true,1);
INSERT INTO krug_role (role_id_pr, authority) VALUES (1,'ROLE_ADMIN');
INSERT INTO krug_user_roles (user_kruger_user_id_pr, roles_role_id_pr) VALUES (1, 1);


## Login with endpoint (http://localhost:8080/oauth/token)

Note: basic auth is:

username:kruger
password:12345

username:admin
password:12345
grant_type:password

## Swanger to test client CRUD
http://localhost:8080/swagger-ui/index.html

## Note 
The collection of the postman file is located in the static courses of the project.