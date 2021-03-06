package utils;

import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class FormatCheckerTest {
    @Test
    public void input_in_illegal_format_return_false() {
        //too short
        boolean res = FormatChecker.hasFormatMismatch( "1p" );
        assertTrue( res );
        // too long
        res = FormatChecker.hasFormatMismatch( "qweqwexasdasdasdasd" );
        assertTrue( res );
        //without digit
        res = FormatChecker.hasFormatMismatch( "zillahr" );
        assertTrue( res );
        //without alphabet
        res = FormatChecker.hasFormatMismatch( "12342341" );
        assertTrue( res );
        //illegal word
        res = FormatChecker.hasFormatMismatch("!@#$%^");
        assertTrue(res);
    }

    @Test
    public void input_in_correct_format_return_true() {
        boolean res = FormatChecker.hasFormatMismatch( "qq0qq0" );
        assertFalse( res );
    }
}