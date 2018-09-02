package model;

public class Selection {
    private byte typeSelection;
    private byte tournamentSize;
    private byte rankFunction;

    public static final byte ROULETTE = 0;
    public static final byte TOURNAMENT = 1;
    public static final byte RANKED = 2;

    public static final byte LINEAR = 3;
    public static final byte HYPERBOLIC = 4;

    public Selection (byte typeSelection){
        this.typeSelection = typeSelection;

        tournamentSize = 3;
        rankFunction = LINEAR;
    }

    /*private Individual rouletteSelection(Population pop){
        return null;
    }

    private Individual tournamentSelection(Population pop){
        // create population of parents
        // select parent

        Population tournament = new Population(tournamentSize, false);
        for(int i = 0; i < tournamentSize; ++i){
            int randomInd = (int) (Math.random() * pop.size());
            tournament.saveIndividual(i, pop.getIndividual(randomInd));
        }
        Individual fittest = tournament.getFittest();
        return fittest;
    }

    private Individual rankedSelection(Population pop){
        return null;
    }*/

    private Population rouletteSelectPopulation(Population pop) {
        Population parentPop = new Population(pop.size(),false);

        // TODO !!!!!!!!!!!

        return null;
    }

    private Population tournamentSelectPopulation(Population pop) {
        Population parentPop = new Population(pop.size(),false);

        for(int j = 0; j < pop.size(); ++j){
            Population tournament = new Population(tournamentSize, false);
            for(int i = 0; i < tournamentSize; ++i){
                int randomInd = (int) (Math.random() * pop.size());
                tournament.saveIndividual(i, pop.getIndividual(randomInd));
            }

            Individual fittest = tournament.getFittest();

            parentPop.saveIndividual(j, fittest);
        }

        return parentPop;
    }

    private Population rankedSelectPopulation(Population pop) {
        return null;
    }

    //---------------------------------------------------------------------
    // public methods
    /*public Individual getParent(Population population){
        Individual parent;
        switch (typeSelection){
            case ROULETTE: parent = rouletteSelection(population);break;
            case TOURNAMENT: parent = tournamentSelection(population);break;
            case RANKED: parent = rankedSelection(population);break;
            default: parent = null;
        }

        return parent;
    }*/

    public Population getParentPopulation(Population pop){
        Population parentPopulation;
        switch (typeSelection){
            case ROULETTE: parentPopulation = rouletteSelectPopulation(pop);break;
            case TOURNAMENT: parentPopulation = tournamentSelectPopulation(pop);break;
            case RANKED: parentPopulation = rankedSelectPopulation(pop);break;
            default: parentPopulation = null;
        }

        return parentPopulation;
    }

    // set tournament size
    public void setTournamentSize(byte size){
        if(size > 0)
            this.tournamentSize = size;
    }

    // set ranked function
    public void setRankFunction(byte functionType){
        switch (functionType){
            case LINEAR: this.rankFunction = LINEAR; break;
            case HYPERBOLIC: this.rankFunction = HYPERBOLIC;break;
        }
    }


    public static void insertIntoSort(Individual[] arr) {
        Individual temp;
        int j;
        for(int i = 0; i < arr.length - 1; i++){
            if (arr[i].getFitness() > arr[i + 1].getFitness()) {
                temp = arr[i + 1];
                arr[i + 1] = arr[i];
                j = i;
                while (j > 0 && temp.getFitness() < arr[j - 1].getFitness()) {
                    arr[j] = arr[j - 1];
                    j--;
                }
                arr[j] = temp;
            }
        }
    }

    // check if ranked selection
    public boolean isRanked(){
        return this.typeSelection == RANKED;
    }
}
