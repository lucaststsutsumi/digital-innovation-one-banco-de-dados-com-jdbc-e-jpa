package aulas.part1;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JdbcPrimeiraAula {
    public static void main(String[] args) throws ClassNotFoundException {
        // aula
        Connection com = getConnectionOrganizedDefault();
        // 1. Criar outro usuário do DB e senha e se conectar através da api JDBC;
        Connection com2 = getConnectionOrganized("lucas", "20126028");
    }

    /**
     * @return retorna uma conexão DAO.factory.mysql com o usuário root ou null em caso de falha
     */
    public static Connection getConnectionOrganizedDefault() {
        String user = "root";
        String password = "1234";

        try {
            return getConnectionOrganized(user, password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @param user     nome do usuário DAO.factory.mysql
     * @param password senha do usuário DAO.factory.mysql
     * @return retorna uma conexão DAO.factory.mysql com base nas informações passadas por parâmetro ou null em caso de falha
     */
    public static Connection getConnectionOrganized(String user, String password) throws ClassNotFoundException {
        // definir parametros  para se conectar ao banco de dados DAO.factory.mysql;
        String driver = "mysql";
        String dataBaseAddress = "localhost";
        String dataBaseName = "digital_innovation_one";

        String userTimezone = "useTimezone=true";
        String serverTimezone = "serverTimezone=UTC";
        // construção da string de conexão.
        StringBuilder sb = new StringBuilder("jdbc:")
                .append(driver).append("://")
                .append(dataBaseAddress).append("/")
                .append(dataBaseName)
                .append("?")
                .append(userTimezone).append("&")
                .append(serverTimezone);

        String connectionUrl = sb.toString();// mysql://localhost/digital_innovation_one

        // carregar a classe específica de implementação do driver de memória da JVM.(a partir da versão 4 é mais necessário!!)
        // Class.forName("com.DAO.factory.mysql.jdbc.Driver")

        // Criar conexão usando o driverManager, passando como parâmetros a string de conexão, usuário e senha;
        Class.forName("com.mysql.cj.jdbc.Driver");

        try (Connection com = DriverManager.getConnection(connectionUrl, user, password)) {
            System.out.println("sucesso");
            return com;
        } catch (SQLException throwables) {
            System.out.println("falha");
            throwables.printStackTrace();
            return null;
        }
    }

}
