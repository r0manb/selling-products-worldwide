package by.r0manb.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class DateParser {
    private static final DateTimeFormatter slashSepDateFormatter = DateTimeFormatter.ofPattern("M/d/uuuu");
    private static final DateTimeFormatter dotSepDateFormatter = DateTimeFormatter.ofPattern("d.M.uuuu");

    public static LocalDate parseString(String dateString){
        return LocalDate.parse(
                dateString,
                dateString.contains(".") ? dotSepDateFormatter : slashSepDateFormatter
        );
    }
}
