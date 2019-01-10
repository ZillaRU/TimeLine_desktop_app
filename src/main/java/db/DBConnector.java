package db;

import home.ConstantSetting;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @author: zilla0148
 * @date: 2018/12/17 16:43
 */
public class DBConnector {
    private final static DBConnector INSTANCE = new DBConnector();
    private Connection conn = null;

    public static DBConnector getInstance() {
        return INSTANCE;
    }

    public Connection getConnection() {
        return conn;
    }

    public Connection createConnection(String url, String user, String password, String database) throws SQLException {
        System.out.println( "createConnection" );
        if (conn != null) {
            System.out.println( "conn != null" );
            return conn;
        }
        try {
            System.out.println( "try" );
            Properties connectionProps = new Properties();
            connectionProps.put( "user", user );
            connectionProps.put( "password", password );
            connectionProps.put( "database", database );
            conn = DriverManager.getConnection( url + database + ConstantSetting.DB_ENCODE, connectionProps );
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }
        return conn;
    }
}
