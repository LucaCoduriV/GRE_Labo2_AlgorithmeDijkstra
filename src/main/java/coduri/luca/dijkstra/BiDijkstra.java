package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.Arrays;

public class BiDijkstra implements DijkstraCallback{
    public BiDijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId){
        //var forward = new Dijkstra(graph, sourceId, this);
        var backward = new Dijkstra(graph, destinationId, this);
        //forward.resolve();
        backward.resolve();
    }

    @Override
    public void onIterationComplete(CouplePrioQueue queue) {
        System.out.println(Arrays.toString(queue.getCouples()));
    }
}
