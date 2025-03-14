
# Transação API

Este projeto é uma API REST para gerenciar transações e calcular estatísticas das transações realizadas nos últimos 60 segundos. A API foi desenvolvida com Java e Spring Boot.

## Variáveis de Ambiente

Para rodar esta aplicação, você precisa de:

Java: JDK 21 ou superior.
Maven: Versão 3.8.1 ou superior.
Git: Para clonar o repositório.
Docker (opcional): Caso queira rodar a aplicação em um container.

##  Como Configurar o Projeto

1. Clone o Repositório

2. Compile o Projeto

```bash
 gradle clean build
```

3. Execute o Projeto

```bash
./gradlew bootRun
```
4. Como Rodar em um Container (Opcional)

4.1. Crie a Imagem Docker
Certifique-se de que o Docker está instalado e execute:

```bash
docker build -t transaction-api 
```

4.2. Execute o Container

```bash
docker run -p 8080:8080 transaction-api
```

## Documentação da API

#### Receber Transações

```http
  POST /transacao
```

| Parâmetro   | Tipo       | Descrição                           |
| :---------- | :--------- | :---------------------------------- |
| `valor` | `BigDecimal` | **Obrigatório**. O valor da transação 
| `dataHora` | `OffsetDateTime` | **Obrigatório**. O horário que a transação ocorreu

#### Limpar Transações

```http
  DELETE /transacao
```

#### Calcular Estatísticas

```http
  GET /estatistica
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `intervaloSegundos` | `integer` | **Não Obrigatório** O padrão default é 60s  |

<br>

#

<div align="center">

**Developed by © Gabriel Mendes**

<a href="https://www.linkedin.com/in/gabriel-mendes-0706ab1b8" target="_blank"><img src="https://img.shields.io/badge/-Linkedin-blue" width="50px" target="_blank"></a> <a href="https://github.com/Biellms" target="_blank"><img src="https://img.shields.io/badge/-Github-gray" width="43px" target="_blank"></a>

</div>