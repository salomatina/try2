package ru.mephi;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    //private static Scene scene;
    private DataImporter dataImporter;

    @Override
    public void start(Stage stage) throws IOException {
        //stage.centerOnScreen();
        Button importB = new Button("Import");
        importB.setPrefSize(300, 40);
        importB.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                dataImporter = new DataImporter();
                try {
                    dataImporter.findSheet();
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Good job!");
                    alert.setHeaderText(null);
                    alert.setContentText("Done");
                    alert.showAndWait();
                } catch (Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Warning!!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Something went wrong and we didn't find a file");
                    alert.showAndWait();
                }
            }
        });
        Group buttonImp = new Group(importB);
        Button exportB = new Button("Export");
        exportB.setPrefSize(300, 40);
        exportB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Calculator calculator = new Calculator();
                DataExporter dataExporter = new DataExporter();
                try {
                    dataExporter.writeData(calculator);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Perfectly done!");
                    alert.setHeaderText(null);
                    alert.setContentText("Everything was written");
                    alert.showAndWait();
                } catch (Exception exception) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Some error here!!");
                    alert.setHeaderText(null);
                    alert.setContentText("Nothing was written");
                    alert.showAndWait();
                }
            }
        });
        Group buttonExp = new Group(exportB);
        Button exitB = new Button("exit");
        exitB.setPrefSize(300, 40);
        exitB.setStyle("-fx-background-color: gray");
        exitB.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        Group buttonExit = new Group(exitB);
        VBox root = new VBox(buttonImp, buttonExp, buttonExit);
        Scene scene = new Scene(root, 300, 125);
        stage.setScene(scene);
        stage.setTitle("let's do something!");
        //export.add
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}