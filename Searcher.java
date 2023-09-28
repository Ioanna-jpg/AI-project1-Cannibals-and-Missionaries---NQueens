import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//The A_star algorithm method searches for the best path between the initial state and the target state
public class Searcher {
    public static State A_star(State start, State target){
        //The frontier contains states that we've encountered, but haven't analyzed yet.
        ArrayList<State> frontier = new ArrayList<>();
        //The closedSet contains states whose all children have been added to the frontier.
        //Closed states have their shortest path calculated and their adjacent states "scheduled"
        // for analysis by being added to the frontier. Closed states can become open again if we
        // encounter them through a different path and that path that is more optimal than the one we
        // previously used to reach them.
        ArrayList<State> closedSet = new ArrayList<>();

        start.g = 0; //Initially, the number of crossings is 0
        start.f = start.g + start.calculateHeuristic();
        frontier.add(start); // Initially, the frontier only contains the starting state.

        //We go through open states, get their children, calculate their f and g and then close
        //them again.
        while(!frontier.isEmpty()){
            State n = frontier.get(0);
            if (n.equals(target)){
                return n;
            }
            ArrayList<State.Child> children = n.getChildren();
            for(State.Child child : children){
                int totalWeight = n.g + 1; //the number of crossings of a child are equal
                // to the number of crossings of the parent plus one
                if(!child.inside(frontier) && !child.inside(closedSet)){
                    child.parent = n;
                    child.g = totalWeight;
                    child.f = child.g + child.calculateHeuristic();
                    frontier.add(child);
                } else {
                    if(totalWeight < child.g){
                        child.parent = n;
                        child.g = totalWeight;
                        child.f = child.g + child.calculateHeuristic();

                        if(child.inside(closedSet)){
                            closedSet.remove(child);
                            frontier.add(child);
                        }
                    }
                }
            }
            frontier.remove(n);
            closedSet.add(n);
            sort(frontier);
        }
        return null;
    }
    //sort frontier by the value f of each state
    public static ArrayList<State> sort(ArrayList<State> frontier) {
        int n = frontier.size();
        for (int i = 0; i < n - 1; i++) {
            int j;
            State x;
            for (j = 0; j < n - i - 1; j++) {
                x = frontier.get(j);
                State y = frontier.get(j + 1);
                if (x.f < y.f) {
                    // swap frontier[j+1] and frontier[j]
                    frontier.set(j, y);
                    frontier.set(j + 1, x);
                }
            }
        }
        return frontier;
    }

    public static void printPath(State target){
        int N = Parameters.getN();
        State n = target;
        if(n==null)
            return;
        //initialize path arraylist
        List<State> path = new ArrayList<>();
        path.add(target);
        while(n.parent != null){
            n = n.parent;
            path.add(n);
        }
        Collections.reverse(path);
        //print formatted table
        System.out.printf("%10s %20s %20s %20s %20s", "Boat side", " Missionaries left", " Cannibals left", " Missionaries right", " Cannibals right");
        System.out.println();
        System.out.println("-----------------------------------------------------------------------------------------------------");
        for(State state : path){
            System.out.format("%10s %20s %20s %20s %20s",
                    state.get_boat(), state.get_m(), state.get_c(), N - state.get_m(), N - state.get_c());
            System.out.println();
        }
    }
}