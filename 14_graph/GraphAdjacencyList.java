
import java.util.Set;
import java.util.Map;
import java.util.HashSet;
import java.util.HashMap;

class Edge {
  int from, to, weight, hash;
  public Edge(int from, int to, int weight) {
    this.weight = weight;
    this.from = from;
    this.to = to;
    this.hash = java.util.Objects.hash(from, to, weight);
  }
  @Override public int hashCode() {
    return hash;
  }
  @Override public boolean equals( Object obj ) {
    if (obj == null) return false;
    Edge other = (Edge) obj;
    return (from==other.from) && (to==other.to) && (weight==other.weight);
  }
  @Override public String toString() {
    return "("+to + ", " + from + ", "+ hash +")";
  }
}

public class GraphAdjacencyList {

  private int edgeCount = 0;
  // IMap <Integer, Set<Edge>> adjacencyList;
  // IMap <Integer, HSet<Edge>> adjacencyList;
  Map <Integer, HSet<Edge>> adjacencyList;

  public GraphAdjacencyList () {
    // adjacencyList = new Mapping<>();
    adjacencyList = new HashMap<>();
  }

  // Intializes adjacency matrix for nodes indexed
  // from [0, numNodes). Additional nodes can also be added later
  public GraphAdjacencyList(int numNodes) {
    if (numNodes <= 0) throw new IllegalArgumentException();
    // adjacencyList = new Mapping<>( numNodes );
    adjacencyList = new HashMap<>( numNodes );
    for (int i = 0; i < numNodes; i++)
      adjacencyList.put(i, new HSet<Edge>());
      // adjacencyList.add(i, new HashSet<Edge>());
  }

  // Returns the number of nodes in this graph
  public int numNodes() {
    return adjacencyList.size();
  }

  // Returns the number of edges in this graph
  public int numEdges() {
    return edgeCount;
  }

  // Get all the edges exiting node at nodeID
  // public Set <Edge> getEdges(int nodeID) {
  public HSet <Edge> getEdges(int nodeID) {
    return adjacencyList.get(nodeID);
  }

  // Add an edge to this graph, O(1)
  public void addDirectedEdge(int from, int to, int weight) {

    Edge newEdge = new Edge(from, to, weight);
    // Set <Edge> edges = adjacencyList.get(from);
    HSet <Edge> edges = adjacencyList.get(from);

    if (edges == null) {
      // edges = new HashSet<>();
      edges = new HSet<>();
      // adjacencyList.add( from, edges );
      adjacencyList.put( from, edges );
    }

    // If edge did not exist before
    if (!edges.add(newEdge))
      edgeCount++;

  }

  // Add an edge to this graph, O(1)
  public void addUndirectedEdge(int from, int to, int weight) {
    addDirectedEdge(from, to, weight);
    addDirectedEdge(to, from, weight);
  }

  // Remove an edge from this Graph, O(E)
  public void removeDirectedEdge(int from, int to) {
    
    // Set <Edge> edges = getEdges(from);
    HSet <Edge> edges = getEdges(from);

    if (edges != null) {

      Edge edgeToRemove = null;

      for (Edge edge : edges) {
        if ( edge.to == to ) {
          edgeToRemove = edge; break;
        }
      }

      if (edges.remove(edgeToRemove))
        edgeCount--;

    }
  }

  public void removeUndirectedEdge(int from, int to) {
    removeDirectedEdge(from, to);
    removeDirectedEdge(to, from);
  }

}

