package db;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author: zilla0148
 * @date: 2018/12/25 14:23
 */
public class UserDAO {
    public boolean register(String name,String password){
        String stmt="INSERT INTO userinfo(username, password) "
                + "VALUES ('"
                + name + "', '"
                + password + "');";
        try {
            if(DBInterface.executeStatement(stmt)){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return false;
    }

    public int getAccount(String name,String passoword)  {
        String stmt="SELECT count(*) as userExist\n" +
                "FROM userinfo\n"+
                "WHERE username='"+name+
                "'\nAND password='"+passoword+"';";
        ResultSet resultSet=DBInterface.getResultSet( stmt );
        if(resultSet!=null) {
            try {
                resultSet.next();
                return resultSet.getInt("userExist");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return 0;
    }
}
