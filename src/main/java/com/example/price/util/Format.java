package com.example.price.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Format {

    public static LocalDateTime dateFormat(String dateInitial){
        return LocalDateTime.parse(dateInitial,
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

}
