package com.tymkovskiy.ui;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class UIVacances extends Application {

    public static void main(String[] args) {

        launch(args);
    }

    @Override
    public void start(Stage stage) throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().
                getResource("/vacances.fxml"));
        Parent root = loader.load();

        Scene scene = new Scene(root);
        stage.setScene(scene);
        stage.setTitle("Vacances");
        scene.getStylesheets().add(
                UIVacances.class.
                        getResource("/style.css").
                        toExternalForm());

        stage.setWidth(620);
        stage.setMinWidth(620);
        stage.setHeight(660);
        stage.setMinHeight(660);

        stage.show();
    }
}