package model;

public class Population {
    Individual[] individuals;

    public Population(int populationSize, boolean initialize){
        individuals = new Individual[populationSize];
        if(initialize){
            for(int i = 0; i < size(); i++){
                Individual newIndividual = new Individual();
                newIndividual.generateIndividual();
                saveIndividual(i, newIndividual);
            }
        }
    }

    //---------------------------------------------------------------------
    //Getters
    public Individual getIndividual(int index){
        return individuals[index];
    }

    public Individual getFittest(){
        Individual fittestIndiv = individuals[0];
        for(int i = 0; i < size(); ++i){
            if(fittestIndiv.getFitness() <= getIndividual(i).getFitness()){
                fittestIndiv = getIndividual(i);
            }
        }
        return fittestIndiv;
    }

    //---------------------------------------------------------------------
    //Public methods
    public int size(){
        return individuals.length;
    }

    public void saveIndividual(int index, Individual indiv){
        individuals[index] = indiv;
    }
}
