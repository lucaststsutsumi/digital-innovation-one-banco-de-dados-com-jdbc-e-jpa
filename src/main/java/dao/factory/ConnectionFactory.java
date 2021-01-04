package dao.factory;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionFactory {
    private static final String BANK_DEFAULT = "mysql.connection.properties";

    public static Connection getConnection() {
        return createConnection(BANK_DEFAULT);
    }

    public static Connection getConnection(BankEnum nomeBanco) {
        String dbPropertyFile = null;

        if (nomeBanco.equals(BankEnum.MYSQL)) {
            dbPropertyFile = "mysql.connection.properties";
        } else if (nomeBanco.equals(BankEnum.POSTGRES)) {
            dbPropertyFile = "postgres.connection.properties";
        }

        return createConnection(dbPropertyFile);
    }

    /**
     * @param fileName nome do arquivo de configuração
     * @return retorna uma conexão do banco com base nas informações passadas por parâmetro ou null em caso de falha
     */
    private static Connection createConnection(String fileName) {
        try (InputStream is = ConnectionFactory.class.getClassLoader().getResourceAsStream(fileName)) {

            // definir parâmetros  para se conectar ao banco de dados DAO.factory.mysql;
            Properties prop = new Properties();
            prop.load(is);

            String driver = prop.getProperty("jdbc.driver");
            String dataBaseAddress = prop.getProperty("db.address");
            String dataBaseName = prop.getProperty("db.name");
            String user = prop.getProperty("db.user.login");
            String password = prop.getProperty("db.user.password");

            String userTimezone = prop.getProperty("db.userTimezone");
            String serverTimezone = prop.getProperty("db.serverTimezone");

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
            // Class.forName("com.DAO.factory.mysql.cj.jdbc.Driver");

            // Criar conexão usando o driverManager, passando como parâmetros a string de conexão, usuário e senha;
            return DriverManager.getConnection(connectionUrl, user, password);

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            return null;
        }

    }

}

