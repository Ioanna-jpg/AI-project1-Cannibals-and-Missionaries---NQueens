import java.util.Random;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class GeneticAlgorithm
{
	
    private int treeDepth; // bathos toy pinaka dentrou
    private Chromosome[] population;
    private Chromosome bestSol;
    private Chromosome propableSecondSol;
    // dimiourgoume ena disdiastato dentro 
    private Chromosome[][] doubleTree;
    // poses genarations thelei mexri na ftasei se teliki katastasi
    private int goalGen=0;

    //constractor tou genetic algorithm me tis parametrous pou theloume apo tin main
    GeneticAlgorithm(int num, int POPULATION)
    {
    	
        this.population = null;
        // ypologismos tou bathous me ton typo depth = log2(elements) = log(elements) / log(2)
        
        // ypologismos me ton tipo  depth = logn + 1
        this.treeDepth = (int)(Math.log(POPULATION) / Math.log(2)) + 1;
        this.doubleTree = new Chromosome[this.treeDepth][];
        //kataskeui tou pinaka populatio me ta chromosome kai arxikopoihsh
        population = new Chromosome[POPULATION];
        for (int i = 0; i < POPULATION; i++) {
            population[i] = new Chromosome(num);
        }

    }
    // pername ta orismata pou xreiazetai gia na ginei h anazhthsh
    public void geneticSearch(int num , double mutationProbability, int minFitness, int POPULATION) {
    	 
        do {
            // kaloume thn methodo gia na epilejoyme chrromosomes
            this.tourneySelect();
            // kaloume tin populate gia na kataskeuasoyme thn epomenh genia apo chromosomes
            this.populate(num ,mutationProbability, POPULATION);
            this.goalGen += 1;// auksisi tou generations
        } while (this.bestSol.getFitness()<minFitness);
    }
   
    // h methodos ton tournament tree h winner tree gia thn epilogh ton kalyteron chromosmes
    private void tourneySelect() {
        this.createTournamentTree();
        this.bestSol = this.doubleTree[this.treeDepth - 1][0];// bazoyme to kalitero chromosome 
        //pou einai kai sosta topothetimeno otan egine h kataskeyh os thn kaliteri lisi
        this.setPropableSecondBest();// kaloyme gia na broyme to deytero kalitero
    }
    
    //xrisi domis tournament tree gia kataskeui tou dentrou
    private void createTournamentTree() {
        this.doubleTree[0] = this.population;
        Chromosome[] currentRow = this.population;

        for (int i = 1; i < this.treeDepth; i++) {
            int newRowSize = currentRow.length / 2;
            Chromosome[] nextRow = new Chromosome[newRowSize];

            //epilogi ton katalilon kombon gia na katskeyastei to dentro
            for (int j = 0; j < newRowSize; j++) {
                nextRow[j] = this.fitnessFunc(currentRow[j * 2], currentRow[j * 2 + 1]);
            }
            // epanaepilogi gia to epomeno row
            currentRow = nextRow;
            this.doubleTree[i] = nextRow;
        }
    }
    // anazitisi deyteroy kalyteroy chromosome
    private void setPropableSecondBest() {
        
        // elekxos gia idia chromosomes kai anazitisi opvw prin
        if (this.doubleTree[this.treeDepth - 2][0] == this.bestSol) {
            this.propableSecondSol = this.doubleTree[this.treeDepth - 2][1];
         
        }
        else {
            this.propableSecondSol = this.doubleTree[this.treeDepth - 2][0];
           
        }
        
    }
    // ginetai h paragogh kainoyrion chromosomes me tin poulate me basi dyo gvnion 
    // tou best kai seconbest 
    private void populate(int num , double mutationProbability, int POPULATION) {
        int[] parentOne = this.bestSol.getGenes();
        int[] parentTwo = this.propableSecondSol.getGenes();
        
        boolean slice = true;
        Random r = new Random();
        for (int i = 0; i < POPULATION; i++) {
            // dimioyrgia neou chromosome kai epilogi tyxiaou shmeiou kopis sto board
        	// kai antigrafh ton proton kai antistoixa ton teleytaivn uesevn sthn proti epanalipsi
            int[] newChromosome = new int[num];
            int crosspoint = r.nextInt(num);
            if (slice = true){
                System.arraycopy(parentOne, 0, newChromosome, 0, crosspoint);
                System.arraycopy(parentTwo, crosspoint, newChromosome, crosspoint, num - crosspoint);
            }
            else {
                System.arraycopy(parentTwo, 0, newChromosome, 0, crosspoint);
                System.arraycopy(parentOne, crosspoint, newChromosome, crosspoint, num - crosspoint);
            }

            // tyxaio mutate meta apo anaparagogi 
            if (r.nextInt(1000) < (mutationProbability * 1000)) {
                newChromosome[r.nextInt(num)] = r.nextInt(num);
            }
            
            this.population[i] = new Chromosome(newChromosome, num);
            slice = !slice;
        }
    }
   // xrisi fitness opos tin eidame sto ergastirio afou prota ginetai ypologismos
    public Chromosome fitnessFunc(Chromosome boardOne, Chromosome boardTwo) {
    	boardOne.calculateFitness();
    	boardTwo.calculateFitness();
        if (boardOne.getFitness() > boardTwo.getFitness()) {
            return boardOne;
        }
        else {
            return boardTwo;
        }
    }
    // print 
    public void printSolution() {
        if (this.bestSol != null) {
            this.bestSol.print();
            System.out.println("Generations: " + this.goalGen);
        }
        else {
            System.out.println("No solution found.");
        }
    }
    
}
