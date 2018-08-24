package controllers;

import javafx.fxml.FXML;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
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

    @FXML
    private TextField txtA0, txtA1, txtA2, txtA3,
                      txtA4, txtA5, txtA6, txtA7,
                      txtA8, txtA9;
    @FXML
    private TextField txtInterval;
    @FXML
    private TextField txtStep;


    private final int RANGE = 0;
    private final int STEP_SIZE = 1;

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

        lineChartExpData.getData().clear();

        XYChart.Series series = new XYChart.Series();
        series.setName("Полином");

        Polynom pol = getPolynomFromUI();

        if(pol != null) {
            for (int i = 0; i < pol.getIntSteps(); ++i) {
                series.getData().add(new XYChart.Data(i, pol.getFunctionArray().get(i)));
                //if (i % 1000 == 0) System.out.println(i);
            }
            lineChartExpData.getData().add(series);
        }

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

    //------------------------------------------------------------------------------
    private Polynom getPolynomFromUI(){
        Polynom polynom;
        try {

            double[] coeff = getCoefficient();
            double[] calc = getRangeAndStep();

            if(calc != null) {
                polynom = new Polynom(coeff[9],coeff[8], coeff[7], coeff[6], coeff[5],
                        coeff[4], coeff[3], coeff[2], coeff[1],
                        coeff[0], calc[RANGE], calc[STEP_SIZE]);
                return polynom;
            }

        }catch (NumberFormatException e){
            showInfMassage("Ошибка преобразования данных!",e.toString());
        }

        return null;
    }

    private double[] getCoefficient(){
        double[] coefficient = new double[10];

        coefficient[0] = Double.valueOf(txtA0.getText());
        coefficient[1] = Double.valueOf(txtA1.getText());
        coefficient[2] = Double.valueOf(txtA2.getText());
        coefficient[3] = Double.valueOf(txtA3.getText());
        coefficient[4] = Double.valueOf(txtA4.getText());
        coefficient[5] = Double.valueOf(txtA5.getText());
        coefficient[6] = Double.valueOf(txtA6.getText());
        coefficient[7] = Double.valueOf(txtA7.getText());
        coefficient[8] = Double.valueOf(txtA8.getText());
        coefficient[9] = Double.valueOf(txtA9.getText());

        return coefficient;
    }

    //index 0 - for range
    //      1 - for step size
    private double[] getRangeAndStep(){
        double[] randAndStep = new double[2];

        double range = Double.valueOf(txtInterval.getText());
        double stepSize = Double.valueOf(txtStep.getText());

        if(range < 0 || stepSize <= 0) {
            showInfMassage("Неверные параметры интервала и(или) шага", "");
            return null;
        }

        if(range/stepSize < 2){
            showInfMassage("Значение интервала мало или значение шага велико", "");
            return null;
        }

        randAndStep[RANGE] = range;
        randAndStep[STEP_SIZE] = stepSize;

        return randAndStep;
    }

    private void showInfMassage(String header, String content){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Ошибка!");
        alert.setHeaderText(header);
        alert.setContentText(content);

        alert.showAndWait();
    }
}
