package db;

import home.ConstantSetting;

import java.sql.*;
import java.util.ArrayList;

/**
 * @author: zilla0148
 * @date: 2018/12/19 20:23
 */
public class DBCreator {
    public static void main(String[] args) throws SQLException {
        if (DBConnector.getInstance().getConnection() == null || DBConnector.getInstance().getConnection().isClosed()) {
            Connection connection = DBConnector.getInstance().createConnection( ConstantSetting.DB_URL,
                    ConstantSetting.DB_USER,
                    ConstantSetting.DB_PASSWORD,
                    "" );
            if (connection != null) {
                ArrayList<String> log = new ArrayList<>();
                Connection con = DBConnector.getInstance().getConnection();
                Statement st = con.createStatement();
                st.executeUpdate( "CREATE DATABASE timelineSys character set utf8mb4;" );
                connection = DBConnector.getInstance().createConnection( ConstantSetting.DB_URL,
                        ConstantSetting.DB_USER,
                        ConstantSetting.DB_PASSWORD,
                        ConstantSetting.DB_NAME );
                createSystemTables( connection, log );
            } else {
                System.out.println( "connection failed!" );
            }
        }
    }

    private static boolean isTableExist(Connection con, String tableName, ArrayList<String> log) {
        try {
            DatabaseMetaData dbm = con.getMetaData();
            ResultSet tables = dbm.getTables( null, null, tableName, null );
            if (tables.next()) {
                log.add( tableName + " exists." );
                return true;
            }
            log.add( tableName + " doesn't exist." );
            return false;
        } catch (SQLException ex) {
            ex.printStackTrace();
            return false;
        }
    }

    private static void createSystemTables(Connection connection, ArrayList<String> log) {
        try {
            if (!isTableExist( connection, "userInfo", log )) {
                createTable( connection, "userInfo",
                        "username VARCHAR(20) NOT NULL,\n" +
                                "password VARCHAR(40) NOT NULL,\n" +
                                "PRIMARY KEY (username)\n", log );
            }
            if (!isTableExist( connection, "post", log )) {
                createTable( connection, "post",
                        "postID VARCHAR(20) NOT NULL," +
                                "username VARCHAR(40) NOT NULL,\n" +
                                "hasImg TINYINT(1) NOT NULL DEFAULT 0,\n" +
                                "content TEXT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci,\n" +
                                "updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                                "PRIMARY KEY (postID),\n" +
                                "FOREIGN KEY(username) REFERENCES userInfo(username)", log );
            }
            if (!isTableExist( connection, "post_img", log )) {
                createTable( connection, "post_img",
                        "postID VARCHAR(20) NOT NULL,\n" +
                                "imgUrl VARCHAR(40) NOT NULL,\n" +
                                "FOREIGN KEY(postID) REFERENCES post(postID)", log );
            }
        } finally {
            for (String str : log) {
                System.out.println( str );
            }
        }
    }

    private static void createTable(Connection con, String tableName, String body, ArrayList<String> log) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate( "CREATE TABLE " + tableName + "(" + body + ")" );
            log.add( tableName + " is created." );
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

}