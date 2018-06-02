package Connectors;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgreSQLConnector {

    private Connection con = null;

//    private final String url = "jdbc:postgresql://localhost:5432/gutenberg";
    private final String url = "jdbc:postgresql://localhost:5433/postgres";
    private final String username = "postgres";
    private final String password = "hejsan";

    public Connection SQLConnector() {
        try {
            con = DriverManager.getConnection(url, username, password);
        } catch (SQLException ex) {
            ex.getSQLState();
        }
        return con;
    }

    public void close() throws SQLException {
        con.close(); 
    }
}
