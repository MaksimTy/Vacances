package com.tymkovskiy.ui;

import com.sun.javafx.binding.StringFormatter;
import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.model.VacancyManager;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;

import java.text.DateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.Callable;

public class ControllerVacances {

    public VacancyManager vacancyManager;


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
    private Button back;
    @FXML
    private Button front;
    @FXML
    private Button save;


    public void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        Vacancy vacancy = this.vacancyManager.getVacancyById(10);
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


}
