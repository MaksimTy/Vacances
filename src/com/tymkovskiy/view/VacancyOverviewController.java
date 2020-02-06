package com.tymkovskiy.view;

import com.tymkovskiy.MainApp;
import com.tymkovskiy.controller.VacancyManager;
import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Window;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class VacancyOverviewController {

    private VacancyManager vacancyManager;
    private ObservableList<Vacancy> vacancies;
    private MainApp mainApp;

    @FXML
    private TableView<Vacancy> tableView;
    @FXML
    private TableColumn<Vacancy, Integer> id;
    @FXML
    private TableColumn<Vacancy, String> company;
    @FXML
    private TableColumn<Vacancy, String> vacancy;
    @FXML
    private TableColumn<Vacancy, LocalDate> request;
    @FXML
    private TableColumn<Vacancy, String> answer;
    @FXML
    private TableColumn<Vacancy, LocalDate> replay;


    /**
     * Поля карточки вакансии.
     */
    @FXML
    private Label idLabel;
    @FXML
    private TextField companyField;
    @FXML
    private TextField vacancyField;
    @FXML
    private TextField mailField;
    @FXML
    private TextField linkField;
    @FXML
    private DatePicker requestField;
    @FXML
    private TextArea messageArea;
    @FXML
    private TextField answerField;
    @FXML
    private DatePicker replayField;

    /**
     * Поля фильтра.
     */
    @FXML
    private TextField searchCompanyField;
    @FXML
    private TextField searchVacancyField;
    @FXML
    private DatePicker searchFromDate;
    @FXML
    private DatePicker searchBeforeDate;

    @FXML
    private void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        this.setVacancies();
        this.fillTable();

        this.tableView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) ->
                        this.fillVacancy(newValue));
    }

    public void setMainApp(MainApp mainApp) {
        this.mainApp = mainApp;
    }

    /**
     * Метод заполняет таблицу данными из this.vacancies.
     */
    private void fillTable() {
        this.tableView.setItems(this.vacancies);
        // определяем фабрику для столбца с привязкой к свойству id
        this.id.setCellValueFactory(new PropertyValueFactory<Vacancy, Integer>("id"));
        // определяем фабрику для столбца с привязкой к свойству company
        this.company.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("company"));
        // определяем фабрику для столбца с привязкой к свойству vacancy
        this.vacancy.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("vacancy"));
        // определяем фабрику для столбца с привязкой к свойству dateRequest
        this.request.setCellValueFactory(new PropertyValueFactory<Vacancy, LocalDate>("requestDate"));
        // определяем фабрику для столбца с привязкой к свойству answer
        this.answer.setCellValueFactory(new PropertyValueFactory<Vacancy, String>("answer"));
        // определяем фабрику для столбца с привязкой к свойству replay
        this.replay.setCellValueFactory(new PropertyValueFactory<Vacancy, LocalDate>("replayDate"));
    }

    /**
     * Метод заполняет карточку вакансии
     */
    private void fillVacancy(Vacancy vacancy) {
        this.idLabel.setText(Integer.toString(vacancy.getId()));
        this.companyField.setText(vacancy.getCompany());
        this.vacancyField.setText(vacancy.getVacancy());
        this.mailField.setText(vacancy.getMail());
        this.linkField.setText(vacancy.getLink());
        this.answerField.setText(vacancy.getAnswer());
        this.messageArea.setText(vacancy.getMessage());

        this.requestField.setValue(vacancy.getRequestDate());
        this.replayField.setValue(vacancy.getReplayDate());
    }

    /**
     * Метод заполняет this.vacancies актуалными данными, обращается к
     * базе.
     *
     * @throws DAOException
     */
    private void setVacancies() throws DAOException {
        this.vacancies = FXCollections.observableArrayList();
        this.vacancyManager.
                getVacancies().
                stream().
                forEach(vacancy -> this.vacancies.add(vacancy));
    }

    @FXML
    private void setSearchButtonClick() throws DAOException {
        String company = this.searchCompanyField.getText().trim().toLowerCase();
        CharSequence chs = company.subSequence(0, company.length());
        String vacancy = this.searchVacancyField.getText().trim().toLowerCase();
        CharSequence vhs = vacancy.subSequence(0, vacancy.length());
        //Фильтрация по полям "searchCompanyField" и "searchVacancyField".
        List<Vacancy> select = this.vacancies.
                stream().
                filter(x -> x.getCompany().toLowerCase().contains(chs)).
                filter(x -> x.getVacancy().toLowerCase().contains(vhs)).
                collect(Collectors.toList());
        //Фильтрация по полям "searchFromDate" и "searchBeforeDate".
        this.selectFromDate(select);
        this.selectBeforeDate(select);

        this.vacancies = FXCollections.observableArrayList(select);
        this.refreshTable();
    }

    public void refreshTable() throws DAOException {
        this.fillTable();
        this.setVacancies();
    }

    /**
     * Метод фильтрует List<Vacancy> по полю
     * replayDate, отбирая значения меньшие и равные текущему
     * значению поля searchBeforeDate
     *
     * @param select отфильтрованный по replayDate меньше и
     *               равной searchBeforeDate.
     */
    private void selectBeforeDate(List<Vacancy> select) {
        LocalDate before = this.searchBeforeDate.getValue();
        if (!this.searchBeforeDate.getEditor().getText().isEmpty()) {
            for (int i = 0; i < select.size(); i++) {
                if (!select.get(i).getRequestDate().isAfter(before)) {
                    continue;
                }
                select.remove(i);
                i--;
            }
        }
    }

    /**
     * Метод фильтрует List<Vacancy> по полю
     * requestDate, отбирая значения больше и равные текущему
     * значению поля searchFromDate
     *
     * @param select отфильтрованный по requestDate больше и
     *               равной searchFromDate.
     */
    private void selectFromDate(List<Vacancy> select) {
        LocalDate from = this.searchFromDate.getValue();
        if (!this.searchFromDate.getEditor().getText().isEmpty()) {
            for (int i = 0; i < select.size(); i++) {
                if (!select.get(i).getRequestDate().isBefore(from)) {
                    continue;
                }
                select.remove(i);
                i--;
            }
        }
    }

    /**
     * Действия при нажании кнопки "New..."
     */
    @FXML
    private void setNewButtonClick() throws IOException, DAOException {
        this.mainApp.showVacancyEditDialog(null);
    }

    /**
     * Действия при нажании кнопки "Edit"
     */
    @FXML
    private void setEditButtonClick() throws IOException, DAOException {
        Vacancy currentVacancy = this.tableView.getSelectionModel().getSelectedItem();
        this.mainApp.showVacancyEditDialog(currentVacancy);
    }

    /**
     * Действия при нажании кнопки "Delete"
     */
    @FXML
    private void setDeleteButtonClick() throws IOException, DAOException, SQLException {

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Removing...");
        alert.setHeaderText("Are you sure?");
        alert.setContentText("The entry will be deleted!");
        alert.showAndWait();

        if (alert.getResult() == ButtonType.OK) {
            Vacancy currentVacancy = this.tableView.getSelectionModel().getSelectedItem();
            this.vacancyManager.deleteVacancy(currentVacancy.getId());
            this.refreshTable();
        }
        this.refreshTable();
    }


}
