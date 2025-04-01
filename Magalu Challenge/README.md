<div align="center">

### Schedule API

</div>

Este projeto é a resolução de um **[desafio técnico da Magalu](https://github.com/Biellms/CodeChallenges/blob/main/Magalu%20Challenge/Desafio%20T%C3%A9cnico%20API%20Comunica%C3%A7%C3%A3o.pdf)**, da qual foi desenvolvido uma API REST para gerenciar e programar agendamentos. 
 
A API REST foi desenvolvida com Java e Spring Boot, com banco de dados em PostgreSQL e testes unitarios com JUnit.

<br>

#

<div align="center">

### Environmental Variables

</div>

To run this application you will need:

`Java`: JDK 17 ou superior.
`Maven`: Versão 4.0.0 ou superior.
`Git`: Para clonar o repositório.
`Postman or insomnia`: Para executar as requisições.
`Docker` (opcional): Caso queira rodar a aplicação em um container.

<div align="center">

<br>

#

### How to configure the project

</div>

1. Clone the repository

```bash
 git clone https://github.com/Biellms/{link-do-projeto}
```

<br>

2. Compile the project

```bash
 mvn clean package
```

<br>

3. Run the project

```bash
mvn spring-boot:run
```

<br>

4. Test the project (Opcional)

```bash
mvn test
```

<br>

5. How to run with docker (Opcional)

- Create a Docker Compose

```bash
docker-compose up --build
```

- Run the docker compose

```bash
docker-compose up -d
```

<br>

#

<div align="center">

### API Documentation

</div>

```http
  POST /schedule
```

| Param   | Type       | Description                           |
| :---------- | :--------- | :---------------------------------- |
| `emailDestinatary` | `string` | **Required**. Email do destinatário do agendamento |
| `phoneDestinatary` | `string` | **Required**. Telefone do destinatário do agendamento |
| `message` | `string` | **Required**. Mensagem do agendamento |
| `dateHourSend` | `LocalDateTime` | **Required**. Data hora do envio no formato dd-MM-yyyy HH:mm:ss |

#### Return a schedule by id

```http
  GET /schedule/${id}
```

| Param   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Required**. O ID do agendamento que você quer buscar |

#### Cancel a schedule by id

```http
  DELETE /schedule/${id}
```

| Param   | Type       | Description                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `string` | **Required**. O ID do agendamento que você quer cancelar |

<br><br>

#

<div align="center">

**Developed by © Gabriel Mendes**

<a href="https://www.linkedin.com/in/gabriel-mendes-0706ab1b8" target="_blank"><img src="https://img.shields.io/badge/-Linkedin-blue" width="50px" target="_blank"></a> <a href="https://github.com/Biellms" target="_blank"><img src="https://img.shields.io/badge/-Github-gray" width="43px" target="_blank"></a>

</div>