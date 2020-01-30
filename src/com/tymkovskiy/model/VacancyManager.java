package com.tymkovskiy.model;

import com.tymkovskiy.dao.DAO;
import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.ui.UIVacances;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 */
public class VacancyManager {

    /**
     * Default constructor
     */
    public VacancyManager() {
        this.vacancies = new ArrayList<>();
    }

    /**vacances     */
    private List<Vacancy> vacancies;
    /**DAO class     */
    private DAO base;
    /**UI class     */
    private UIVacances ui;
    private static Logger logger = Logger.getLogger(VacancyManager.class.getName());


    /**
     * @param vacancy
     */
    public void addVacancy(Vacancy vacancy) throws DAOException {
        this.base.insertVacancy(vacancy);
    }

    /**
     * @param id
     * @throws SQLException
     * @throws IOException
     * @throws DAOException
     */
    public void deleteVacancy(int id) throws SQLException, IOException, DAOException {
        this.base.deleteVacancy(id);
    }

    /**
     * @param vacancy
     */
    public void updateVacancy(Vacancy vacancy) throws DAOException {
        this.base.updateVacancy(vacancy);
    }

    /**
     * @param vacancy
     */
    public void readVacancy(Vacancy vacancy) {
        // TODO implement here ????????
    }

    /**
     * @param string
     */
    public void findVacancy(String string) {
        // TODO implement here ????????
    }


    /**
     * @param vacancies
     */
    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
        logger.info("vacancies created. size = " + vacancies.size());
    }

    /**
     *
     * @return
     * @throws DAOException
     */
    public List<Vacancy> getVacancies() throws DAOException {
        this.base.getAllVacancies();
        return this.vacancies;
    }


    /**
     * @param date
     */
    public List<Vacancy> getVacancies(Date date) throws DAOException {
        this.base.getVacanciesByMailDate(date);
        return this.vacancies;
    }

    /**
     * @param company
     */
    public List<Vacancy> getVacancies(String company) throws DAOException {
        this.base.getVacanciesByCompany(company);
        return this.vacancies;
    }

    /**
     * @param start
     * @param end
     */
    public List<Vacancy> getVacancies(Date start, Date end) throws DAOException {
        this.base.getVacanciesByMailDate(start, end);
        return this.vacancies;
    }

    public Vacancy getVacancyById(int vacancyId) throws DAOException {
        return this.base.getVacancyById(vacancyId);
    }

    public DAO getBase() {
        return this.base;
    }

    public void setBase(DAO base) {
        this.base = base;
    }

    public void showVacanciesInConsole(){
        for (Vacancy vacancy: this.vacancies
             ) {
            System.out.println(vacancy);
        }
    }



}