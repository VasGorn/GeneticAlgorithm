package model;

public class Crossover {
    private byte typeCrossover;
    private final double uniformRate = 0.5;

    public static final byte SINGLE_POINT = 0;
    public static final byte TWO_POINT = 1;
    public static final byte UNIFORM = 2;

    public Crossover (byte typeCrossover){
        this.typeCrossover = typeCrossover;
    }

    private Individual singlePointCrossover(Individual parentA, Individual parentB){
        return null;
    }

    private Individual twoPointCrossover(Individual parentA, Individual parentB){
        return null;
    }

    private Individual uniformCrossover(Individual parentA, Individual parentB){
        Individual offspring = new Individual();
        for(int i = 0; i < parentA.size(); ++i){
            if(Math.random() <= uniformRate){
                offspring.setGene(i, parentA.getGene(i));
            }else{
                offspring.setGene(i, parentB.getGene(i));
            }
        }
        return offspring;
    }

    //---------------------------------------------------------------------
    // public methods

    public Individual crossover(Individual parentA, Individual parentB){
        Individual offspring;
        switch (typeCrossover){
            case SINGLE_POINT: offspring = singlePointCrossover(parentA, parentB); break;
            case TWO_POINT: offspring = twoPointCrossover(parentA, parentB); break;
            case UNIFORM: offspring = uniformCrossover(parentA, parentB); break;
            default: offspring = null;
        }
        return offspring;
    }
}
