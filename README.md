# Desafio Beblue Engenheiro back-end


O  projeto foi feito em Java com Spring boot se conectando com o banco de dados PostgreSQL.

O banco é executado em um container docker. Além do banco, são criados mais dois containers: um com o pgAdmin e outro com o portainer.

Para executar o projeto, precisa ter instalado o Java 8, Maven 3, docker e o docker-compose.


## Build e execução do projeto

##### Passo 1

Após o clone do repositório, entre na pasta do projeto:

```bash
$ cd Desafio_Beblue_Engenheiro-Back-End_Victor_Napoles
```

##### Passo 2

Execute o build do docker-compose para iniciar os containers:

```bash
$ docker-compose up
```
##### Passo 3

Em outra janela do terminal, obtenha o id do container do PostgresSQL:

```bash
$ docker inspect -f '{{range .NetworkSettings.Networks}}{{.IPAddress}}{{end}}' desafio_beblue_engenheiro-back-end_victor_napoles_db_1
```

##### Passo 4

Copie a o ip do container e altere a url do datasource no arquivo `src/main/resources/application.properties` na propriedade `spring.datasource.url`.

```bash
spring.datasource.url= jdbc:postgresql://'172.24.0.3':5432/cashback
```

##### Passo 5

Execute o build do Maven:

```bash
mvn clean package spring-boot:run
```

## Links

##### PgAdmin
http://localhost

Usuário: postgres@beblue.com.br
Senha: 123456

Host Server: db
Usuário: postgres
Senha: 123456

##### Portanier
http://localhost:9000

Usuário criado no primeiro acesso.

Após a criação do usuário selecionar conexão local.


##### SwaggerUI
http://localhost:8080/swagger-ui.html
