package ua.com.foxminded.util;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class ParameterValidatorTest {

    ParameterValidator parameterValidator = new ParameterValidator();
    
    @Test
    public void isNotNullNotEmpty_shouldThrowIllegalArgumentException_whenInputNull() {
        final String text = null;
        assertThrows(IllegalArgumentException.class, () -> parameterValidator.isNotNullNotEmpty(text));
    }

    @Test
    public void isNotNullNotEmpty_shouldThrowIllegalArgumentException_whenInputEmpty() {
        final String text = "";
        assertThrows(IllegalArgumentException.class, () -> parameterValidator.isNotNullNotEmpty(text));
    }
    
    @Test
    public void isNotNullNotEmpty_shouldThrowIllegalArgumentException_whenInputBlank() {
        final String text = " ";
        assertThrows(IllegalArgumentException.class, () -> parameterValidator.isNotNullNotEmpty(text));
    }   
}
