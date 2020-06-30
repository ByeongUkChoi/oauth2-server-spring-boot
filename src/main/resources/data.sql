insert into member(member_id, username, password, name) values(90001, 'cbw0916', 'pass123', 'choi bu');

insert into client(client_id, client_secret, redirect_uri, grant_type, member_id) values('cbw-client', 'secret123', '', 'Authorization Code', 90001);
