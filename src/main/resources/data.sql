INSERT IGNORE INTO PROFILE (ID, NAME) VALUES (1, 'ADMIN');
INSERT IGNORE INTO PROFILE (ID, NAME) VALUES (2, 'INSTRUCTOR');
INSERT IGNORE INTO PROFILE (ID, NAME) VALUES (3, 'STUDENT');

-- Inserir o usuário na tabela user_school
--INSERT INTO user_school (email, username, name, password, created_at)
--VALUES ('jaqcueline.oliveira@alura.com', 'jacqoliveira', 'Jacqueline Oliveira', '$2a$10$wBJQGhP5Zb5O1Pv/pDXswe8z2scr46WdyqauIHi6ARLb1UhJm8ajm', CURDATE());

-- Inserir o usuário na tabela user_profile com o papel INSTRUCTOR
--INSERT INTO user_profile (username, email, profile_id)
--VALUES ('jacqoliveira', 'jaqcueline.oliveira@alura.com', 2);


-- Inserir o curso "Java: criando a sua primeira aplicação"
--INSERT INTO course (name, code, instructor_username, instructor_email, description, status, created_at)
--VALUES ('Java: criando a sua primeira aplicação', 'JAVACPA', 'jacqoliveira', 'jaqcueline.oliveira@alura.com',
--        'Este curso aborda os fundamentos do Java e como criar sua primeira aplicação.', true, CURDATE());

-- Inserir o curso "Java: aplicando a Orientação a Objetos"
--INSERT INTO course (name, code, instructor_username, instructor_email, description, status, created_at)
--VALUES ('Java: aplicando a Orientação a Objetos', 'JAVAAOO', 'jacqoliveira', 'jaqcueline.oliveira@alura.com',
--        'Neste curso, aprenda a aplicar os conceitos de Orientação a Objetos em Java.', true, CURDATE());

-- Inserir o curso "Java: trabalhando com listas e coleções de dados"
--INSERT INTO course (name, code, instructor_username, instructor_email, description, status, created_at)
--VALUES ('Java: trabalhando com listas e coleções de dados', 'JAVATLCD', 'jacqoliveira', 'jaqcueline.oliveira@alura.com',
--        'Aprenda a manipular listas e coleções de dados em Java neste curso.', true, CURDATE());

-- Inserir o curso "Java: consumindo API, gravando arquivos e lidando com erros"
--INSERT INTO course (name, code, instructor_username, instructor_email, description, status, created_at)
--VALUES ('Java: consumindo API, gravando arquivos e lidando com erros', 'JAVACAGL', 'jacqoliveira', 'jaqcueline.oliveira@alura.com',
--       'Este curso ensina como consumir APIs, lidar com arquivos e gerenciar erros em Java.', true, CURDATE());
