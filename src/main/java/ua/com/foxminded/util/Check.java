package ua.com.foxminded.util;

import java.util.Optional;

public class Check {

    public void checkParameter(String parameter) {
        Optional
            .ofNullable(parameter)
            .orElseThrow(() -> new IllegalArgumentException("Null as parameter is not allowed "));
    
        if (parameter.isEmpty()) {
            throw new IllegalArgumentException("Empty paramete are not allowed ");
        }
    }  
}
