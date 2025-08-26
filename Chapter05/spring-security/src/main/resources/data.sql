
--for JdbcUserDetailsManager
INSERT INTO users(username, password, enabled) VALUES('user', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e', TRUE);
INSERT INTO users(username, password, enabled) VALUES('admin', '$2a$12$hYUqLSsCfWsFxQd9w8AUOeJvXx4ICsK/inDl5rcoN4/FUdNHE33.e', TRUE);

--for JdbcUserDetailsManager
INSERT INTO authorities(username, authority) VALUES('user', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('admin', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('admin', 'ROLE_ADMIN');