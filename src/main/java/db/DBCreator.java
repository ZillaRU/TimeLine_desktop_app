package db;

import home.ConstantSetting;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author: zilla0148
 * @date: 2018/12/19 20:23
 */
public class DBCreator {
    public static void main(String[] args) throws SQLException {
        if (DBConnector.getInstance().getConnection() == null || DBConnector.getInstance().getConnection().isClosed()){
            Connection connection = DBConnector.getInstance().createConnection(ConstantSetting.DB_URL,
                    ConstantSetting.DB_USER,
                    ConstantSetting.DB_PASSWORD,
                    "");
            if (connection != null){
                ArrayList<String> log = new ArrayList<>();
                DBInterface.executeStatement( "CREATE DATABASE " + ConstantSetting.DB_NAME + " character set utf8mb4;" );
                connection=DBConnector.getInstance().createConnection(ConstantSetting.DB_URL,
                        ConstantSetting.DB_USER,
                        ConstantSetting.DB_PASSWORD,
                        ConstantSetting.DB_NAME);
                DBInterface.createSystemTables( connection, log);
            } else {
                System.out.println( "connection failed!" );
            }
        }
    }
}
