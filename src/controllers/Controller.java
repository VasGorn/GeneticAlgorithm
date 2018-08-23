package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;

import java.io.File;

public class Controller {
    @FXML
    private Button btnChooseFile;
    @FXML
    private Label lblFilePath;

    @FXML
    private RadioButton rBtnTable;
    @FXML
    private RadioButton rBtnPolynom;

    @FXML
    private LineChart<Float,Float> lineChartExpData;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    @FXML
    private AnchorPane aPaneTable;
    @FXML
    private AnchorPane aPanePolynom;


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

    public void btnLoadDataClicked(){
        aPaneTable.setDisable(true);


        //Defining X axis
        xAxis.setLabel("dsfxcvsdfxcvbr");

        XYChart.Series series = new XYChart.Series();
        series.setName("No of schools in an year");

        series.getData().add(new XYChart.Data(1970, 15));
        series.getData().add(new XYChart.Data(1980, 30));
        series.getData().add(new XYChart.Data(1990, 60));
        series.getData().add(new XYChart.Data(2000, 120));
        series.getData().add(new XYChart.Data(2013, 240));
        series.getData().add(new XYChart.Data(2014, 300));
        lineChartExpData.getData().add(series);
    }

    public void rBtnTableClicked(){
        rBtnPolynom.setSelected(false);
        aPanePolynom.setDisable(true);
        aPaneTable.setDisable(false);
    }

    public void rBtnPolynomClicked(){
        rBtnTable.setSelected(false);
        aPanePolynom.setDisable(false);
        aPaneTable.setDisable(true);
    }
}
