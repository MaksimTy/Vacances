package com.tymkovskiy.model;

import com.tymkovskiy.dao.DAO;

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
    private int id;
    /**
     * наименование компании
     */
    private String company;
    /**
     * наименование вакансии
     */
    private String vacancy;
    /**
     * электронный адрес компании
     */
    private String mail;
    /**
     * дата отправки резюме
     */
    private Date mail_date;
    /**
     * текст сопроводительного письма
     */
    private String text;
    /**
     * ответ
     */
    private String answer;
    /**
     * ссылка на вакансию
     */
    private String vacancy_linc;
    /**
     * дата ответа компании
     */
    private Date answer_date;


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

    public Vacancy(int id, String company, String vacancy, String mail, Date mail_date, String text, String vacancy_linc) {
        this.id = id;
        this.company = company;
        this.vacancy = vacancy;
        this.mail = mail;
        this.mail_date = mail_date;
        this.text = text;
        this.vacancy_linc = vacancy_linc;
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
    public Vacancy(String company, String vacancy, String mail, Date mail_date, String text, String vacancy_linc) {
        this.company = company;
        this.vacancy = vacancy;
        this.mail = mail;
        this.mail_date = mail_date;
        this.text = text;
        this.vacancy_linc = vacancy_linc;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public void setAnswer_date(Date answer_date) {
        this.answer_date = answer_date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getCompany() {
        return company;
    }

    public String getVacancy() {
        return vacancy;
    }

    public String getMail() {
        return mail;
    }

    public Date getMail_date() {
        return mail_date;
    }

    public String getText() {
        return text;
    }

    public String getAnswer() {
        return answer;
    }

    public String getVacancy_linc() {
        return vacancy_linc;
    }

    public Date getAnswer_date() {
        return answer_date;
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