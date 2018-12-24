package db;

import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author: zilla0148
 * @date: 2018/12/17 16:43
 */
public class DBInterface {
    public static boolean isTableExist(Connection con, String tableName, ArrayList<String> log) {
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
            Logger.getLogger( DBInterface.class.getName() ).log( Level.SEVERE, null, ex );
            return false;
        }
    }

    public static void createSystemTables(Connection connection, ArrayList<String> log) {
        try {
            if (!isTableExist(connection, "userInfo", log)) {
                createTable(connection, "userInfo",
                        "username VARCHAR(20) NOT NULL,\n" +
                                "password VARCHAR(40) NOT NULL,\n" +
                                "PRIMARY KEY (username)\n", log);
            }
            if (!isTableExist(connection, "post", log)) {
                createTable(connection, "post",
                        "postID VARCHAR(20) NOT NULL,"+
                                "username VARCHAR(40) NOT NULL,\n" +
                                "hasImg TINYINT(1) NOT NULL DEFAULT 0,\n"+
                                "content TEXT,\n"+
                                "updateTime TIMESTAMP DEFAULT CURRENT_TIMESTAMP,\n" +
                                "PRIMARY KEY (postID),\n"+
                                "FOREIGN KEY(username) REFERENCES userInfo(username)", log);
            }
            if (!isTableExist(connection, "post_img", log)) {
                createTable(connection, "post_img",
                        "postID VARCHAR(20) NOT NULL,\n" +
                                "imgUrl VARCHAR(40) NOT NULL,\n"+
                                "FOREIGN KEY(postID) REFERENCES post(postID)", log);
            }
        } finally {
            for (String str : log) {
                System.out.println( str );
            }
        }
    }

    public static void createTable(Connection con, String tableName, String body, ArrayList<String> log) {
        try {
            Statement stmt = con.createStatement();
            stmt.executeUpdate( "CREATE TABLE " + tableName + "(" + body + ")" );
            log.add( tableName + " is created." );
        } catch (SQLException ex) {
            Logger.getLogger( DBInterface.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public static void dropTable(Connection con, String tableName, ArrayList<String> log) {
        try {
            Statement stmt = con.createStatement();
            if (stmt.executeUpdate( "DROP TABLE IF EXISTS " + tableName ) != 0) {
                log.add( tableName + " is deleted." );
            }
        } catch (SQLException ex) {
            Logger.getLogger( DBInterface.class.getName() ).log( Level.SEVERE, null, ex );
        }
    }

    public static ResultSet getResultSet(String str){
        try {
            Connection con = DBConnector.getInstance().getConnection();
            Statement stmt = con.createStatement();
            return stmt.executeQuery(str);
        } catch (SQLException ex) {
            Logger.getLogger(DBInterface.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public static boolean executeStatement(String str) throws SQLException {
        Connection con = DBConnector.getInstance().getConnection();
        Statement st = con.createStatement();
        st.executeUpdate( str );
        return true;
    }
}
