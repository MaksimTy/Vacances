package com.tymkovskiy.controller;


import com.tymkovskiy.model.Vacancy;
import javafx.beans.property.*;

import java.text.SimpleDateFormat;
import java.util.Date;

public class SimpleVacancyProperty {

    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

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
    private StringProperty mail_date;
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
    private StringProperty answer_date;


    public SimpleVacancyProperty(Vacancy vacancy) {
        this.id = new SimpleIntegerProperty(vacancy.getId());
        this.company = new SimpleStringProperty(vacancy.getCompany());
        this.vacancy = new SimpleStringProperty(vacancy.getVacancy());
        this.mail = new SimpleStringProperty(vacancy.getMail());
        this.vacancy_linc = new SimpleStringProperty(vacancy.getVacancy_linc());

        this.mail_date = vacancy.getMail_date() == null ?
                null : new SimpleStringProperty(sdf.format(vacancy.getMail_date()));
        this.text = new SimpleStringProperty(vacancy.getText());
        this.answer = new SimpleStringProperty(vacancy.getAnswer());
        this.answer_date = vacancy.getAnswer_date() == null ?
                null : new SimpleStringProperty(sdf.format(vacancy.getAnswer_date()));
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

    public String getMail_date() {
        return mail_date.get();
    }

    public StringProperty mail_dateProperty() {
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

    public String getAnswer_date() {
        return answer_date.get();
    }

    public StringProperty answer_dateProperty() {
        return answer_date;
    }
}
