package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    @FXML
    private Button btnChooseFile;
    @FXML
    private Label lblFilePath;

    public void btnChooseFileClicked(){
        System.out.println("Hi");
        FileChooser fileChooser = new FileChooser();

        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "CSV files (*.csv)","*.csv");
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(null);
        if(file != null){
            lblFilePath.setText(file.getPath());
        }

    }
}
