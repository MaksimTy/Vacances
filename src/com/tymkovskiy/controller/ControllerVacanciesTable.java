package com.tymkovskiy.controller;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.VacancyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


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
    private TableColumn<SimpleVacancyProperty, String> dateRequest;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> answer;
    @FXML
    private TableColumn<SimpleVacancyProperty, String> dateAnswer;


    public void initialize() throws DAOException {

        this.getList();
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

    public void getList() throws DAOException {
        this.vacancyManager = new VacancyManager();
        this.simpleVacancyProperties = FXCollections.observableArrayList();
        this.vacancyManager.
                getVacancies().
                stream().
                forEach(x -> this.simpleVacancyProperties.add(new SimpleVacancyProperty(x)));
    }
}
