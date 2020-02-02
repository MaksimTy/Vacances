package com.tymkovskiy;

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

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        this.stage = stage;
        this.stage.setTitle("Vacancies");
        //  stage.setWidth(620);
        //  stage.setMinWidth(620);
        //  stage.setHeight(660);
        //  stage.setMinHeight(660);
        this.initRootLayout();
        this.showTableVacancies();
     //this.showCardVacancy();

    }

    public void initRootLayout() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/RootLayout.fxml"));
        this.rootLayout = (BorderPane) loader.load();
        Scene scene = new Scene(this.rootLayout);
        this.stage.setScene(scene);
        this.stage.show();
    }

    public void showTableVacancies() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/TableVacancies.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        this.rootLayout.setCenter(anchorPane);
    }

    public void showCardVacancy() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(MainApp.class.getResource("../tymkovskiy/view/CardVacancy.fxml"));
        AnchorPane anchorPane = (AnchorPane) loader.load();
        this.rootLayout.setRight(anchorPane);

    }
}