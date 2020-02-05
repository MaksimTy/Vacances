package com.tymkovskiy.model;

import com.tymkovskiy.dao.DAO;
import com.tymkovskiy.util.Convertor;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.*;
import java.util.logging.Logger;

/**
 *
 */
public class Vacancy {

    private static Logger logger = Logger.getLogger(Vacancy.class.getName());
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


    /**
     * Constructor
     *
     * @param id           id вакансии
     * @param company      наименование компании
     * @param vacancy      электронный адрес компании
     * @param mail         электронный адрес компании
     * @param mail_date    дата отправки резюме
     * @param text         текст сопроводительного письма
     * @param vacancy_linc ссылка на вакансию
     */

    public Vacancy(
            int id,
            String company,
            String vacancy,
            String mail,
            Date mail_date,
            String text,
            String vacancy_linc) {
        this.id = new SimpleIntegerProperty(id);
        this.company = new SimpleStringProperty(company);
        this.vacancy = new SimpleStringProperty(vacancy);
        this.mail = new SimpleStringProperty(mail);
        this.mail_date = new SimpleObjectProperty<LocalDate>(
                Convertor.convertDateToLocalDate(mail_date));
        this.text = new SimpleStringProperty(text);
        this.vacancy_linc = new SimpleStringProperty(vacancy_linc);
    }

    /**
     * Конструктор для нового объекта, не имеет id. Id - максимальный текущий id + 1.
     *
     * @param company      наименование компании
     * @param vacancy      электронный адрес компании
     * @param mail         электронный адрес компании
     * @param mail_date    дата отправки резюме
     * @param text         текст сопроводительного письма
     * @param vacancy_linc ссылка на вакансию
     */
    public Vacancy(
            String company,
            String vacancy,
            String mail,
            Date mail_date,
            String text,
            String vacancy_linc) {
        this.company = new SimpleStringProperty(company);
        this.vacancy = new SimpleStringProperty(vacancy);
        this.mail = new SimpleStringProperty(mail);
        this.mail_date = new SimpleObjectProperty<LocalDate>(
                Convertor.convertDateToLocalDate(mail_date));
        this.text = new SimpleStringProperty(text);
        this.vacancy_linc = new SimpleStringProperty(vacancy_linc);
    }


    public int getId() {
        return id.get();
    }

    public IntegerProperty idProperty() {
        return id;
    }

    public void setId(int id) {
        this.id.set(id);
    }

    public String getCompany() {
        return company.get();
    }

    public StringProperty companyProperty() {
        return company;
    }

    public void setCompany(String company) {
        this.company.set(company);
    }

    public String getVacancy() {
        return vacancy.get();
    }

    public StringProperty vacancyProperty() {
        return vacancy;
    }

    public void setVacancy(String vacancy) {
        this.vacancy.set(vacancy);
    }

    public String getMail() {
        return mail.get();
    }

    public StringProperty mailProperty() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail.set(mail);
    }

    public LocalDate getMail_date() {
        return mail_date.get();
    }

    public ObjectProperty<LocalDate> mail_dateProperty() {
        return mail_date;
    }

    public void setMail_date(LocalDate mail_date) {
        this.mail_date.set(mail_date);
    }

    public String getText() {
        return text.get();
    }

    public StringProperty textProperty() {
        return text;
    }

    public void setText(String text) {
        this.text.set(text);
    }

    public String getAnswer() {
        return answer.get();
    }

    public StringProperty answerProperty() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer.set(answer);
    }

    public String getVacancy_linc() {
        return vacancy_linc.get();
    }

    public StringProperty vacancy_lincProperty() {
        return vacancy_linc;
    }

    public void setVacancy_linc(String vacancy_linc) {
        this.vacancy_linc.set(vacancy_linc);
    }

    public LocalDate getAnswer_date() {
        return answer_date.get();
    }

    public ObjectProperty<LocalDate> answer_dateProperty() {
        return answer_date;
    }

    public void setAnswer_date(LocalDate answer_date) {
        this.answer_date.set(answer_date);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", vacancy='" + vacancy + '\'' +
                ", mail='" + mail + '\'' +
                ", mail_date=" + mail_date +
                //  ", text='" + text + '\'' +
                ", answer='" + answer + '\'' +
                ", vacancy_linc='" + vacancy_linc + '\'' +
                ", answer_date=" + answer_date +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vacancy)) return false;
        Vacancy vacancy1 = (Vacancy) o;
        return id == vacancy1.id &&
                company.equals(vacancy1.company) &&
                vacancy.equals(vacancy1.vacancy);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, company, vacancy);
    }
}