package com.tymkovskiy.ui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Convertor {


    /**
     * Метод представляет дату формата Date в формат LocalDate
     *
     * @param date Date
     * @return date LocalDate
     */
    public static LocalDate convertDateToLocalDate(Date date) {
        if (date == null) {
            return null;
        }
        Date instanse = new Date(date.getTime());
        return instanse.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }

}
