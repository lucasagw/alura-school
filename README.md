<h1 align="center">
  Case Técnico Alura
</h1>

<p align="center">
 <img src="https://img.shields.io/static/v1?label=Tipo&message=Desafio&color=8257E5&labelColor=000000" alt="Desafio" />
</p>

Projeto elaborado para solucionar [esse desafio](https://drive.google.com/file/d/11Cz8dviGSYwUpMqwnAneuIQ8LSGB6ngU/view?usp=sharing) para uma vaga backend.

## Tecnologias

- [Spring Boot](https://spring.io/projects/spring-boot)
- [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
- [Docker Compose](https://docs.docker.com/compose/)
- [MySQL](https://www.mysql.com/)

## Como Executar

- Clonar repositório git:
```
git clone https://github.com/lucasagw/alura-school.git
```
- Executar o comando abaixo para criar o arquivo .jar da aplicação Spring Boot:
```
mvn clean package -DskipTests
```
- Navegar até o diretório docker:
```
cd docker
```
- Executar o comando abaixo para subir o container do MySQL e da aplicação Spring Boot:
```
docker-compose up
```
- Acessar aplicação em `http://localhost:8081`.

## API

- Acessar o diretório 'doc' para visualizar a coleção de requisições da API no Postman.

## Diagrama de Entidade e Relacionamento (ER)

![Diagrama de Entidade e Relacionamento (ER)](https://i.ibb.co/hHDCNtX/aluraschool.png)



