import java.util.Scanner;



public class Main {

    static Scanner scanner = new Scanner(System.in);  // Create a Scanner object

    public static void main(String[] args) {
        //Parameters p = new Parameters();
        //Searcher s = new Searcher();
        // Read user input
        System.out.println("Enter the number of missionaries and the number of cannibals for the initial state:");
        int N = scanner.nextInt();
        System.out.println("Enter the boat capacity:");
        int M = scanner.nextInt();
        System.out.println("Enter the maximum number of boat crossings:");
        int K = scanner.nextInt();
        System.out.println();
        //create object of the class Parameters
        Parameters p = new Parameters();
        //set parameters in order to use them on the State class
        p.setN(N);
        p.setM(M);
        p.setK(K);
        //create initial state A:
        State A = new State(0,N,N,p);
        //create target state:
        State target = new State(1,0, 0,p);
        long start = System.currentTimeMillis(); //start counter
        //apply the A_star algorithm and return the path state, which is the final state
        // after the algorithm has been applied
        State path = Searcher.A_star(A, target);
        long end = System.currentTimeMillis(); //stop counter
        // total time of searching in seconds.
        System.out.println("Search time:" + (double) (end - start) / 1000 + " sec.");
        if (path == null){
            System.out.println("Cannot find a path for these specific states");
        }
        Searcher.printPath(path);
    }
}
