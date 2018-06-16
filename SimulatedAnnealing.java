
/**
 *
 * @author sameepshah
 */
import java.util.*;

public class SimulatedAnnealing {

    private final static int N = 8;
    int nodesGenerated;
    private Queen[] startState;
    private Node start;
    int a = 0;
    int b = 0;

    public SimulatedAnnealing(Queen[] s) {
        nodesGenerated = 0;
        start = new Node();
        startState = new Queen[N];

        for (int i = 0; i < N; i++) {
            startState[i] = new Queen(s[i].getRow(), s[i].getColumn());
        }
        start.setState(startState);
        start.Heuristic();

    }

    // initialized random configurations for the queens
    public void startState() {
        start = new Node();
        startState = new Queen[N];
        Random r = new Random();

        for (int i = 0; i < N; i++) {
            startState[i] = new Queen(r.nextInt(N), i);
        }
        start.setState(startState);
        start.Heuristic();
    }

    // simulated annealing algorithm which takes argument as initial temperature
    //and step and returns node
    //checks whether the current node's heruistic is not 0 and temperature
    //is greater than 1 than it makes the random neighbour generated from current node
    //as then next node, if heruistuic of this next node=0  it will return that node
    // calculating the delta and checks if the current node has higher heruistic
    //than it will set current node as next node else it will calculate the probablity
    //of sticking with the same node or choosing the next random node.
    public Node simulatedAnneal(double initialTemp, double step) {
        Node currentNode = start;
        double temperature = initialTemp;
        double val = step;
        double probability;
        int delta;
        double determine;

        Node nextNode = new Node();

        while (currentNode.getHeuristic() != 0 && temperature > 0) {

            nextNode = currentNode.getRandomNeighbour(currentNode);

            if (nextNode.getHeuristic() == 0) {

                return nextNode;
            }

            delta = currentNode.getHeuristic() - nextNode.getHeuristic();

            if (delta > 0) {

                currentNode = nextNode;
                a++;

            } else {

                probability = Math.exp(delta / temperature);

                determine = Math.random();

                if (determine <= probability) {
                    currentNode = nextNode;
                    nodesGenerated++;
                }

            }

            temperature = temperature - val;

        }

        return currentNode;

    }

    public int getNodesGenerated() {
        return a;
    }

    public Node getStartNode() {
        return start;
    }
}
