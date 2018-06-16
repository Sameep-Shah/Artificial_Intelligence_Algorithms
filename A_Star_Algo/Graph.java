
import java.util.HashMap;
import java.util.ArrayList;
import java.util.HashSet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.FileReader;
// Implementation of graph using HashMap
public class Graph {
    private HashMap<Integer, ArrayList<Edge>> adjList = new HashMap(); 
    private int numNodes;
    public Graph() {
    }
    // reading data from file and storing it in graph
    public Graph(String fName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fName));
        String ln = null;
        ln = reader.readLine();
        numNodes = Integer.parseInt(ln);
        while ((ln = reader.readLine()) != null) {
            String[] tokens = ln.split("\\s");

            if (tokens.length == 3) {
                int from = Integer.parseInt(tokens[0]);
                int to = Integer.parseInt(tokens[1]);
                double weight = Double.parseDouble(tokens[2]);

                addEdge(new Edge(from, to, weight));
            }
        }
    }
       // returns a list of edges
    public ArrayList<Edge> edgeTo(int vertex) {
        return adjList.get(vertex);
    }
    
    public ArrayList<Edge> edges() {
        ArrayList list = new ArrayList<Edge>();

        for (int from : adjList.keySet()) {
            ArrayList<Edge> cEdges = adjList.get(from);
            for (Edge e : cEdges) {
                list.add(e);
            }
        }
        return list;
    }
    public Iterable<Integer> vertices() {
        HashSet set = new HashSet();
        for (Edge edge : edges()) {
            set.add(edge.from());
            set.add(edge.to());
        }

        return set;
    }
    //returns size of the list
    public int size() {
        return adjList.size();
    }
    public String toString() {
        String output = "";
        for (int from : adjList.keySet()) {
            ArrayList<Edge> queuedEdges = adjList.get(from);
            output += from + " -> ";

            if (queuedEdges.size() == 0)
                output += "-,";

            for (Edge edge : queuedEdges)
                output += edge.to() + " @ " + edge.weight() + ", ";

            output += "\n";
        }

        return output;
    }
    public void addEdge(Edge nEdge) {
        if (!adjList.containsKey(nEdge.from()))
            adjList.put(nEdge.from(), new ArrayList<Edge>());

        ArrayList<Edge> currentEdges = adjList.get(nEdge.from());
        boolean edgeExists = false;
        for (int i = 0; i < currentEdges.size(); i++) {
            if (currentEdges.get(i).to() == nEdge.to()) {
                currentEdges.set(i, nEdge);
                edgeExists = true;
                break;
            }
        }

        if (!edgeExists)
            currentEdges.add(nEdge);

        adjList.put(nEdge.from(), currentEdges);
    }

	public int getNumNodes() {
		return numNodes;
	}

	public void setNumNodes(int numNodes) {
		this.numNodes = numNodes;
	}
}
