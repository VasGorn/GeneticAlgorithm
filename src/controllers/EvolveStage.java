package controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import model.*;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;

public class EvolveStage implements Initializable {
    @FXML
    private Button btnStartEvolve;

    @FXML
    private LineChart<Double, Double> chartEvolve;
    @FXML
    private NumberAxis xAxis;
    @FXML
    private NumberAxis yAxis;

    public void btnStartEvolveClicked(){
        FitnessCalc.setSolution("1111000000000000000000000000000001000000000000000000000000001111");
        Population myPop = new Population(5, true);

        System.out.println("TEST SORTING ...........");
        System.out.println("BEFORE:\n" + myPop.toString());

        Selection.insertIntoSort(myPop.getIndividualsArray());

        System.out.println("AFTER:\n" + myPop.toString());


        Selection tornSel = new Selection(Selection.TOURNAMENT);
        Crossover unifCross = new Crossover(Crossover.UNIFORM);

        Algorithm algorithm = new Algorithm(myPop,true,tornSel,
                                            unifCross,0.017);

        int generationCount = 0;

        XYChart.Series series = prepareChart();

        while (myPop.getFittest().getFitness() < FitnessCalc.getNumMaxFitness()){
            generationCount++;
            System.out.println("Generation: " + generationCount +
                               " Fittest: " + myPop.getFittest().getFitness());
            myPop = algorithm.evolvePopulation(myPop);

            series.getData().add(new XYChart.Data(generationCount, myPop.getFittest().getFitness()));

        }
        System.out.println("Solution found!");
        System.out.println("Generation: " + generationCount);
        System.out.println("Genes:");
        System.out.println(myPop.getFittest());

        chartEvolve.getData().add(series);

    }

    private XYChart.Series prepareChart(){
        // clear chart data
        chartEvolve.getData().clear();

        // the name of axis
        xAxis.setLabel("Поколения");
        yAxis.setLabel("Наиболее приспособленная особь");

        // data points on line chart
        XYChart.Series series = new XYChart.Series();
        //series.setName("эволюция");

        return series;
    }

    //-------------------------------------------------------------------
    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }
}
