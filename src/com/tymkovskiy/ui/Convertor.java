package com.tymkovskiy.ui;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        return instanse.
                toInstant().
                atZone(ZoneId.systemDefault()).
                toLocalDate();
    }

    /**
     * Метод представляет дату формата LocalDate в формат Date
     *
     * @param localDate LocalDate
     * @return date Date
     */
    public static Date convertLocalDateToDate(LocalDate localDate) {
        if(localDate == null){
            return null;
        }
        return Date.from(
                localDate.atStartOfDay().
                        atZone(ZoneId.systemDefault()).
                        toInstant());
    }

    /**
     * Метод проверяет корректность введённого e-mail адреса.
     * @param mail введенный e-mail.
     * @return true если адрес валидный или отсутствует и false,
     * если не валидный.
     */
    public static boolean isEmailValidate(String mail) {
        if (mail == null || mail.trim().isEmpty()) {
            return true;
        }
        String regex = "^[\\w!#$%&'*+/=?`{|}~^-]+(?:\\.[\\w!#$%&'*+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mail);
        return matcher.matches();
    }
}
