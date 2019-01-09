package db;

import home.ConstantSetting;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.assertTrue;

public class UserDAOTest {

    @Before
    public void setUp() throws Exception {
        if (DBConnector.getInstance().getConnection() == null || DBConnector.getInstance().getConnection().isClosed()) {
            DBConnector.getInstance().createConnection(ConstantSetting.DB_URL,
                    ConstantSetting.DB_USER,
                    ConstantSetting.DB_PASSWORD,
                    ConstantSetting.DB_NAME);
        }
    }

    @Test
    public void connect_sql_when_register() {
        UserDAO userDAO = new UserDAO();
        boolean register = userDAO.register("qqq" + new Random().nextInt(900) + 100, "dd112211");
        assertTrue(register);
    }

    //
//    @Test(expected = SQLException.class)
//    public void disconnect_sql_when_register() {
//        UserDAO userDAO=Mockito.mock(UserDAO.class);
//        userDAO.doThrow(new SQLException());
////        assertFalse(register);
//    }
    @Test
    public void getAccount() {
    }
}