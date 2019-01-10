package db;

import home.ConstantSetting;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DBConnectorTest {

    @Test
    public void get_Instance() {

    }

    @Test
    public void get_connection() {
        DBConnector dbConnector=new DBConnector();
        assertNull(dbConnector.getConnection());
    }


    @Test
    public void create_connection_return_success_connection() throws SQLException {
        DBConnector dbConnector=new DBConnector();
        Connection con=dbConnector.createConnection(ConstantSetting.DB_URL,ConstantSetting.DB_USER, ConstantSetting.DB_PASSWORD, ConstantSetting.DB_NAME );
        assertNotNull(con);
    }

    @Test
    public void create_connection_when_already_connection() throws SQLException, NoSuchFieldException {
        DBConnector dbConnector=new DBConnector();
        Connection con1=dbConnector.createConnection(ConstantSetting.DB_URL,ConstantSetting.DB_USER, ConstantSetting.DB_PASSWORD, ConstantSetting.DB_NAME );
        assertNotNull(con1);
        Connection con2=dbConnector.createConnection(ConstantSetting.DB_URL,ConstantSetting.DB_USER, ConstantSetting.DB_PASSWORD, ConstantSetting.DB_NAME );
        assertNotNull(con2);
        assertEquals(con1,con2);
    }
}