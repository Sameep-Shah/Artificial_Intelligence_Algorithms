
/**
 *
 * @author sameepshah
 */
import java.util.*;

public class Node implements Comparable<Node> {

    public static final int N = 8; 
    public Queen[] state; 
    public final ArrayList<Node> neighbours;
    public int h; 

    // public constructor
    public Node() {
        state = new Queen[N]; 
        neighbours = new ArrayList<>(); 
    }

    // creating copy of a node 
    public Node(Node n) {
        state = new Queen[N];
        neighbours = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            state[i] = new Queen(n.state[i].getRow(), n.state[i].getColumn());
        }
        h = 0;
    }

    //generating all the neighbours which are possible
    public ArrayList<Node> generateNeighbours(Node startState) {
        int count = 0;

        for (int i = 0; i < N; i++) {
            for (int j = 1; j < N; j++) {
                neighbours.add(count, new Node(startState));
                neighbours.get(count).state[i].moveDown(j);

                neighbours.get(count).Heuristic();

                count++;
            }
        }

        return neighbours;
    }

    // generated random neighbour which is needed for simulated annealing
    public Node getRandomNeighbour(Node startState) {
        Random r = new Random();

        int col = r.nextInt(N);
        int d = r.nextInt(N - 1) + 1;

        Node neighbour = new Node(startState);
        neighbour.state[col].moveDown(d);
        neighbour.Heuristic();

        return neighbour;
    }

    //computing heruistic which is calculating no of queens
    // that attack each other
    public int Heuristic() {

        for (int i = 0; i < N - 1; i++) {
            for (int j = i + 1; j < N; j++) {
                if (state[i].canAttack(state[j])) {
                    h++;
                }
            }
        }

        return h;
    }

    // getter for heruistic value
    public int getHeuristic() {
        return h;
    }

    // overriding comaprable method compare to
    //which comapres the heruistic value of nodes
    //and select on the basis of it
    @Override
    public int compareTo(Node n) {
        if (this.h < n.getHeuristic()) {
            return -1;
        } else if (this.h > n.getHeuristic()) {
            return 1;
        } else {
            return 0;
        }
    }

    public void setState(Queen[] s) {
        for (int i = 0; i < N; i++) {
            state[i] = new Queen(s[i].getRow(), s[i].getColumn());
        }
    }

    public Queen[] getState() {
        return state;
    }
}
