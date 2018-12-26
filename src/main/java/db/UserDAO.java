package db;

import home.ConstantSetting;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: zilla0148
 * @date: 2018/12/25 14:23
 */
public class UserDAO {
    private Connection con = DBConnector.getInstance().getConnection();

    public boolean register(String name, String password) {
        try {
            String stmt = "INSERT INTO userinfo(username, password) "
                    + "VALUES (?,ENCODE(?,?));";
            System.out.println( stmt );
            PreparedStatement preparedStatement = con.prepareStatement( stmt );
            preparedStatement.setString( 1, name );
            preparedStatement.setString( 2, password );
            preparedStatement.setString( 3,ConstantSetting.ENCODE_KEY );
            preparedStatement.executeUpdate();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAccount(String name, String password) {
        try {
            String stmt = "SELECT count(*) as userExist\n" +
                    "FROM userinfo\n" +
                    "WHERE username=? AND DECODE(password,?)=?;";
            System.out.println( stmt );
            PreparedStatement preparedStatement = con.prepareStatement( stmt );
            preparedStatement.setString( 1, name );
            preparedStatement.setString( 2, ConstantSetting.ENCODE_KEY );
            preparedStatement.setString( 3, password);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet != null) {
                try {
                    resultSet.next();
                    return resultSet.getInt( "userExist" );
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
}
