package ru.bellintegrator.weatherqueue.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public final class Parsing {
    private static final SimpleDateFormat DOT_DATE_FORMAT = new SimpleDateFormat("dd.mm.yyyy");

    /**
     * Парсинг даты
     */
    public static Date parseDate(String strDate){
        Date outDate = null;
        try {
            outDate = DOT_DATE_FORMAT.parse(strDate);
        } catch (ParseException e) {
            return null;
        }
        return outDate;
    }
}
