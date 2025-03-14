<div align="center">

### Transaction API

</div>

Este projeto é a resolução de um **[desafio técnico do Itau](https://github.com/rafaellins-itau/desafio-itau-vaga-99-junior)**, da qual foi desenvolvido
 uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. 
 
 A API REST foi desenvolvida com Java e Spring Boot, com banco de dados em memória e testes unitarios com jUnit.

<br>

#

<div align="center">

### Environmental Variables

</div>

To run this application you will need:

`Java`: JDK 21 ou superior.
`Gradle`: Versão 8.12.1 ou superior.
`Git`: Para clonar o repositório.
`Docker` (opcional): Caso queira rodar a aplicação em um container.

<div align="center">

<br>

#

### How to configure the project

</div>

1. Clone the repository

<br>

2. Compile the project

```bash
 gradle clean build
```

<br>

3. Run the project

```bash
./gradlew bootRun
```

<br>

4. How to run with docker (Opcional)

- Create a Docker Image

```bash
docker build -t transaction-api 
```

- Run the container

```bash
docker run -p 8080:8080 transaction-api
```

<br>

#

<div align="center">

### API Documentation

</div>

#### Create Transaction

```http
  POST /transaction
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `value` | `BigDecimal` | **Obrigatório**. O valor da transação 
| `date` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### List Trasactions

```http
  GET /transaction
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `searchRange` | `Integer` | **Não Obrigatório** O padrão default é 60s  |

#### Delete Transactions

```http
  DELETE /transaction
```

#### Calculate Statistics

```http
  GET /statistic
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `searchRange` | `Integer` | **Não Obrigatório** O padrão default é 60s  |

<br><br>

#

<div align="center">

**Developed by © Gabriel Mendes**

<a href="https://www.linkedin.com/in/gabriel-mendes-0706ab1b8" target="_blank"><img src="https://img.shields.io/badge/-Linkedin-blue" width="50px" target="_blank"></a> <a href="https://github.com/Biellms" target="_blank"><img src="https://img.shields.io/badge/-Github-gray" width="43px" target="_blank"></a>

</div>