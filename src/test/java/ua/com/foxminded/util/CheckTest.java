package ua.com.foxminded.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CheckTest {

    Check check = new Check();
    
    @Test
    public void checkParameter_shouldThrowIllegalArgumentException_whenInputNull() {
        final String text = null;
        assertThrows(IllegalArgumentException.class, () -> check.checkParameter(text));
    }

    @Test
    public void checkParameter_shouldThrowIllegalArgumentException_whenInputEmpty() {
        final String text = "";
        assertThrows(IllegalArgumentException.class, () -> check.checkParameter(text));
    }
    
    @Test
    public void checkParameter_shouldThrowIllegalArgumentException_whenInputBlank() {
        final String text = " ";
        assertThrows(IllegalArgumentException.class, () -> check.checkParameter(text));
    }
    
    @Test
    void checkParameter_shouldThrowIllegalArgumentException_whenInputText() {
        boolean expected = true;
        final String text = "text";
//        boolean actual = check.checkParameter(text);
//        assertTrue();
    }    
}
