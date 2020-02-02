package com.tymkovskiy.controller;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.model.VacancyManager;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class ControllerVacanciesTable {

    private VacancyManager vacancyManager;

    @FXML
    private TableView<Vacancy> tableView;
    @FXML
    private TableColumn<Vacancy, String> id;
    @FXML
    private TableColumn<Vacancy, String> company;
    @FXML
    private TableColumn<Vacancy, String> vacancy;
    @FXML
    private TableColumn<Vacancy, String> mail;
    @FXML
    private TableColumn<Vacancy, String> link;
    @FXML
    private TableColumn<Vacancy, String> dateRequest;
    @FXML
    private TableColumn<Vacancy, String> answer;
    @FXML
    private TableColumn<Vacancy, String> dateAnswer;
    private Object String;


    @FXML
    public TableView getTableView() throws DAOException {

        ObservableList<Vacancy> vacancies =
                FXCollections.observableArrayList(this.vacancyManager.getVacancies());

        this.tableView.setItems(vacancies);



        return tableView;
    }
}
