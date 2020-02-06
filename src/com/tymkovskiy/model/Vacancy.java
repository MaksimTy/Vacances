package com.tymkovskiy.model;

import com.tymkovskiy.util.Convertor;
import javafx.beans.property.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.Objects;
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
    private ObjectProperty<LocalDate> requestDate;
    /**
     * текст сопроводительного письма
     */
    private StringProperty message;
    /**
     * ответ
     */
    private StringProperty answer;
    /**
     * ссылка на вакансию
     */
    private StringProperty link;
    /**
     * дата ответа компании
     */
    private ObjectProperty<LocalDate> replayDate;


    /**
     * Constructor
     *
     * @param id           id вакансии
     * @param company      наименование компании
     * @param vacancy      электронный адрес компании
     * @param mail         электронный адрес компании
     * @param requestDate    дата отправки резюме
     * @param message         текст сопроводительного письма
     * @param link ссылка на вакансию
     */

    public Vacancy(
            int id,
            String company,
            String vacancy,
            String mail,
            Date requestDate,
            String message,
            String link) {
        this.id = new SimpleIntegerProperty(id);
        this.company = new SimpleStringProperty(company);
        this.vacancy = new SimpleStringProperty(vacancy);
        this.mail = new SimpleStringProperty(mail);
        this.requestDate = new SimpleObjectProperty<LocalDate>(
                Convertor.convertDateToLocalDate(requestDate));
        this.message = new SimpleStringProperty(message);
        this.link = new SimpleStringProperty(link);

        this.answer = new SimpleStringProperty();
        this.replayDate = new SimpleObjectProperty<LocalDate>();
    }

    /**
     * Конструктор для нового объекта, не имеет id. Id - максимальный текущий id + 1.
     *
     * @param company      наименование компании
     * @param vacancy      электронный адрес компании
     * @param mail         электронный адрес компании
     * @param requestDate    дата отправки резюме
     * @param message         текст сопроводительного письма
     * @param link ссылка на вакансию
     */
    public Vacancy(
            String company,
            String vacancy,
            String mail,
            Date requestDate,
            String message,
            String link) {
        this.company = new SimpleStringProperty(company);
        this.vacancy = new SimpleStringProperty(vacancy);
        this.mail = new SimpleStringProperty(mail);
        this.requestDate = new SimpleObjectProperty<LocalDate>(
                Convertor.convertDateToLocalDate(requestDate));
        this.message = new SimpleStringProperty(message);
        this.link = new SimpleStringProperty(link);

        this.id = new SimpleIntegerProperty();
        this.answer = new SimpleStringProperty();
        this.replayDate = new SimpleObjectProperty<LocalDate>();
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

    public LocalDate getRequestDate() {
        return requestDate.get();
    }

    public ObjectProperty<LocalDate> requestDateProperty() {
        return requestDate;
    }

    public void setRequestDate(LocalDate requestDate) {
        this.requestDate.set(requestDate);
    }

    public String getMessage() {
        return message.get();
    }

    public StringProperty messageProperty() {
        return message;
    }

    public void setMessage(String message) {
        this.message.set(message);
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

    public String getLink() {
        return link.get();
    }

    public StringProperty linkProperty() {
        return link;
    }

    public void setLink(String link) {
        this.link.set(link);
    }

    public LocalDate getReplayDate() {
        return replayDate.get();
    }

    public ObjectProperty<LocalDate> replayDateProperty() {
        return replayDate;
    }

    public void setReplayDate(LocalDate replayDate) {
        this.replayDate.set(replayDate);
    }

    @Override
    public String toString() {
        return "Vacancy{" +
                "id=" + id +
                ", company='" + company + '\'' +
                ", vacancy='" + vacancy + '\'' +
                ", mail='" + mail + '\'' +
                ", mail_date=" + requestDate +
                //  ", text='" + text + '\'' +
                ", answer='" + answer + '\'' +
                ", vacancy_linc='" + link + '\'' +
                ", answer_date=" + replayDate +
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