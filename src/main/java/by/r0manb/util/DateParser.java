package by.r0manb.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateParser {
    private static final DateTimeFormatter slashSepDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    private static final DateTimeFormatter dotSepDateFormatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");

    public static LocalDate parseString(String dateString){
        return LocalDate.parse(
                dateString,
                dateString.contains(".") ? dotSepDateFormatter : slashSepDateFormatter
        );
    }
}
