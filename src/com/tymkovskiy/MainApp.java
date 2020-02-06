package com.tymkovskiy;

import com.tymkovskiy.dao.DAOException;
import com.tymkovskiy.model.Vacancy;
import com.tymkovskiy.view.VacancyEditDialogController;
import com.tymkovskiy.view.VacancyOverviewController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    private Stage stage;
    private BorderPane rootLayout;
    private VacancyOverviewController vacancyOverviewController;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        this.stage.setTitle("Vacancies");

        this.initRootLayout();
        this.showVacancyOverview();
    }

    public void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/RootLayout.fxml"));
        this.rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(this.rootLayout);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void showVacancyOverview() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/VacancyOverview.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        this.rootLayout.setCenter(anchorPane);

        this.vacancyOverviewController = loader.getController();
        this.vacancyOverviewController.setMainApp(this);

    }

    public void showVacancyEditDialog(Vacancy vacancy) throws IOException, DAOException {

        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/VacancyEditDialog.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        VacancyEditDialogController vacancyEditDialogController = loader.getController();
        vacancyEditDialogController.setVacancyCurrent(vacancy);

        Scene scene = new Scene(anchorPane);
        Stage dialogStage = new Stage();
        dialogStage.setScene(scene);

        dialogStage.setTitle("Edit vacancy");
        dialogStage.initOwner(this.stage);
        dialogStage.initModality(Modality.WINDOW_MODAL);

        vacancyEditDialogController.setDialogStage(dialogStage);
        vacancyEditDialogController.setVacancyOverviewController(this.vacancyOverviewController);

        dialogStage.showAndWait();
        this.vacancyOverviewController.refreshTable();


    }
}