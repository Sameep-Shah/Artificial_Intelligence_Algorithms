/**
 * Main class for running the AStar Search
 */

import java.io.IOException;
import java.util.*;

public class AStarAlgo {
    private int size;
    private HashMap<Integer, Double> weight; 
    private HashMap<Integer, Integer> previousNode; 
    private PriorityQueue<Integer> pq; 
    private Graph graph;
    // Initializing Constructor
    public AStarAlgo(Graph graph) {
        this.graph = graph;
        size = graph.size();
    }\ // Calculating the shortest path using heruistic function
    public ArrayList<Integer> shortestPathAStarHeuristics(int vertexA, int vertexB) {
        previousNode = new HashMap<Integer, Integer>();
        weight = new HashMap<Integer, Double>();
        pq = new PriorityQueue<Integer>(size, pQC);
        // Setting all distances to infinity
        for (int vertex : graph.vertices())
            weight.put(vertex, Double.POSITIVE_INFINITY);

        previousNode.put(vertexA, -1); 
        weight.put(vertexA, 0.0);
        pq.add(vertexA); // adding the 1st vertex

        while (pq.size() > 0) {
            int currentNode = pq.poll();
            ArrayList<Edge> neighbors = graph.edgeTo(currentNode);

            if (neighbors == null) continue;

            for (Edge neighbor : neighbors) {
                int nextVertex = neighbor.to();

                double newDistance = weight.get(currentNode) + neighbor.weight();
                if (weight.get(nextVertex) == Double.POSITIVE_INFINITY) {
                    previousNode.put(nextVertex, currentNode);
                    weight.put(nextVertex, newDistance);
                    pq.add(nextVertex);
                } else {
                    if (weight.get(nextVertex) > newDistance) {
                        previousNode.put(nextVertex, currentNode);
                        weight.put(nextVertex, newDistance);
                    }
                }
            }
        }
        // Using data structure to store the path

        ArrayList<Integer> newPath = new ArrayList<Integer>();
        Stack<Integer> tempPath = new Stack<Integer>();
        tempPath.push(vertexB);

        int v = vertexB;
        while (previousNode.containsKey(v) && previousNode.get(v) >= 0 && v > 0) {
            v = previousNode.get(v);
            tempPath.push(v);
        }
        while (tempPath.size() > 0)  // putting node in reverse order
            newPath.add(tempPath.pop());

        return newPath;
    }
    
    public Comparator<Integer> pQC = new Comparator<Integer>() {

        public int compare(Integer a, Integer b) {
            if (weight.get(a) > weight.get(b)) {
                return 1;
            } else if (weight.get(a) < weight.get(b)) {
                return -1;
            }
            return 0;
        }
    };

    public static void main(String args[]) {
        Graph graph;

        try {
            graph = new Graph(args[0]);
            
            AStarAlgo finder = new AStarAlgo(graph);
            System.out.print(finder.shortestPathAStarHeuristics(0, graph.getNumNodes()-1).get(0));
            for(int i = 1; i < finder.shortestPathAStarHeuristics(0, graph.getNumNodes()-1).size(); i++)
            {
            	System.out.print(">" + finder.shortestPathAStarHeuristics(0, graph.getNumNodes()-1).get(i));
            }
        } catch (IOException e) {}
    }
}
