package ru.mephi;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.*;

import java.io.File;
import java.util.*;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.apache.poi.xssf.usermodel.XSSFSheet;


/**
 * JavaFX App
 */
public class App extends Application {

    private double[][] data;
    private LinkedHashMap<String, Double[]> hashMap;

    @Override
    public void start(Stage stage) {
        //stage.centerOnScreen();
        TextArea textAreaImp = new TextArea();
        textAreaImp.setEditable(false);
        Group groupTextAreaImp = new Group(textAreaImp);
        textAreaImp.setPrefSize(300, 20);
        Button buttonImpDir = new Button("...");
        buttonImpDir.setPrefSize(30, 37);
        buttonImpDir.setOnAction(actionEvent -> {
            textAreaImp.clear();
            hashMap = null;
            data = null;
            FileChooser fileChooser = new FileChooser();
            File file = fileChooser.showOpenDialog(stage);
            if (file != null) {
                textAreaImp.appendText(file.getAbsolutePath());
            }
            //textAreaImp.appendText(file.getAbsolutePath());
        });
        Group groupButtonImpDir = new Group(buttonImpDir);
        Button buttonImp = new Button("Import");
        buttonImp.setPrefSize(80, 37);
        buttonImp.setOnAction(event -> {
            DataImporter dataImporter = new DataImporter();
            try {
                if (textAreaImp.getText() != null) {
                    XSSFSheet sheet = dataImporter.findSheet(textAreaImp.getText());
                    data = dataImporter.readAllData(sheet);
                } else {
                    throw new NullPointerException("Directory isn't chosen");
                }
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
        });
        Group groupButtonImp = new Group(buttonImp);

        TextArea textAreaExp = new TextArea();
        textAreaExp.setEditable(false);
        Group groupTextAreaExp = new Group(textAreaExp);
        textAreaExp.setPrefSize(220, 20);
        Button buttonExpDir = new Button("...");
        buttonExpDir.setPrefSize(30, 37);
        buttonExpDir.setOnAction(actionEvent -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            File dir = directoryChooser.showDialog(stage);
            if (dir != null) {
                textAreaExp.setText(dir.getAbsolutePath());
            } else {
                textAreaExp.setText(System.getProperty("user.home"));
            }
        });
        Group groupButtonExpDir = new Group(buttonExpDir);

        Button buttonCalc = new Button("Calculate");
        buttonCalc.setPrefSize(410, 37);
        buttonCalc.setOnAction(actionEvent -> {
            Calculator calculator = new Calculator();
            try {
                hashMap = calculator.fillHashMap(data);
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Perfectly done!");
                alert.setHeaderText(null);
                alert.setContentText("Data was successfully calculated");
                alert.showAndWait();
            } catch (Exception e) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Some error here!!");
                alert.setHeaderText(null);
                alert.setContentText("First load the data");
                alert.showAndWait();
            }
        });
        Group groupButtonCalc = new Group(buttonCalc);
        TextArea textAreaName = new TextArea();
        textAreaName.setPrefSize(80, 20);
        Group groupTextAreaName = new Group(textAreaName);
        Button buttonExp = new Button("Export");
        buttonExp.setPrefSize(80, 37);
        buttonExp.setOnAction(actionEvent -> {
            DataExporter dataExporter = new DataExporter();
            try {
                //think of why it doesn't work
                if (textAreaName.getText() != null) {
                    dataExporter.writeData(hashMap, textAreaExp.getText(), textAreaName.getText());
                } else {
                    throw new NumberFormatException("Name isn't written");
                }
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
        });
        Group groupButtonExp = new Group(buttonExp);
        Button buttonExit = new Button("exit");
        buttonExit.setPrefSize(410, 37);
        buttonExit.setStyle("-fx-background-color: gray");
        buttonExit.setOnAction(actionEvent -> System.exit(0));
        Group groupButtonExit = new Group(buttonExit);
        HBox importChild = new HBox(groupButtonImpDir, groupTextAreaImp, groupButtonImp);
        //HBox calcChild = new HBox(groupButtonCalc);
        HBox exportChild = new HBox(groupButtonExpDir, groupTextAreaExp, groupTextAreaName, groupButtonExp);
        VBox root = new VBox(importChild, groupButtonCalc, exportChild, groupButtonExit);
        Scene scene = new Scene(root, 410, 154);
        stage.setScene(scene);
        stage.setTitle("let's do something!");
        stage.setResizable(false);
        stage.show();
    }


    public static void main(String[] args) {
        launch();
    }

}