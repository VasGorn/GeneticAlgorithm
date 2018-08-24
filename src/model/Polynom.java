package model;

import java.util.ArrayList;

public class Polynom {
    private double a0;
    private double a1;
    private double a2;
    private double a3;
    private double a4;
    private double a5;
    private double a6;
    private double a7;
    private double a8;
    private double a9;
    //private double stepSize;
    private int intSteps;
    private ArrayList<Double> functionArray;

    public Polynom(double a9, double a8, double a7, double a6,
                   double a5, double a4, double a3, double a2,
                   double a1, double a0, double range, double stepSize){
        this.a0 = a0; this.a1 = a1; this.a2 = a2; this.a3 = a3;
        this.a4 = a4; this.a5 = a5; this.a6 = a6; this.a7 = a7;
        this.a8 = a8; this.a9 = a9;

        //this.stepSize = stepSize;

        double numValue = range/stepSize;
        intSteps = (int)Math.floor(numValue);

        functionArray = new ArrayList<>();

        double value;
        for(int i = 0; i < intSteps; ++i){
            value = a8 * Math.pow(i*stepSize,9) +
                    a8 * Math.pow(i*stepSize,8) +
                    a7 * Math.pow(i*stepSize,7) +
                    a6 * Math.pow(i*stepSize,6) +
                    a5 * Math.pow(i*stepSize,5) +
                    a4 * Math.pow(i*stepSize,4) +
                    a3 * Math.pow(i*stepSize,3) +
                    a2 * Math.pow(i*stepSize,2) +
                    a1 * Math.pow(i*stepSize,1) +
                    a0;
            functionArray.add(value);
        }
    }

    public ArrayList getFunctionArray(){
        return functionArray;
    }

    public int getIntSteps() {
        return intSteps;
    }
}
