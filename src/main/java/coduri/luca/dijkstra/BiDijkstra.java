package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.Arrays;

public class BiDijkstra implements DijkstraCallback{
    Dijkstra forward;
    Dijkstra backward;

    public BiDijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId){
        this.forward = new Dijkstra(graph, sourceId, destinationId, this);
        this.backward = new Dijkstra(graph, destinationId, destinationId,this);
        //forward.resolve();
        backward.resolve();
    }

    public Couple[] resolve(){

        while(true){
            forward.nextIt();
            backward.nextIt();

            forward.getState();
            backward.getState();
        }

        return null;
    }

    @Override
    public void onIterationComplete(CouplePrioQueue queue) {
        System.out.println(Arrays.toString(queue.getCouples()));
    }
}
