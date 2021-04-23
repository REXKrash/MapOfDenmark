package bfst21.pathfinding;

import edu.princeton.cs.algs4.IndexMinPQ;
import edu.princeton.cs.algs4.Stack;


public class Dijkstra {

    private final double[] distTo;
    private final Edge[] edgeTo;
    private final IndexMinPQ<Double> pq;
    private boolean foundDestination;

    public Dijkstra(DirectedGraph directedGraph, Coordinate source, Coordinate destination) {

        int sourceID = directedGraph.getVertexID(source);
        int destinationID = directedGraph.getVertexID(destination);

        int vertexAmount = directedGraph.getVertexAmount();

        distTo = new double[vertexAmount];
        edgeTo = new Edge[vertexAmount];

        for (int v = 0; v < vertexAmount; v++) {
            distTo[v] = Double.POSITIVE_INFINITY;
        }
        distTo[sourceID] = 0.0;

        pq = new IndexMinPQ<>(vertexAmount);
        pq.insert(sourceID, distTo[sourceID]);
        while (!pq.isEmpty() && !foundDestination) {
            int vertexID = pq.delMin();

            if (vertexID == destinationID) {
                foundDestination = true;
            }
            for (Edge edge : directedGraph.getAdjacentEdges(vertexID)) {
                relax(edge);
            }
        }
    }

    public void relax(Edge edge) {
        int v = edge.getFrom();
        int w = edge.getTo();

        double weight = edge.getWeight();

        if (distTo[w] > distTo[v] + weight) {
            distTo[w] = distTo[v] + weight;
            edgeTo[w] = edge;

            if (pq.contains(w)) {
                pq.decreaseKey(w, distTo[w]);

            } else {
                pq.insert(w, distTo[w]);
            }
        }
    }

    public boolean hasPathTo(int targetID) {
        return distTo[targetID] < Double.POSITIVE_INFINITY;
    }

    public Iterable<Edge> pathTo(int targetID) {
        if (!hasPathTo(targetID)) {
            return null;
        }
        Stack<Edge> path = new Stack<>();
        for (Edge e = edgeTo[targetID]; e != null; e = edgeTo[e.getFrom()]) {
            path.push(e);
        }
        return path;
    }

    public Edge[] getEdgeTo() {
        return edgeTo;
    }
}
