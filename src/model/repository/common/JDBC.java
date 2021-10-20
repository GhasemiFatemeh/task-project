package model.repository.common;

import common.annotation.Connection;
import org.apache.commons.dbcp2.BasicDataSource;

import java.sql.DriverManager;
@Connection(user = "ghasemi")
public class JDBC{
    private static String userName;
    private static String password;
    private JDBC(){
    }
    private static BasicDataSource basicDataSource=new BasicDataSource();
    static {
        basicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        basicDataSource.setUsername("ghasemi");
        basicDataSource.setPassword("myjava123");
        basicDataSource.setDriverClassName("oracle.jdbc.driver.oracleDriver");
        basicDataSource.setMaxTotal(10);

       /* try {
            userName=JDBC.class.getDeclaredAnnotation(Connection.class).user();
            password=JDBC.class.getDeclaredAnnotation(Connection.class).pass();
            Class.forName("oracle.jdbc.driver.OracleDriver");

        } catch (Exception e) {
            e.printStackTrace();
        } */
    }
    public static java.sql.Connection getConnection() throws Exception{
        return basicDataSource.getConnection();

      /*  java.sql.Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",userName,password);
        return connection; */
    }
}
