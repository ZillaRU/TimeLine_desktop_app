package utils;

import org.junit.Before;
import org.junit.Test;

import java.sql.Timestamp;

import static org.junit.Assert.assertEquals;

/**
 * @author: zilla0148
 * @date: 2019/1/6 14:14
 */
public class TimeDisplayTest {
    long time;

    @Before
    public void setUp() {
        time = System.currentTimeMillis();
    }

    @Test
    public void display_as_just_now() {
        time = time - 2 * 1000L;
        assertEquals( "Just now", TimeDisplay.format( new Timestamp( time ) ) );
    }

    @Test
    public void display_as_yesterday() {
        time = time - 24 * 60 * 60 * 1000L;
        assertEquals( "Yesterday", TimeDisplay.format( new Timestamp( time ) ) );
    }

    @Test
    public void display_as_min_ago() {
        time = time - 70 * 1000L;
        assertEquals( "1 min ago", TimeDisplay.format( new Timestamp( time ) ) );
    }

    @Test
    public void display_as_hr_ago() {
        time = time - 60 * 60 * 1000L - 300 * 1000L;
        assertEquals( "1 hr ago", TimeDisplay.format( new Timestamp( time ) ) );
    }

    @Test
    public void display_as_d_ago() {
        time = time - 74 * 60 * 60 * 1000L;
        assertEquals( "3 d ago", TimeDisplay.format( new Timestamp( time ) ) );
    }

    @Test
    public void display_as_m_ago() {
        time = time - 66 * 24 * 60 * 60 * 1000L;
        assertEquals( "2 m ago", TimeDisplay.format( new Timestamp( time ) ) );
    }

    @Test
    public void display_as_yr_ago() {
        time = time - 400 * 24 * 60 * 60 * 1000L;
        assertEquals( "1 yr ago", TimeDisplay.format( new Timestamp( time ) ) );
    }

}