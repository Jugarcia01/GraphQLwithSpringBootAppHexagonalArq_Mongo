--CREATE DATABASE IF NOT EXISTS storeDB;
--DROP DATABASE storeDB;
--CREATE DATABASE IF NOT EXISTS storeDB TEMPLATE template0;
SELECT 'CREATE DATABASE storeDB TEMPLATE template0' WHERE NOT EXISTS (SELECT FROM pg_database WHERE datname = 'storeDB');

DROP TABLE IF EXISTS CUSTOMER;

CREATE TABLE CUSTOMER (
  id UUID PRIMARY KEY,
  email VARCHAR(100) NOT NULL,
  name VARCHAR(50) NOT NULL,
  lastName VARCHAR(50) NOT NULL
);

--INSERT INTO CUSTOMER (id, email, name, lastName) VALUES
--('729c5d13-261a-4b46-bb11-9af04cb2cd3e'::uuid, 'bbunny@mail.com', 'Bugs', 'Bunny'),
--('729c5d13-261a-4b46-bb11-9af04cb2cd3f'::uuid, 'lbunny@mail.com', 'Lola', 'Bunny'),
--('1adfa52b-a57c-3b40-8da4-388526f6595b'::uuid, 'plucas@mail.com', 'Pato', 'Lucas'),
--('1adfa52b-a57c-3b40-8da4-388526f6595c'::uuid, 'sgonzales@mail.com', 'Speedy', 'Gonzales'),
--('729c5d13-261a-4b46-bb11-9af04cb2cd3a'::uuid, 'plepew@mail.com', 'Pepe', 'Le Pew'),
--('1adfa52b-a57c-3b40-8da4-388526f6595d'::uuid, 'ddtasmania@mail.com', 'Demonio', 'De Tasmania'),
--('14e46437-1bdd-4a8e-ac81-085d286e7aba'::uuid, 'jgo@mail.com', 'Julian', 'Garcia O');