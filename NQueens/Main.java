import java.util.Scanner;

public class Main
{

    public static void main(String[] args)
    {
    	 
    	
    	Scanner scan = new Scanner(System.in);
        System.out.print("Enter the number of N: ");
      
    	
        // diabazoyme to n pou dinei o xrhstis
        int num = scan.nextInt();
        scan.close();
    	
            //Ksekiname ena taimer gia na metrisoume to xrono
        	double startTime = System.currentTimeMillis();
        	//ypologizoume to minimumfitness me (n-1)+(n-2)+(n-3)...+(n-n)
        	int minfit= 0;
            for (int i=1; i<num; i++) {
         	   minfit=minfit +i;
            }
          //ypologizoume to population me basi to n! kai posa epipeda tha exei to dentro
            int POPULATION = 2*(num-1);
            //dimiurgoume ena antikeimeno GeneticAlgorithm kai toy pername san orisma to N
           GeneticAlgorithm algorithm = new GeneticAlgorithm(num, POPULATION);
           
           //kaloume thn geneticSearch me ta katallila orismata
           algorithm.geneticSearch(num, 0.5f, minfit,POPULATION);
           //ektyponoume me thn katallili methodo
           algorithm.printSolution();
           double endTime = System.currentTimeMillis();
           System.out.println("That took " + (endTime - startTime)/1000 + " seconds");

    }
    
    
}
