-- administrator (password : pass123)
insert into administrator(username, password, name) values('admin', '$2a$10$wJq8RWR.vvweA46Idgcs4OtnuZwQRF/0siKbxx3zItJbNiyF/4iEW', 'choi bu admin');
-- member (password : pass123)
insert into member(username, password, name) values('cbw0916', '$2a$10$wJq8RWR.vvweA46Idgcs4OtnuZwQRF/0siKbxx3zItJbNiyF/4iEW', 'choi bu');
-- role
-- insert into role(id, name) values(1, 'ROLE_ADMIN');
-- member_role
-- insert into member_role(username, role_id) values('cbw0916', 1);

-- test client
insert into oauth_clients(client_id, client_secret, redirect_uri, grant_types, username) values('cbw-client', 'secret123', 'http://localhost:8080/oauth/callback', 'Authorization Code', 'cbw0916');
