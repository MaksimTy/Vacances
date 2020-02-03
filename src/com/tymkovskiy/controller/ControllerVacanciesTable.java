package com.tymkovskiy.controller;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.VacancyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;


public class ControllerVacanciesTable {

    private VacancyManager vacancyManager;
    private ObservableList<SimpleVacancyProperty> simpleVacancyProperties;

    @FXML
    private TableView<SimpleVacancyProperty> tableView;
    @FXML
    private TableColumn<SimpleVacancyProperty, Integer> id;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> company;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> vacancy;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> mail;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> link;
    @FXML
    private TableColumn<SimpleVacancyProperty, LocalDate> dateRequest;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> answer;
    @FXML
    private TableColumn<SimpleVacancyProperty, LocalDate> dateAnswer;

    /**
     * Поля и кнопки фильтра.
     */
    @FXML
    private TextField companyField;
    @FXML
    private TextField vacancyField;
    @FXML
    private DatePicker toField;
    @FXML
    private DatePicker fromField;
    @FXML
    private Button search;
    @FXML
    private Button reset;


    @FXML
    public void setSearchButtonClick() throws DAOException {
        String company = this.companyField.getText().trim().toLowerCase();
        CharSequence chs = company.subSequence(0, company.length());
        String vacancy = this.vacancyField.getText().trim().toLowerCase();
        CharSequence vhs = vacancy.subSequence(0, vacancy.length());

        //Фильтрация по полям "company" и "vacancy".
        List<SimpleVacancyProperty> select = this.simpleVacancyProperties.
                stream().
                filter(x -> x.getCompany().toLowerCase().contains(chs)).
                filter(x -> x.getVacancy().toLowerCase().contains(vhs)).
                collect(Collectors.toList());
        //Фильтрация по полям "from" и "to".
        selectAfterMailDate(select);
        selectBeforeMailDate(select);

        this.resetSimpleVacancyProperties(select);
    }

    /**
     * Метод обновляет таблицу полным актуальным набором вакансий
     *
     * @param event
     * @throws DAOException
     */
    @FXML
    public void setResetButtonClick(ActionEvent event) throws DAOException {
        this.setSimpleVacancyProperties();
        this.fillTable();
        this.clearFilter();
    }

    /**
     * Метод заполняет таблицу результатами поиска и заново заполняет
     * this.simpleVacancyProperties полным набором вакансий.
     *
     * @param list результат поиска
     * @throws DAOException
     */
    private void resetSimpleVacancyProperties(List<SimpleVacancyProperty> list) throws DAOException {
        this.simpleVacancyProperties.clear();
        this.simpleVacancyProperties.addAll(list);
        this.fillTable();
        this.setSimpleVacancyProperties();
    }

    public void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        this.setSimpleVacancyProperties();
        this.fillTable();
    }

    /**
     * Метод заполняет таблицу данными из this.simpleVacancyProperties.
     */
    private void fillTable() {
        this.tableView.setItems(this.simpleVacancyProperties);
        // определяем фабрику для столбца с привязкой к свойству id
        this.id.setCellValueFactory(new PropertyValueFactory<>("id"));
        // определяем фабрику для столбца с привязкой к свойству company
        this.company.setCellValueFactory(new PropertyValueFactory<>("company"));
        // определяем фабрику для столбца с привязкой к свойству vacancy
        this.vacancy.setCellValueFactory(new PropertyValueFactory<>("vacancy"));
        // определяем фабрику для столбца с привязкой к свойству mail
        this.mail.setCellValueFactory(new PropertyValueFactory<>("mail"));
        // определяем фабрику для столбца с привязкой к свойству link
        this.link.setCellValueFactory(new PropertyValueFactory<>("vacancy_linc"));
        // определяем фабрику для столбца с привязкой к свойству answer
        this.answer.setCellValueFactory(new PropertyValueFactory<>("answer"));
        // определяем фабрику для столбца с привязкой к свойству dateRequest
        this.dateRequest.setCellValueFactory(new PropertyValueFactory<>("mail_date"));
        // определяем фабрику для столбца с привязкой к свойству dateAnswer
        this.dateAnswer.setCellValueFactory(new PropertyValueFactory<>("answer_date"));
    }

    /**
     * Метод заполняет this.simpleVacancyProperties актуалными данными, обращается к
     * базе.
     *
     * @throws DAOException
     */
    public void setSimpleVacancyProperties() throws DAOException {
        this.simpleVacancyProperties = FXCollections.observableArrayList();
        this.vacancyManager.
                getVacancies().
                stream().
                forEach(x -> this.simpleVacancyProperties.add(new SimpleVacancyProperty(x)));
    }

    public ObservableList<SimpleVacancyProperty> getSimpleVacancyProperties() {
        return this.simpleVacancyProperties;
    }

    /**
     * Метод фильтрует List<SimpleVacancyProperty> по полю
     * mail_date, отбирая значения больше и равные текущему
     * значению поля fromField
     *
     * @param select отфильтрованный по mail_date больше и
     *               равной fromField.
     */
    private void selectBeforeMailDate(List<SimpleVacancyProperty> select) {
        LocalDate to = this.toField.getValue();
        if (!this.toField.getEditor().getText().isEmpty()) {
            for (int i = 0; i < select.size(); i++) {
                if (!select.get(i).getMail_date().isAfter(to)) {
                    continue;
                }
                select.remove(i);
                i--;
            }
        }
    }

    /**
     * Метод фильтрует List<SimpleVacancyProperty> по полю
     * mail_date, отбирая значения меньшие и равные текущему
     * значению поля toField.
     *
     * @param select отфильтрованный по mail_date меньше и
     *               равной toField.
     */
    private void selectAfterMailDate(List<SimpleVacancyProperty> select) {
        LocalDate from = this.fromField.getValue();
        if (!this.fromField.getEditor().getText().isEmpty()) {
            for (int i = 0; i < select.size(); i++) {
                if (!select.get(i).getMail_date().isBefore(from)) {
                    continue;
                }
                select.remove(i);
                i--;
            }
        }
    }

    /**
     * Метод очищает поля фильтров.
     */
    private void clearFilter() {
        this.companyField.clear();
        this.vacancyField.clear();
        this.fromField.getEditor().clear();
        this.toField.getEditor().clear();
    }

}
