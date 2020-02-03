package com.tymkovskiy.controller;


import com.tymkovskiy.model.Vacancy;
import javafx.beans.property.*;

import java.time.LocalDate;

public class SimpleVacancyProperty {


    /**
     * id вакансии
     */
    private IntegerProperty id;
    /**
     * наименование компании
     */
    private StringProperty company;
    /**
     * наименование вакансии
     */
    private StringProperty vacancy;
    /**
     * электронный адрес компании
     */
    private StringProperty mail;
    /**
     * дата отправки резюме
     */
    private ObjectProperty<LocalDate> mail_date;
    /**
     * текст сопроводительного письма
     */
    private StringProperty text;
    /**
     * ответ
     */
    private StringProperty answer;
    /**
     * ссылка на вакансию
     */
    private StringProperty vacancy_linc;
    /**
     * дата ответа компании
     */
    private ObjectProperty<LocalDate> answer_date;


    public SimpleVacancyProperty(Vacancy vacancy) {
        this.id = new SimpleIntegerProperty(vacancy.getId());
        this.company = new SimpleStringProperty(vacancy.getCompany());
        this.vacancy = new SimpleStringProperty(vacancy.getVacancy());
        this.mail = new SimpleStringProperty(vacancy.getMail());
        this.vacancy_linc = new SimpleStringProperty(vacancy.getVacancy_linc());
        this.mail_date = new SimpleObjectProperty<>(
                Convertor.convertDateToLocalDate(vacancy.getMail_date()));
        this.text = new SimpleStringProperty(vacancy.getText());
        this.answer = new SimpleStringProperty(vacancy.getAnswer());
        this.answer_date = new SimpleObjectProperty<>(
                Convertor.convertDateToLocalDate(vacancy.getAnswer_date()));
    }

    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public String getVacancy() {
        return vacancy.get();
    }

    public StringProperty vacancyProperty() {
        return vacancy;
    }

    public String getMail() {
        return mail.get();
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public LocalDate getMail_date() {
        return mail_date.get();
    }

    public ObjectProperty<LocalDate> mail_dateProperty() {
        return mail_date;
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public String getAnswer() {
        return answer.get();
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public String getVacancy_linc() {
        return vacancy_linc.get();
    }

    public StringProperty vacancy_lincProperty() {
        return vacancy_linc;
    }

    public LocalDate getAnswer_date() {
        return answer_date.get();
    }

    public ObjectProperty<LocalDate> answer_dateProperty() {
        return answer_date;
    }
}
