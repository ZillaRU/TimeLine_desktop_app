package utils;

import org.junit.Test;

import static org.hamcrest.core.StringStartsWith.startsWith;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;

public class IdGeneratorTest {

    @Test
    public void get_Id_with_right_num_and_contain_systime() {
        assertThat(IdGenerator.getId(), startsWith(Long.toString(System.currentTimeMillis()).substring(0, 10)));
        assertEquals(16, IdGenerator.getId().length());
    }
}