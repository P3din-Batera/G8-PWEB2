package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String URL = "jdbc:h2:file:~/sistema_sla;AUTO_SERVER=TRUE;AUTO_SERVER_PORT=9093";
    private static final String USER = "sa";
    private static final String PASS = "";

    static {
        try { Class.forName("org.h2.Driver"); System.out.println("H2 driver loaded"); }
        catch (ClassNotFoundException e) { e.printStackTrace(); }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }
}
