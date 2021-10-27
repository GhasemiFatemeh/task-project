package model.repository.common;

import common.annotation.Connection;
import org.apache.commons.dbcp2.BasicDataSource;

@Connection(user = "QC_TEST",pass = "UUNfVEVTVA==")
public class JDBC{
    private static String userName;
    private static String password;
    private JDBC(){
    }

    public static final int ORACLE_XE = 1;
    public static final int SQL_SERVER = 2;


    private static BasicDataSource oracleBasicDataSource =new BasicDataSource();
    private static BasicDataSource sqlBasicDataSource =new BasicDataSource();
    static {

        userName=JDBC.class.getDeclaredAnnotation(Connection.class).user();
        password=JDBC.class.getDeclaredAnnotation(Connection.class).pass();

        oracleBasicDataSource.setUrl("jdbc:oracle:thin:@localhost:1521:xe");
        oracleBasicDataSource.setUsername(userName);
        oracleBasicDataSource.setPassword(password);
        oracleBasicDataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
        oracleBasicDataSource.setMaxTotal(10);

        sqlBasicDataSource.setUrl("jdbc:sqlserver://94.182.179.17:1433;database=QC_TEST");
        sqlBasicDataSource.setUsername(userName);
        sqlBasicDataSource.setPassword(password);
        sqlBasicDataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        sqlBasicDataSource.setMaxTotal(10);

//        try {
//            userName=JDBC.class.getDeclaredAnnotation(Connection.class).user();
//            password=JDBC.class.getDeclaredAnnotation(Connection.class).pass();
//            Class.forName("oracle.jdbc.driver.OracleDriver");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
    public static java.sql.Connection getConnection(int connectionType) throws Exception{
        switch (connectionType)
        {
            case ORACLE_XE: return oracleBasicDataSource.getConnection();
            default:return  sqlBasicDataSource.getConnection();
        }
//       java.sql.Connection connection= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",userName,password);
     //   return connection;
    }
}
