package model;

public class Algorithm {
    // GA parameters
    private static final double uniformRate = 0.5;
    private static final double mutationRate = 0.015;
    private static final int tournamentSize = 5;
    private static final boolean elitism = true;

    // public methods

    // evolve a population
    public static Population evolvePopulation(Population pop){
        Population newPopulation = new Population(pop.size(),false);

        // save best individual
        if(elitism){
            newPopulation.saveIndividual(0, pop.getFittest());
        }

        // start of population
        int startIndex;
        if(elitism){
            startIndex = 1;
        }else{
            startIndex = 0;
        }

        // create new individuals with crossover
        for(int i = startIndex; i < pop.size(); ++i){
            Individual ind1 = tournamentSelection(pop);
            Individual ind2 = tournamentSelection(pop);

            Individual newIndiv = crossover(ind1, ind2);

            newPopulation.saveIndividual(i, newIndiv);
        }

        // mutate the population
        for(int i = startIndex; i < newPopulation.size(); ++i){
            mutate(newPopulation.getIndividual(i));
        }

        return newPopulation;
    }

    // crossover individuals
    private static Individual crossover(Individual ind1, Individual ind2){
        Individual newSuccessor = new Individual();
        for(int i = 0; i < ind1.size(); ++i){
            if(Math.random() <= uniformRate){
                newSuccessor.setGene(i, ind1.getGene(i));
            }else{
                newSuccessor.setGene(i, ind2.getGene(i));
            }
        }
        return newSuccessor;
    }

    // mutate the individual
    private static void mutate(Individual indiv){
        for(int i = 0; i < indiv.size(); ++i){
            if(Math.random() <= mutationRate){
                byte gene = (byte) Math.round(Math.random());
                indiv.setGene(i,gene);
            }
        }
    }

    // selection
    private static Individual tournamentSelection(Population pop){
        Population tournament = new Population(tournamentSize, false);
        for(int i = 0; i < tournamentSize; ++i){
            int randomInd = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomInd));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

}
