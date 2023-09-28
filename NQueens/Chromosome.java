import java.util.Random;

public class Chromosome 
{
	
    //Ston pinaka genes analoga me ton arithmo tou keliou 
	//einai h thesi tis basilisas ston x kai to periexomeno h thesi ston y
    private int[] genes;
    

    //Integer that holds the fitness score of the chromosome
    private int fitness;

    //Constructs pou kataskeuazei ena tixaio board
    Chromosome(int num)
    {
        this.genes = new int[num];
        Random r = new Random();
        for(int i = 0; i < this.genes.length; i++)
        {
            this.genes[i] = r.nextInt(num);
        }
        this.calculateFitness();
    }

    //Constructs gia na katskeuazei ena idi iparxon board
    Chromosome(int[] genes, int num)
    {
        this.genes = new int[num];
        for(int i = 0; i < this.genes.length; i++)
        {
            this.genes[i] = genes[i];
        }
        this.calculateFitness();
    }
   
    //Ypologismos tou fitness gia ena chromosome diladi enos board
    //me tin formula tou ergastiriou
    void calculateFitness()
    {
        int nonThreats = 0;
        for(int i = 0; i < this.genes.length; i++)
        {
            for(int j = i+1; j < this.genes.length; j++)
            {
                if((this.genes[i] != this.genes[j]) &&
                        (Math.abs(i - j) != Math.abs(this.genes[i] - this.genes[j])))
                {
                    nonThreats++;
                }
            }
        }
        this.fitness = nonThreats;
    }

   

    public int[] getGenes() {
        return this.genes;
    }

    public void setGenes(int[] genes) {
        this.genes = genes;
    }

    public int getFitness() {
        return this.fitness;
    }

    public void setFitness(int fitness) {
        this.fitness = fitness;
    }
    
//Emfanizo to board me tis basilises stho teliko chromosome opou den apeilite kamia queen 
    void print()
    {
        System.out.print("Chromosome : |");
        for(int i = 0; i < this.genes.length; i++)
        {
            System.out.print(this.genes[i]);
            System.out.print("|");
        }
        System.out.print(", Fitness : ");
        System.out.println(this.fitness);

        System.out.println("------------------------------------");
        for(int i = 0; i < this.genes.length; i++)
        {
            for(int j=0; j < this.genes.length; j++)
            {
                if(this.genes[j] == i)
                {
                    System.out.print("|Q");
                }
                else
                {
                    System.out.print("| ");
                }
            }
            System.out.println("|");
        }
        System.out.println("------------------------------------");
    }

    //compareTo function -> sorting can be done according to fitness scores
    
   
   
}
