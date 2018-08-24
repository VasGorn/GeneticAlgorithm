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
import model.Polynom;

import java.io.File;
import java.util.ArrayList;

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

        xAxis.setLabel("Ось абцисс");
        yAxis.setLabel("Ось ординат");

        XYChart.Series series = new XYChart.Series();
        series.setName("Полином");

        Polynom polynom = new Polynom(0,0,0,0,0,0,1,0,0,3,0.0001);
        ArrayList<Double> arrayList = polynom.getFunctionArray();

        for(int i = 0; i < polynom.getIntSteps(); ++i){
            series.getData().add(new XYChart.Data(i, arrayList.get(i)));
            if(i%1000 == 0) System.out.println(i);
        }

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

    public void btnCancelClicked(){
        System.exit(0);
    }
}
