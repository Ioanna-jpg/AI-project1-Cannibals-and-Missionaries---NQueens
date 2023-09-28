import java.util.ArrayList;

import static java.lang.Math.min;

public class State {

    //parameters
	Parameters p;
    public int N = p.getN();
    public int M = p.getM();
    public int K = p.getK();

    public int boat; // the side of the boat. Takes the value 0 for left side and 1 for right side
    public int m; //number of missionaries on the left side
    public int c; //number of cannibals on the left side

    // Parent in the path
    public State parent = null;
    //weight of a child
    public int weight;
    // Evaluation functions
    public int f;
    public int g;
    // Hardcoded heuristic
    public int h;
    //state constructor
    public State(int boat, int m, int c, Parameters p){
        this.boat = boat;
        this.m = m;
        this.c = c;
        this.p=p;
    }

    //the inside method returns true if a state is inside an array and false if it's not
    public boolean inside(ArrayList<State> states){
        boolean temp = false;
        for (State n: states){
            if (this.equals(n)) temp = true;
        }
        return temp;
    }
    //the equals method returns true if a state is equal to another state
    // which happens when they have the same boat, m and c variables
    public boolean equals(State state){
        if (this.boat == state.boat && this.m == state.m && this.c == state.c) return true;
        return false;
    }

    //get methods for boat, m and c
    public int get_boat() {
        return this.boat;
    }
    public int get_m() {
        return this.m;
    }
    public int get_c() {
        return this.c;
    }

    //class child inherits the properties of state, but has extra properties too
    //Those are the weight and the parent state properties
    static class Child extends State{
        Child(int w, State state, int boat, int m, int c ,Parameters p) {
            super(boat, m, c,p);
            this.weight = w;
            this.parent = state;
        }
    }

    /* this function displays the requirements of the Cannibals and Missionaries problem.
  If those requirements are not fulfilled, it returns false, otherwise it returns true*/
    public boolean evaluate(int x, int y) {
        if (this.m < 0 || this.m > N || this.c < 0 || this.c > N) return false;// m and c must be in range [0, N]
        if (this.m > 0 && this.c > this.m) return false; // c must be less than m, if there are missionaries on the left side
        if (x < 0 && x > M || y < 0 && y > M) return false; //people on the boat must be in range [0, M]
        if (x + y > M) return false;
        if (this.weight < 0 || this.weight > K) return false; //weight must be in range [0, K]
        if (this.m + this.c == 2*N && this.boat == 1) return false; //if all m and c are on the left side,
        if (this.m + this.c == 0 && this.boat == 0) return false;// then the boat cannot be on the right side, and the opposite
        return true;
    }
    //checks to do for the left side
    private boolean check_left(int x, int y, Child s) {
        if (this.boat == 1 || s.m - x > N || s.c - y > N || s.m - x < 0 || s.c - y < 0 || x + y > M) {
            return false;
        }
        if (s.m - x > 0 && s.c - y > s.m - x) {
            return false;
        }
        if (N - s.m + x > 0 && N - s.m + x < N - s.c + y) {
            return false;
        }
        return true;
    }
    //checks to do for the right side
    private boolean check_right(int x, int y, Child s) {
        if (s.boat == 0 || s.m + x > N || s.c + y > N || s.m + x < 0 || s.c + y < 0 || x + y > M) {
            return false;
        }
        if (s.m + x > 0 && s.c + y > s.m + x) {
            return false;
        }
        if (N - s.m - x > 0 && N - s.m - x < N - s.c - y) {
            return false;
        }
        return true;
    }

    //function that moves missionaries and/or cannibals across the river
    private State move(int x, int y) {
        int weight = this.g + 1; //the number of crossings of a child are equal
        // to the number of crossings of the parent plus one
        Child child = new Child(weight, this, this.boat, this.m, this.c,this.p); //create child
        if (check_left(x, y, child)) { //if child is on the left side
            child.m = child.m - x; //decrease number of m's by the number of missionaries who leave(x)
            child.c = child.c - y; //decrease number of c's by the number of cannibals who leave(y)
            child.boat = 1; //switch boat side from left to right
        } else if (check_right(x, y, child)) { //if child is on the right side
            child.m = child.m + x; //increase number of m's by the number of missionaries who come(x)
            child.c = child.c + y; //increase number of c's by the number of cannibals who come(y)
            child.boat = 0; //switch boat side from right to left
        } else {
            child.weight = -1; //if none of the checks are correct, error
        }
        return child;
    }

    public ArrayList<Child> getChildren() {
        ArrayList<Child> children = new ArrayList<>();
        int j;
        for (int i = 0; i <= N; i++) {
            j = 0;
            for (; j <= N; j++) {
                if (i + j != 0) {
                    Child child = (Child) this.move(i, j);
                    if (child.weight != -1 && child.evaluate(i, j)) {//if child state fulfills all requirements,
                        children.add(child);                          //add child to children array
                    }
                }
            }
        }
        return children;
    }

    // compare the two heuristic functions to find the most efficient
    public int calculateHeuristic() {
        this.h = min(this.h1(), this.h2());
        return this.h;
    }
    /*Heuristic function that takes off the limitation that does not allow the number of cannibals
   in the one side to be greater than the number of missionaries in the same side*/
    private int h1() {
        int n = this.m + this.c;//sum of all humans on the left side of the river
        if (this.boat == 1 && n > 0) {
            return 2;
        } else if (this.boat == 0) {
            if (n == 1) {
                return 1;
            } else {
                return 2 * (n - 3);
            }
        } else {
            return 0;
        }
    }
    /*Heuristic function that takes off the limitation that does not allow the number of people
    in the boat to be greater than 2 */
    private int h2() {
        int n = this.m + this.c; //sum of all humans on one side of the river
        if (n > 0) {
            if (this.boat == 1) {
                return 2;
            } else if (this.boat == 0) {
                return 1;
            }
        }
        return 0;
    }

}