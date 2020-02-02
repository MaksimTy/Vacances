package com.tymkovskiy.controller;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.model.VacancyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class ControllerVacancyCard {

    private VacancyManager vacancyManager;
    /**
     * Вакансия, карточка которой открыта в форме в данный момент.
     */
    private Vacancy currentVacancy;

    @FXML
    private Label id;
    @FXML
    private TextField company;
    @FXML
    private TextField vacancy;
    @FXML
    private TextField mail;
    @FXML
    private TextField link;
    @FXML
    private DatePicker dateRequest;
    @FXML
    private TextArea message;
    @FXML
    private ComboBox<String> answer;
    @FXML
    private DatePicker dateAnswer;

    @FXML
    private Button start;
    @FXML
    private Button back;
    @FXML
    private Button front;
    @FXML
    private Button end;
    @FXML
    private Button cansel;
    @FXML
    private Button create;
    @FXML
    private Button save;


    /**
     * Метод инициализирует поля формы значениями последнего объекта
     * Vacancy с максимальным id.
     *
     * @throws DAOException
     */
    public void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        Vacancy vacancy = this.vacancyManager.getVacancyById(this.vacancyManager.getLastId());
        this.currentVacancy = vacancy;
        this.setFields(this.currentVacancy);
        ObservableList<String> answers = FXCollections.observableArrayList(this.vacancyManager.getAnswersList());
        this.answer.setItems(answers);

    }

    @FXML
    public void setStartButtonClick(ActionEvent actionEvent) throws DAOException {
        int id = this.vacancyManager.getFirstId();
        this.currentVacancy = this.vacancyManager.getVacancyById(id);
        this.setFields(this.currentVacancy);
    }

    @FXML
    public void setBackButtonClick(ActionEvent actionEvent) throws DAOException {
        int id = -1;
        oneStepAlongVacancies(id);
    }

    @FXML
    public void setFrontButtonClick(ActionEvent actionEvent) throws DAOException {
        int id = 1;
        oneStepAlongVacancies(id);
    }

    @FXML
    public void setEndButtonClick(ActionEvent actionEvent) throws DAOException {
        int id = this.vacancyManager.getLastId();
        this.currentVacancy = this.vacancyManager.getVacancyById(id);
        this.setFields(this.currentVacancy);
    }

    @FXML
    public void setCanselButtonClick(ActionEvent actionEvent) throws DAOException {
        this.clearFields();
        this.currentVacancy = this.vacancyManager.getVacancyById(this.vacancyManager.getLastId());
        this.setFields(this.currentVacancy);
        this.cansel.setVisible(false);
    }

    @FXML
    public void setCreateButtonClick(ActionEvent actionEvent) throws DAOException {
        this.clearFields();
        this.cansel.setVisible(true);
        this.id.setText((this.vacancyManager.getLastId() + 1) + " new entry !");
    }

    @FXML
    public void setSaveButtonClick(ActionEvent actionEvent) throws DAOException {
        try {
            this.setCurrentVacancy();
            this.vacancyManager.updateVacancy(this.currentVacancy);
        } catch (NumberFormatException e) {
            this.vacancyManager.addVacancy(this.currentVacancy);
            this.clearFields();
            this.setFields(this.vacancyManager.getVacancyById(this.vacancyManager.getLastId()));
            this.setCurrentVacancy();
        }
        this.cansel.setVisible(false);
    }

    @FXML
    public void setDeleteButtonClick(ActionEvent actionEvent) throws DAOException, IOException, SQLException {
        int id = Integer.parseInt(this.id.getText());
        this.vacancyManager.deleteVacancy(id);
        this.setFields(this.vacancyManager.getVacancyById(this.vacancyManager.getLastId()));
        this.setCurrentVacancy();

    }

    /**
     * Метод инициализирует поля формы значениями
     * полей вакансии
     *
     * @param vacancy вакансия
     */
    private void setFields(Vacancy vacancy) {
        this.id.setText(String.valueOf(vacancy.getId()));
        this.company.setText(vacancy.getCompany());
        this.vacancy.setText((vacancy.getVacancy()));
        this.mail.setText(vacancy.getMail());
        this.link.setText(vacancy.getVacancy_linc());
        this.dateRequest.setValue(
                Convertor.convertDateToLocalDate(vacancy.getMail_date()));
        this.message.setText(vacancy.getText());
        this.answer.getEditor().setText(vacancy.getAnswer());
        this.dateAnswer.setValue(
                Convertor.convertDateToLocalDate(vacancy.getAnswer_date()));
    }

    /**
     * Метод сбрасывает текущие значеня полей формы.
     */
    private void clearFields() {
        this.id.setText(null);
        this.company.setText(null);
        this.vacancy.setText(null);
        this.mail.setText(null);
        this.link.setText(null);
        this.dateRequest.setValue(
                Convertor.convertDateToLocalDate(new Date()));
        this.message.setText(null);
        this.answer.getEditor().setText(null);
    }

    /**
     * Метод считывает значения полей формы возвращает объект Vacancy
     *
     * @return Vacancy
     * @throws DAOException
     */
    private Vacancy parserVacancy() throws DAOException {
        String company = null;
        String vacancy = null;
        try {
            company = this.company.getText().trim();
            vacancy = this.vacancy.getText().trim();
        } catch (NullPointerException e) {
            this.message.setText(this.message.getText() + "\n"
                    + "Field cannot be empty: company, vacancy!");
        }
        String mail = this.mail.getText() == null ? "" : this.mail.getText().trim();
        if (!Convertor.isEmailValidate(mail)) {
            this.mail.setText("Invalid mail!");
            throw new DAOException("Invalid mail!");
        }
        Date date = Convertor.convertLocalDateToDate(this.dateRequest.getValue());
        String text = this.message.getText();
        String link = this.link.getText();
        Vacancy result = new Vacancy(company, vacancy, mail, date, text, link);
        return result;
    }

    /**
     * Метод возвращает объект Vacancy из списка Vacancies со сдвигом
     * на id и присваевает его полю this.carrentVacancy.
     *
     * @param id сдвиг
     * @throws DAOException
     */
    private void oneStepAlongVacancies(int id) throws DAOException {
        List<Vacancy> vacancies = this.vacancyManager.getVacancies();
        id += vacancies.indexOf(this.currentVacancy);
        this.setFields(vacancies.get(id));
        this.currentVacancy = vacancies.get(id);
    }

    /**
     * Метод инициализирует currentVacancy
     */
    private void setCurrentVacancy() throws DAOException {
        this.currentVacancy = this.parserVacancy();
        int id = Integer.parseInt(this.id.getText());
        this.currentVacancy.setId(id);
    }

}
