/*The Parameters class achieves the connection between the main() and the State class.
The user input parameters are passed inside the Parameters class using the set methods.
Then in turn, they get called in the State class using the get methods.
*/
public class Parameters {
    public static int N, M, K;
    //set parameters
    public static void setN(int N){
        Parameters.N = N;
    }
    public static void setM(int M){
        Parameters.M = M;
    }
    public static void setK(int K){
        Parameters.K = K;
    }
    //get parameters
    static int getN(){
        return N;
    }
    static int getM(){
        return M;
    }
    static int getK(){
        return K;
    }
}