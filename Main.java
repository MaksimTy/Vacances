package com.tymkovskiy;

import com.tymkovskiy.dao.DAO;
import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.model.VacancyManager;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.Date;


public class Main {

    public static void main(String[] args) throws IOException, SQLException, DAOException, NoSuchMethodException, ClassNotFoundException {
        // write your code here

        VacancyManager vacancyManager = new VacancyManager();
        DAO dao = new DAO(vacancyManager);
        vacancyManager.setBase(dao);


        //2019-08-06
        Calendar calendar = Calendar.getInstance();
        calendar.set(2019, Calendar.DECEMBER, 25);
        Date date = calendar.getTime();

        Calendar calendar2 = Calendar.getInstance();
        calendar2.set(2020, Calendar.JANUARY, 11);
        Date date2 = calendar2.getTime();

        //Vacancy temp = new Vacancy(100,"temp+","temp+","temp@temp+",date,null,"temp@temp");
        //temp.setAnswer_date(date2);
       //vacancyManager.addVacancy(temp);
       // vacancyManager.updateVacancy(temp);
       // vacancyManager.getVacancies(date, date2);
        vacancyManager.deleteVacancy(99);
        vacancyManager.getVacancies();

        vacancyManager.showVacanciesInConsole();

    }
}
