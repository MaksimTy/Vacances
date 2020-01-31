package com.tymkovskiy.dao;

import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.model.VacancyManager;

import java.io.IOException;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;


/**
 *
 */
public class DAO {

    /**
     * Default constructor
     */
    public DAO(VacancyManager vacancyManager) {
        this.vacancyManager = vacancyManager;
    }

    private static final String ORDER = " ORDER BY id";
    private VacancyManager vacancyManager;
    private static Logger logger = Logger.getLogger(DAO.class.getName());


    /**
     * @param vacancy
     * @throws DAOException
     */
    public void insertVacancy(Vacancy vacancy) throws DAOException {
        logger.info("start...");
        String sql = "INSERT INTO public.mail (id, company, vacancy, mail, mail_date, text, vacancy_linc) " +
                "VALUES (?,?,?,?,?,?,?)";
        SimpleDateFormat sDateForm = new SimpleDateFormat("dd-MM-yyyy");
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                /**id вакансии*/
                statement.setInt(1, this.getLastId() + 1);
                logger.info(Integer.toString(vacancy.getId()));
                /**наименование компании*/
                if (vacancy.getCompany() == null) statement.setString(2, null);
                statement.setString(2, vacancy.getCompany());
                logger.info(vacancy.getCompany());
                /**наименование вакансии*/
                if (vacancy.getVacancy() == null) statement.setString(3, null);
                statement.setString(3, vacancy.getVacancy());
                logger.info(vacancy.getVacancy());
                /**электронный адрес компании*/
                if (vacancy.getMail() == null) statement.setString(4, null);
                statement.setString(4, vacancy.getMail());
                logger.info(vacancy.getMail());
                /**дата отправки резюме*/
                if (vacancy.getMail_date() == null) {
                    statement.setDate(5, null);
                    logger.info("mail date : ");
                } else {
                    java.sql.Date date = new java.sql.Date(vacancy.getMail_date().getTime());
                    statement.setDate(5, date);
                    logger.info(sDateForm.format(vacancy.getMail_date()));
                }
                /**текст сопроводительного письма*/
                if (vacancy.getText() == null) statement.setString(6, null);
                statement.setString(6, vacancy.getText());
                logger.info(vacancy.getText());

                /**ссылка на вакансию*/
                if (vacancy.getVacancy_linc() == null) statement.setString(7, null);
                statement.setString(7, vacancy.getVacancy_linc());
                logger.info(vacancy.getVacancy_linc());

                int items = statement.executeUpdate();
                logger.info(Integer.toString(items) + " items updated/inserted!");
                logger.info("Vacancy" + vacancy.toString() + " updated/inserted successfully!");
            }
        } catch (IOException | SQLException e) {
            logger.warning("Vacancy" + vacancy.toString() + " cannot updated/inserted!");
            logger.warning("Error in update/insert: " + e);

            throw new DAOException("Cannot updated/inserted.");
        }
        logger.info(" is ended.");
    }

    /**
     * @throws DAOException
     */
    public void getAllVacancies() throws DAOException {
        this.vacancyManager.setVacancies(new ArrayList<>());
        logger.info("start...");
        String sql = "SELECT * FROM public.mail";
        this.selectQuerySet(sql);
        logger.info(" is ended.");
    }

    /**
     * @param companyName
     * @throws DAOException
     */
    public void getVacanciesByCompany(String companyName) throws DAOException {
        vacancyManager.setVacancies(new ArrayList<>());
        logger.info("start...");
        String sql = "SELECT * FROM public.mail WHERE company ILIKE '%" + companyName.toLowerCase().trim() + "%'";
        selectQuerySet(sql);
        logger.info(" is ended.");
    }

    /**
     * @param companyId
     * @return
     * @throws DAOException
     */
    public Vacancy getVacancyById(int companyId) throws DAOException {
        logger.info("start...");
        Vacancy vacancy = null;
        String sql = "SELECT * FROM public.mail WHERE id=" + companyId;
        vacancy = selectQueryItem(vacancy, sql);
        logger.info(" is ended.");
        return vacancy;
    }

    /**
     * @param date
     * @throws DAOException
     */
    public void getVacanciesByMailDate(Date date) throws DAOException {
        vacancyManager.setVacancies(new ArrayList<>());
        logger.info("start...");
        SimpleDateFormat sDateForm = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "SELECT * FROM public.mail WHERE mail_date='" + sDateForm.format(date) + "'";
        selectQuerySet(sql);
        logger.info(" is ended.");
    }

    /**
     * @param start
     * @param end
     * @throws DAOException
     */
    public void getVacanciesByMailDate(Date start, Date end) throws DAOException {
        vacancyManager.setVacancies(new ArrayList<>());
        logger.info("start...");
        SimpleDateFormat sDateForm = new SimpleDateFormat("dd-MM-yyyy");
        String sql = "SELECT * FROM public.mail WHERE mail_date BETWEEN '" +
                sDateForm.format(start) +
                "' AND '" +
                sDateForm.format(end) + "'";
        selectQuerySet(sql);
        logger.info(" is ended.");
    }

    /**
     * @param vacancy
     * @throws DAOException
     */
    public void updateVacancy(Vacancy vacancy) throws DAOException {
        logger.info("start...");
        String sql = "UPDATE public.mail SET company=?, vacancy=?, mail=?, mail_date=?, text=?, answer=?, vacancy_linc=?, answer_date=? WHERE id=?";
        SimpleDateFormat sDateForm = new SimpleDateFormat("dd-MM-yyyy");

        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                /**наименование компании*/
                if (vacancy.getCompany() == null) statement.setString(1, null);
                statement.setString(1, vacancy.getCompany());
                logger.info(vacancy.getCompany());
                /**наименование вакансии*/
                if (vacancy.getVacancy() == null) statement.setString(2, null);
                statement.setString(2, vacancy.getVacancy());
                logger.info(vacancy.getVacancy());
                /**электронный адрес компании*/
                if (vacancy.getMail() == null) statement.setString(3, null);
                statement.setString(3, vacancy.getMail());
                logger.info(vacancy.getMail());
                /**дата отправки резюме*/
                if (vacancy.getMail_date() == null) {
                    statement.setDate(4, null);
                    logger.info("mail date : ");
                } else {
                    java.sql.Date date = new java.sql.Date(vacancy.getMail_date().getTime());
                    statement.setDate(4, date);
                    logger.info(sDateForm.format(vacancy.getMail_date()));
                }
                /**текст сопроводительного письма*/
                if (vacancy.getText() == null) statement.setString(5, null);
                statement.setString(5, vacancy.getText());
                logger.info(vacancy.getText());
                /**ответ*/
                if (vacancy.getAnswer() == null) statement.setString(6, null);
                statement.setString(6, vacancy.getAnswer());
                logger.info(vacancy.getAnswer());
                /**ссылка на вакансию*/
                if (vacancy.getVacancy_linc() == null) statement.setString(7, null);
                statement.setString(7, vacancy.getVacancy_linc());
                logger.info(vacancy.getVacancy_linc());
                /**дата ответа компании*/
                if (vacancy.getAnswer_date() == null) {
                    statement.setDate(8, null);
                    logger.info("answer date : ");
                } else {
                    java.sql.Date date = new java.sql.Date(vacancy.getAnswer_date().getTime());
                    statement.setDate(8, date);
                    logger.info(sDateForm.format(vacancy.getAnswer_date()));
                }
                /**id вакансии*/
                statement.setInt(9, vacancy.getId());
                logger.info(Integer.toString(vacancy.getId()));

                int items = statement.executeUpdate();
                logger.info(Integer.toString(items) + " items updated/inserted!");
                logger.info("Vacancy" + vacancy.toString() + " updated/inserted successfully!");
            }
        } catch (IOException | SQLException e) {
            logger.warning("Vacancy" + vacancy.toString() + " cannot updated/inserted!");
            logger.warning("Error in update/insert: " + e);

            throw new DAOException("Cannot updated/inserted.");
        }
        logger.info(" is ended.");
    }

    /**
     * @param id
     * @throws IOException
     * @throws SQLException
     * @throws DAOException
     */
    public void deleteVacancy(int id) throws DAOException {
        logger.info("start...");
        String sql = "DELETE FROM public.mail WHERE id=" + id;

        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (Statement statement = connection.createStatement()) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                boolean isDeleted = statement.execute(sql);
                message = isDeleted ? "vacancy  deleted!" : "vacancy not deleted!";
                logger.info(message);
                if (isDeleted) {
                    logger.info(message);
                } else {
                    logger.warning(message);
                }
            }
        } catch (IOException | SQLException e) {
            logger.warning("Vacancy with id=" + id + " cannot deleted!");
            logger.warning("Error in delete: " + e);

            throw new DAOException("Cannot delete.");
        }
        logger.info(" is ended.");
    }

    /**
     * @param vacancy
     * @param sql
     * @return
     * @throws DAOException
     */
    private Vacancy selectQueryItem(Vacancy vacancy, String sql) throws DAOException {
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                try (ResultSet resultSet = statement.executeQuery()) {
                    message = resultSet.isClosed() == false ? "resultSet is open." : "resultSet is close.";
                    logger.info(message);
                    while (resultSet.next()) {
                        /**id вакансии*/
                        int id = resultSet.getInt("id");
                        /**наименование компании*/
                        String company = resultSet.getString("company");
                        /**наименование вакансии*/
                        String vacancyName = resultSet.getString("vacancy");
                        /**электронный адрес компании*/
                        String mail = resultSet.getString("mail");
                        /**дата отправки резюме*/
                        Date mail_date = resultSet.getDate("mail_date");
                        /**текст сопроводительного письма*/
                        String text = resultSet.getString("text");
                        /**ответ*/
                        String answer = resultSet.getString("answer");
                        /**ссылка на вакансию*/
                        String vacancy_linc = resultSet.getString("vacancy_linc");
                        /**дата ответа компании*/
                        Date answer_date = resultSet.getDate("answer_date");
                        vacancy = new Vacancy(id, company, vacancyName, mail, mail_date, text, vacancy_linc);
                        if (answer != null) {
                            vacancy.setAnswer(answer);
                        }
                        if (answer_date != null) {
                            vacancy.setAnswer_date(answer_date);
                        }

                        logger.info(String.format("get vacancy with id %d !", vacancy.getId()));
                    }
                }
            }
        } catch (IOException | SQLException e) {
            logger.warning("Error in select: " + e);
            throw new DAOException("Cannot selected.");
        }
        return vacancy;
    }

    /**
     * @param sql
     * @throws DAOException
     */
    private void selectQuerySet(String sql) throws DAOException {
        sql = sql + ORDER;
        List<Vacancy> vacancies = new ArrayList<>();
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                try (ResultSet resultSet = statement.executeQuery()) {
                    message = resultSet.isClosed() == false ? "resultSet is open." : "resultSet is close.";
                    logger.info(message);
                    while (resultSet.next()) {
                        /**id вакансии*/
                        int id = resultSet.getInt("id");
                        /**наименование компании*/
                        String company = resultSet.getString("company");
                        /**наименование вакансии*/
                        String vacancyName = resultSet.getString("vacancy");
                        /**электронный адрес компании*/
                        String mail = resultSet.getString("mail");
                        /**дата отправки резюме*/
                        Date mail_date = resultSet.getDate("mail_date");
                        /**текст сопроводительного письма*/
                        String text = resultSet.getString("text");
                        /**ответ*/
                        String answer = resultSet.getString("answer");
                        /**ссылка на вакансию*/
                        String vacancy_linc = resultSet.getString("vacancy_linc");
                        /**дата ответа компании*/
                        Date answer_date = resultSet.getDate("answer_date");
                        Vacancy vacancy = new Vacancy(id, company, vacancyName, mail, mail_date, text, vacancy_linc);
                        if (answer != null) {
                            vacancy.setAnswer(answer);
                        }
                        if (answer_date != null) {
                            vacancy.setAnswer_date(answer_date);
                        }
                        vacancies.add(vacancy);
                        logger.info(String.format("get vacancy with id %d !", vacancy.getId()));
                    }
                    this.vacancyManager.setVacancies(vacancies);
                }
            }
        } catch (IOException | SQLException e) {
            logger.warning("Error in select: " + e);
            throw new DAOException("Cannot selected.");
        }
    }

    /**
     * Метод возвращает максимальный id.
     *
     * @return lastId
     * @throws DAOException
     */
    public int getLastId() throws DAOException {
        logger.info("start...");
        String sql = "SELECT MAX(id) AS id FROM public.mail";
        int maxId = 0;
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (Statement statement = connection.createStatement()) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    message = resultSet.isClosed() == false ? "resultSet is open." : "resultSet is close.";
                    logger.info(message);
                    while (resultSet.next()) {
                        maxId = resultSet.getInt("id");
                    }
                }
            }
        } catch (IOException | SQLException e) {
            logger.warning("Error : " + e);
            throw new DAOException("Cannot selected.");
        }
        logger.info(" is ended.");
        return maxId;
    }

    /**
     * Метод возвращает минимальный id.
     *
     * @return firstId
     * @throws DAOException
     */
    public int getFirstId() throws DAOException {
        logger.info("start...");
        String sql = "SELECT MIN(id) AS id FROM public.mail";
        int minId = 0;
        try (Connection connection = ConnectionFactory.getInstance().getConnection()) {
            String message = connection.isClosed() == false ? "connection is open." : "connection is close.";
            logger.info(message);
            try (Statement statement = connection.createStatement()) {
                message = statement.isClosed() == false ? "statement is open." : "statement is close.";
                logger.info(message);
                try (ResultSet resultSet = statement.executeQuery(sql)) {
                    message = resultSet.isClosed() == false ? "resultSet is open." : "resultSet is close.";
                    logger.info(message);
                    while (resultSet.next()) {
                        minId = resultSet.getInt("id");
                    }
                }
            }
        } catch (IOException | SQLException e) {
            logger.warning("Error : " + e);
            throw new DAOException("Cannot selected.");
        }
        logger.info(" is ended.");
        return minId;
    }
}