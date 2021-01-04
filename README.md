# Banco de dados com JDBC e JPA

## Tópicos

1. Introdução ao JDBC;
    - Configurar banco de dados;
    - JDBC e Drivers de conexão;
    - Consultas com JDBC;
2. Trabalhando com JPA;
    - Entendendo o que é JPA;
    - implementações do JPA;
    - Linguagens de consulta orientada a objetos;

## Pré-requesitos;

- Mysql(SGBD) e noções de SQL;
- Java Development Kit (JDK) - 1.8 ou superior;
- Intellij - IDE;
- Gradle 5.3.1 (para baixar o Driver JDBC)

Issues

- Error: Client does not support authentication protocol requested by server; consider upgrading MySQL client;
    1. [Solução](https://stackoverflow.com/questions/50424900/error-client-does-not-support-authentication-protocol-requested-by-server-cons)
       ;
- Caused by: com.dao.factory.mysql.cj.exceptions.InvalidConnectionAttributeException: The server time zone value 'Hora
  oficial do Brasil' is unrecognized or represents more than one time zone. You must configure either the server or JDBC
  driver (
  via the 'serverTimezone' configuration property) to use a more specific time zone value if you want to utilize time
  zone support.
    1. [Solução](https://www.guj.com.br/t/resolvido-erro-fuso-horario-DAO.factory.mysql/344446)