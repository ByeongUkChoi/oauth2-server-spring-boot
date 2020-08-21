-- administrator
insert into administrator(username, password, name) values('admin', 'pass123', 'choi bu admin');
-- member
insert into member(username, password, name) values('cbw0916', 'pass123', 'choi bu');
-- role
-- insert into role(id, name) values(1, 'ROLE_ADMIN');
-- member_role
-- insert into member_role(username, role_id) values('cbw0916', 1);

-- test client
insert into oauth_clients(client_id, client_secret, redirect_uri, grant_types, username) values('cbw-client', 'secret123', 'http://localhost:8080/oauth/callback', 'Authorization Code', 'cbw0916');
