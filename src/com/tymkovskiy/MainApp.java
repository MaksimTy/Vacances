package com.tymkovskiy;

import com.tymkovskiy.view.ControllerVacanciesTable;
import com.tymkovskiy.view.ControllerVacancyCard;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;


public class MainApp extends Application {

    private Stage stage;
    private BorderPane rootLayout;
    private ControllerVacanciesTable controllerVacanciesTable;
    private ControllerVacancyCard controllerVacancyCard;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        this.stage.setTitle("Vacancies");

        this.initRootLayout();
        this.showVacancyOverview();
       // this.showVacancyEditDialog();

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

    }

    public void showVacancyEditDialog() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/VacancyEditDialog.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        this.rootLayout.setRight(anchorPane);

           }
}