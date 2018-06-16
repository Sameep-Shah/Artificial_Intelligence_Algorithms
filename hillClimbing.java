
/**
 *
 * @author sameepshah
 */
import java.util.*;

public class hillClimbing {

    public final static int N = 8;
    public Queen[] startState;
    public Node start;
    double nodesGenerated;
    double nodesGenerated1;
    int count = 0;

    public hillClimbing() {
        startState = new Queen[N];
        startState();
        nodesGenerated = 0;
        nodesGenerated1 = 0;
    }

    public hillClimbing(Queen[] s) {
        start = new Node();
        startState = new Queen[N];
        for (int i = 0; i < s.length; i++) {
            startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
        }

        start.setState(startState);
        start.Heuristic();

    }

    // logic for hill climbing
    // chechks whether the immediate neighbour has low value then current
    //if yes than it makes the neighbour as current
    //checks the heruistic of the current node if it is 0 it will increment counter
    // which will then help in counting average move for success and stuck
    public Node hilClimbing() {
        Node current = start;

        int b = 0;
        int a = 0;

        while (true) {
            ArrayList<Node> successors = current.generateNeighbours(current);
            nodesGenerated = nodesGenerated + a;
            nodesGenerated1 = nodesGenerated1 + b;

            Node nextNode = null;

            for (int i = 0; i < successors.size(); i++) {

                if (successors.get(i).compareTo(current) < 0) {
                    nextNode = successors.get(i);
                    a = successors.indexOf(i);
                    count++;
                    if (nextNode.getHeuristic() == 0) {
                        a = i;

                    }
                    if (nextNode.getHeuristic() != 0) {
                        b = i;
                    }

                }

            }

            if (nextNode == null) {

                return current;
            }
            current = nextNode;
        }

    }

    public void startState() {
        Random r = new Random();
        for (int i = 0; i < N; i++) {
            startState[i] = new Queen(r.nextInt(N), i);
        }
        start.setState(startState);
        start.Heuristic();
    }

    // getter for the moves
    public double getNodesGenerated() {
        return nodesGenerated;
    }

    public double getNodesGenerated1() {
        return nodesGenerated1;
    }

    public Node getStartNode() {
        return start;
    }
}
