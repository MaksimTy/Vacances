package com.tymkovskiy.view;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.controller.VacancyManager;
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
    private ObservableList<Vacancy> vacances;
    private ControllerVacancyCard controllerVacancyCard;

    @FXML
    private TableView<Vacancy> tableView;
    @FXML
    private TableColumn<Vacancy, Integer> id;
    @FXML
    private TableColumn<Vacancy, String> company;
    @FXML
    private TableColumn<Vacancy, String> vacancy;
    @FXML
    private TableColumn<Vacancy, String> mail;
    @FXML
    private TableColumn<Vacancy, String> link;
    @FXML
    private TableColumn<Vacancy, LocalDate> dateRequest;
    @FXML
    private TableColumn<Vacancy, String> answer;
    @FXML
    private TableColumn<Vacancy, LocalDate> dateAnswer;

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
        List<Vacancy> select = this.vacances.
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
     * this.vacances полным набором вакансий.
     *
     * @param list результат поиска
     * @throws DAOException
     */
    private void resetSimpleVacancyProperties(List<Vacancy> list) throws DAOException {
        this.vacances.clear();
        this.vacances.addAll(list);
        this.fillTable();
        this.setSimpleVacancyProperties();
    }

    public void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        this.setSimpleVacancyProperties();
        this.fillTable();
        this.tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    try {
                        System.out.println(vacancyManager.getVacancyById(newValue.getId()));
                        controllerVacancyCard.setCurrentVacancy(vacancyManager.getVacancyById(newValue.getId()));
                    } catch (DAOException e) {
                        e.printStackTrace();
                    }
                });
    }

    /**
     * Метод заполняет таблицу данными из this.vacances.
     */
    private void fillTable() {
        this.tableView.setItems(this.vacances);
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
     * Метод заполняет this.vacances актуалными данными, обращается к
     * базе.
     *
     * @throws DAOException
     */
    public void setSimpleVacancyProperties() throws DAOException {
        this.vacances = FXCollections.observableArrayList();
        this.vacancyManager.
                getVacancies().
                stream().
                forEach(x -> this.vacances.add(x));
    }

    public ObservableList<Vacancy> getSimpleVacancyProperties() {
        return this.vacances;
    }

    /**
     * Метод фильтрует List<Vacancy> по полю
     * mail_date, отбирая значения больше и равные текущему
     * значению поля fromField
     *
     * @param select отфильтрованный по mail_date больше и
     *               равной fromField.
     */
    private void selectBeforeMailDate(List<Vacancy> select) {
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
     * Метод фильтрует List<Vacancy> по полю
     * mail_date, отбирая значения меньшие и равные текущему
     * значению поля toField.
     *
     * @param select отфильтрованный по mail_date меньше и
     *               равной toField.
     */
    private void selectAfterMailDate(List<Vacancy> select) {
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

    public void setControllerVacancyCard(ControllerVacancyCard controllerVacancyCard) {
        this.controllerVacancyCard = controllerVacancyCard;
    }
}
