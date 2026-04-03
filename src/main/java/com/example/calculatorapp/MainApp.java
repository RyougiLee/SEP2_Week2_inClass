package com.example.calculatorapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.NodeOrientation;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Locale;
import java.util.ResourceBundle;

public class MainApp extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws Exception{
        primaryStage = stage;
        setRoot("English");
    }

    public static void setRoot(String selectedLanguage) throws Exception{
        ResourceBundle bundle = new DbResourceBundle(selectedLanguage);
        FXMLLoader loader = new FXMLLoader(MainApp.class.getResource("/com/example/calculatorapp/main-view.fxml"), bundle);
        Parent root = loader.load();
        if("Arabic".equals(selectedLanguage)){
            root.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        } else {
            root.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);
        }
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Zongru Li / Calculator");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}

class Launcher {
    public static void main(String[] args) {
        MainApp.main(args);
    }
}