package model;

public class Algorithm {
    // GA parameters
    private Population populEvolve;
    private Selection selection;
    private Crossover crossover;
    private boolean elitism;

    private double mutationRate;

    public Algorithm(Population population, boolean elitism,
                     Selection selection, Crossover crossover,
                     double mutationRate){
        //this.populEvolve = new Population(populationSize,true);
        this.populEvolve = population;

        this.elitism = elitism;
        this.selection = selection;
        this.crossover = crossover;
        this.mutationRate = mutationRate;
    }

    //---------------------------------------------------------------------
    // public methods

    // evolve a population
    public Population evolvePopulation(Population pop){
        Population newGeneration = new Population(pop.size(),false);

        // save best individual
        if(elitism)
            newGeneration.saveIndividual(0, pop.getFittest());

        // start of population
        int startIndex = elitism ? 1 : 0;

        if(selection.isRanked()){
            // TODO sort the population
        }

        // get parent pool
        Population parentPop = selection.getParentPopulation(pop);

        // create new individuals with crossover
        for(int i = startIndex; i < pop.size(); ++i){
            int random;

            random = randomWithRange(0, pop.size()-1);
            Individual parentA = parentPop.getIndividual(random);

            random = randomWithRange(0, pop.size()-1);
            Individual parentB = parentPop.getIndividual(random);

            Individual offspring = crossover.crossover(parentA, parentB);

            newGeneration.saveIndividual(i, offspring);
        }

        // mutate the population
        for(int i = startIndex; i < newGeneration.size(); ++i){
            mutate(newGeneration.getIndividual(i));
        }

        return newGeneration;
    }


    // mutate the individual
    private void mutate(Individual indiv){
        for(int i = 0; i < indiv.size(); ++i){
            if(Math.random() <= mutationRate){
                // or inverse gene?
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i,gene);
            }
        }
    }

    /*private int randomWithRange(int min, int max)
    {
        int range = Math.abs(max - min) + 1;
        return (int)(Math.random() * range) + (min <= max ? min : max);
    }*/

    private int randomWithRange(int min, int max)
    {
        int range = (max - min) + 1;
        return (int)(Math.random() * range) + min;
    }

}
