package br.com.stonks.stonks.helper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoFactory {

    public static final int MYSQL = 0;
    public static final int POSTGRES = 1;
    private static final String MySQLDriver = "com.mysql.cj.jdbc.Driver";
    private static final String PostgreSQLDriver = "org.postgresql.Driver";

    public static String URL = "jdbc:postgresql://localhost:5432/stonks_db";

    private static final String NOME = "postgres";

    private static final String SENHA = "root";

    private static final int BANCO = 1;

    public static Connection conexao() throws ClassNotFoundException, SQLException {
        switch (BANCO) {
            case MYSQL:
                Class.forName(MySQLDriver);
                break;

            case POSTGRES:
                Class.forName(PostgreSQLDriver);
                break;
        }
        return DriverManager.getConnection(URL, NOME, SENHA);
    }
}
