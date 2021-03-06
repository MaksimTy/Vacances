package com.tymkovskiy.controller;

import com.tymkovskiy.dao.DAO;
import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;

import java.io.IOException;
import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import java.util.stream.Collectors;

/**
 *
 */
public class VacancyManager {

    /**
     * Default constructor
     */
    public VacancyManager() throws DAOException {
        DAO dao = new DAO(this);
        this.setBase(dao);
        this.vacancies = this.getVacancies();
    }

    /**
     * vacances
     */
    private List<Vacancy> vacancies;
    /**
     * DAO class
     */
    private DAO base;
    private static Logger logger = Logger.getLogger(VacancyManager.class.getName());


    /**
     * @param vacancy
     */
    public void addVacancy(Vacancy vacancy) throws DAOException {
        this.base.insertVacancy(vacancy);
        this.base.getAllVacancies();
    }

    /**
     * @param id
     * @throws SQLException
     * @throws IOException
     * @throws DAOException
     */
    public void deleteVacancy(int id) throws SQLException, IOException, DAOException {
        this.base.deleteVacancy(id);
        this.base.getAllVacancies();
    }

    /**
     * @param vacancy
     */
    public void updateVacancy(Vacancy vacancy) throws DAOException {
        this.base.updateVacancy(vacancy);
        this.base.getAllVacancies();
    }

    /**
     * @param vacancies
     */
    public void setVacancies(List<Vacancy> vacancies) {
        this.vacancies = vacancies;
        logger.info("vacancies created. size = " + vacancies.size());
    }

    /**
     * @return
     * @throws DAOException
     */
    public List<Vacancy> getVacancies() throws DAOException {
        this.base.getAllVacancies();
        return this.vacancies;
    }

    private void setBase(DAO base) {
        this.base = base;
    }

    public void showVacanciesInConsole() {
        for (Vacancy vacancy : this.vacancies
        ) {
            System.out.println(vacancy);
        }
    }

    public int getLastId() throws DAOException {
        return this.base.getLastId();
    }

    public int getFirstId() throws DAOException {
        return this.base.getFirstId();
    }

    public List<String> getAnswersList() throws DAOException {
        base.getAllVacancies();
        return this.vacancies.stream().
                map(x -> x.getAnswer()).
                distinct().
                collect(Collectors.toList());
    }
}