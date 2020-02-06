package com.tymkovskiy.view;

import com.tymkovskiy.controller.VacancyManager;
import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.util.Convertor;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.stage.Stage;


/**
 * Окно для изменения информации о вакансии.
 */
public class VacancyEditDialogController {


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
    private DatePicker request;
    @FXML
    private DatePicker replay;
    @FXML
    private ComboBox<String> answer;
    @FXML
    private TextArea message;

    private VacancyManager vacancyManager;
    private Vacancy vacancyCurrent;
    private Stage dialogStage;
    private boolean okClicked = false;
    private boolean isNewVacancy = false;
    private VacancyOverviewController vacancyOverviewController;


    @FXML
    private void initialize() throws DAOException {
        this.vacancyManager = new VacancyManager();
        //инициализируем список answer
        ObservableList<String> answers = FXCollections.observableArrayList(
                this.vacancyManager.getAnswersList());
        this.answer.setItems(answers);
    }

    /**
     * Метод проверяет корректность ввода полей вакансии.
     *
     * @return true если ввод корректен.
     */
    private boolean isInputValid() {
        StringBuilder errorMessage = new StringBuilder();

        if (this.company.getText() == null || this.company.getText().trim().isEmpty()) {
            errorMessage.append("No valid company name!\n");
        }
        if (this.vacancy.getText() == null || this.vacancy.getText().trim().isEmpty()) {
            errorMessage.append("No valid vacancy name!\n");
        }
        if (!this.mail.getText().isEmpty() && !Convertor.isEmailValidate(this.mail.getText())) {
            errorMessage.append("No valid mail!\n");
        }
        if (this.mail.getText().isEmpty() && this.link.getText().isEmpty()) {
            errorMessage.append("No mail or linc!\n");
        }
        if (this.request.getValue() == null) {
            errorMessage.append("No request date!\n");
        }

        if (errorMessage.length() == 0) {
            return true;
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.initOwner(dialogStage);
            alert.setTitle("Invalid fields");
            alert.setHeaderText("Please correct invalid fields");
            alert.setContentText(errorMessage.toString());
            alert.showAndWait();
            return false;
        }
    }

    /**
     * Действия при нажании кнопки "OK"
     */
    @FXML
    private void setOkButtonClick() throws DAOException {
        this.getCurrentVacancy();
        if (this.isNewVacancy) {
            this.vacancyManager.addVacancy(this.vacancyCurrent);
        } else {
            this.vacancyManager.updateVacancy(this.vacancyCurrent);
        }
        this.okClicked = true;
        this.dialogStage.close();
        this.vacancyOverviewController.refreshTable();

    }

    /**
     * Действия при нажатии кнопки "Cancel"
     */
    @FXML
    private void setCancelButtonClick() throws DAOException {
        this.dialogStage.close();
        this.vacancyOverviewController.refreshTable();
    }

    public void setDialogStage(Stage dialogStage) {
        this.dialogStage = dialogStage;
    }

    /**
     * Метод перехватывает значение vacancy, от VacancyOverviewController
     * и отображает его в полях формы.
     *
     * @param vacancy
     * @throws DAOException
     */
    public void setVacancyCurrent(Vacancy vacancy) throws DAOException {
        this.vacancyCurrent = vacancy;
        this.showCurrentVacancy();
    }

    /**
     * Метод отображает в полях формы данные поля вакансии.
     *
     * @throws DAOException
     */
    private void showCurrentVacancy() throws DAOException {
        if (this.vacancyCurrent == null) {
            this.isNewVacancy = true;
            //
        } else {
            this.id.setText(Integer.toString(this.vacancyCurrent.getId()));
            this.company.setText(this.vacancyCurrent.getCompany());
            this.vacancy.setText(this.vacancyCurrent.getVacancy());
            this.mail.setText(this.vacancyCurrent.getMail());
            this.link.setText(this.vacancyCurrent.getLink());
            this.request.setValue(this.vacancyCurrent.getRequestDate());
            this.replay.setValue(this.vacancyCurrent.getReplayDate());
            this.answer.getEditor().setText(this.vacancyCurrent.getVacancy());
            this.message.setText(this.vacancyCurrent.getMessage());
        }
    }

    /**
     * Метод обновляет поле vacancyCurrent текущими значениями
     * полей формы.
     */
    private void getCurrentVacancy() {
        if (this.isInputValid()) {

            this.vacancyCurrent = new Vacancy(
                    this.company.getText(),
                    this.vacancy.getText(),
                    this.mail.getText(),
                    Convertor.convertLocalDateToDate(this.request.getValue()),
                    this.message.getText(),
                    this.link.getText());

            this.vacancyCurrent.setAnswer(
                    this.answer.getValue());
            this.vacancyCurrent.setReplayDate(
                    this.replay.getValue());
            if (!isNewVacancy) {
                this.vacancyCurrent.setId(
                        Integer.parseInt(this.id.getText()));
            }
        }
    }

    public void setVacancyOverviewController(VacancyOverviewController vacancyOverviewController) {
        this.vacancyOverviewController = vacancyOverviewController;
    }
}
