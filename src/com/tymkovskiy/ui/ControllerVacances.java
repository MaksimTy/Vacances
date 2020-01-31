package com.tymkovskiy.ui;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.model.VacancyManager;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class ControllerVacances {

    public VacancyManager vacancyManager;


    @FXML
    private Label id;
    @FXML
    private TextField company;
    @FXML
    private TextField vacancy;
    @FXML
    private TextField mail;
    @FXML
    private TextField link;
    @FXML
    private DatePicker dateRequest;
    @FXML
    private TextArea message;
    @FXML
    private ComboBox<String> answer;
    @FXML
    private DatePicker dateAnswer;

    @FXML
    private Button back;
    @FXML
    private Button front;
    @FXML
    private Button add;
    @FXML
    private Button save;


    public void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        Vacancy vacancy = this.vacancyManager.getVacancyById(10);
        this.setFields(vacancy);
    }

    @FXML
    public void setBackButtonClick(ActionEvent actionEvent) throws DAOException {
        int id = Integer.parseInt(this.id.getText()) - 1;
        setFields(this.vacancyManager.getVacancyById(id));
    }

    @FXML
    public void setFrontButtonClick(ActionEvent event) throws DAOException {
        int id = Integer.parseInt(this.id.getText()) + 1;
        setFields(this.vacancyManager.getVacancyById(id));
    }

    @FXML
    public void setAddBottonClick(ActionEvent actionEvent) throws DAOException {
          }

    @FXML
    public void setSaveBottonClick(ActionEvent event) {
    }

    /**
     * Метод инициализирует поля формы значениями
     * полей вакансии
     *
     * @param vacancy вакансия
     */
    private void setFields(Vacancy vacancy) {
        this.id.setText(String.valueOf(vacancy.getId()));
        this.company.setText(vacancy.getCompany());
        this.vacancy.setText((vacancy.getVacancy()));
        this.mail.setText(vacancy.getMail());
        this.link.setText(vacancy.getVacancy_linc());
        this.dateRequest.setValue(
                Convertor.convertDateToLocalDate(vacancy.getMail_date()));
        this.message.setText(vacancy.getText());
        this.answer.getEditor().setText(vacancy.getAnswer());
        this.dateAnswer.setValue(
                Convertor.convertDateToLocalDate(vacancy.getAnswer_date()));
    }


}
