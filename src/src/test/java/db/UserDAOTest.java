package db;

import home.ConstantSetting;
import org.junit.Before;
import org.junit.Test;

import java.util.Random;

import static org.junit.Assert.*;

public class UserDAOTest {

    @Before
    public void setUp() throws Exception {
        if(DBConnector.getInstance().getConnection()==null||DBConnector.getInstance().getConnection().isClosed()){
            DBConnector.getInstance().createConnection( ConstantSetting.DB_URL,
                    ConstantSetting.DB_USER,
                    ConstantSetting.DB_PASSWORD,
                    ConstantSetting.DB_NAME);
        }
    }

    @Test
    public void connect_sql_when_register() {
        UserDAO userDAO=new UserDAO();
        boolean register=userDAO.register("qqq"+new Random().nextInt( 900 ) + 100,"dd112211");
        assertTrue(register);
    }

//    @Test(expected = SQLException.class)
//    public void disconnect_sql_when_register() throws NoSuchFieldException {
//        UserDAO userDao=new UserDAO();
//        try{
//            Field field = UserDAO.class.getDeclaredField("con");
//            field.setAccessible(true);
//            field.set(userDao, null);
//        }catch (IllegalAccessException e){
//            e.printStackTrace();
//        }
//        //doThrow(SQLException.class).when(userDAO).register("qqq123l","dd112211");
//        userDao.register("qqq123l","dd112211");
//    }

    @Test
    public void get_account_will_return_one_or_zero_account() {
        UserDAO userDAO=new UserDAO();
        int account=userDAO.getAccount("qqq123","dd112211");
        assertEquals(1,account);
        int noaccount=userDAO.getAccount("qqq","dd112211");
        assertEquals(0,noaccount);
    }
}