package model;

import functionTest.TestConstants;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class UserTest {
    @Test
    public void get_user_right() {
        User user = new User(TestConstants.USERNAME);
        assertEquals(TestConstants.USERNAME, user.getUserID());
    }
}