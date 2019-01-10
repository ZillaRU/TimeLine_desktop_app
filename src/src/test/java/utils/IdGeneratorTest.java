package utils;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.startsWith;
import static org.junit.Assert.*;

public class IdGeneratorTest {

    @Test
    public void get_Id_with_right_num_and_contain_systime() {
        assertThat(IdGenerator.getId(),startsWith(Long.toString( System.currentTimeMillis()).substring(0,10)));
        assertEquals(16,IdGenerator.getId().length());
    }
}