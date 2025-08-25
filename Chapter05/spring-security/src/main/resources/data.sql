INSERT INTO users(username, password, enabled) VALUES('user', '$2a$10$uTBb2tN9u.wlPkEqg2KHxOhTlNB1GwpbUzspojKdYlmNElQ1RIhIW', TRUE);
INSERT INTO users(username, password, enabled) VALUES('admin', '$2a$10$uTBb2tN9u.wlPkEqg2KHxOhTlNB1GwpbUzspojKdYlmNElQ1RIhIW', TRUE);

INSERT INTO authorities(username, authority) VALUES('user', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('admin', 'ROLE_USER');
INSERT INTO authorities(username, authority) VALUES('admin', 'ROLE_ADMIN');
