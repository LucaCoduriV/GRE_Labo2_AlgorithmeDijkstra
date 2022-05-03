package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

public class Dijkstra {

    private static final String NEG_CIRCUIT_ERROR = "Le réseau contient un circuit à coût négatif accessible depuis la source";
    private final Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph;

    /** Les sommets **/
    private final VertexImpl[] vertices;

    /** les couples poids et prédécesseur **/
    private final Couple[] couples;
    private int numberIteration = 0;

    public Dijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph){
        this.graph = graph;
        this.vertices = graph.getVertices().toArray(VertexImpl[]::new);
        this.couples = new Couple[vertices.length];

    }

    public Couple[] resolve(){
        final int lambdaSource = 0;
        final CustomList L = new CustomList(vertices);
        VertexImpl currentVertex;
        while((currentVertex = L.removeFirstAvailable()) != null){

        }
        return new Couple[0];
    }
}
