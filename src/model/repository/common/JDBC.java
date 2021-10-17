package model.repository.common;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBC {
    private JDBC(){
    }
    static {
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws Exception{
        Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","ghasemi","myjava123");
        return connection;
    }
}
