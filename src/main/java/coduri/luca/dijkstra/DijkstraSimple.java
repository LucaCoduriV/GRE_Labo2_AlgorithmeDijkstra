package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.Arrays;
import java.util.LinkedList;

public class DijkstraSimple implements Dijkstra {
    private static final String CONNEXE_CIRCUIT_ERROR = "Le graph n'est pas connexe";
    private final Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph;
    private final DijkstraCallback callback;
    private Couple lastCoupleRemoved;


    /** les couples poids et prédécesseur **/
    private final CouplePrioQueue pq;
    private final int destinationId;

    public DijkstraSimple(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId, DijkstraCallback callback) {
        this.destinationId = destinationId;
        this.graph = graph;
        final VertexImpl[] vertices = graph.getVertices().toArray(VertexImpl[]::new);
        final Couple[] couples = Arrays.stream(vertices).map(Couple::new).toArray(Couple[]::new);
        this.pq = new CouplePrioQueue(couples);
        this.callback = callback;

        pq.get(sourceId).setWeight(0);
        pq.update(pq.get(sourceId));
    }

    public DijkstraSimple(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId){
        this(graph,sourceId,destinationId,null);
    }

    public DijkstraSimple resolve(){
        while(nextIt());
        return this;
    }

    boolean nextIt(){

        if((lastCoupleRemoved = pq.poll()) == null){
            return false;
        }

        if(lastCoupleRemoved.getWeight() == Couple.INFINITY){
            throw new IllegalArgumentException(CONNEXE_CIRCUIT_ERROR);
        }

        visitSuccessors(lastCoupleRemoved);

        if(callback != null)
            callback.onIterationComplete(pq.getCouples(), lastCoupleRemoved);

        return lastCoupleRemoved.getVertex().id() != destinationId;
    }

    Couple[] getState(){
        return pq.couples();
    }
    CouplePrioQueue getPrioQueue(){
        return pq;
    }

    private void visitSuccessors(Couple couple){
        var successors = graph.getSuccessorList(couple.getVertex().id());
        final Couple[] couples = pq.couples();
        successors.forEach(s ->{
            if(couples[s.to().id()].getWeight() > couples[couple.getVertex().id()].getWeight() + s.weight()){
                couples[s.to().id()].setWeight(couples[couple.getVertex().id()].getWeight() + s.weight());
                couples[s.to().id()].setPredecessor(couple.getVertex());

                pq.update(couples[s.to().id()]);
            }
        });
    }

    public Path getPath(){
        var couples = pq.couples();
        LinkedList<Integer> l = new LinkedList<>();
        long totalWeight = couples[destinationId].getWeight();
        int nextId = destinationId;
        while(true){
            var curr = couples[nextId];
            l.addFirst(curr.getVertex().id());
            if(curr.getPredecessor() == null) break;
            nextId = curr.getPredecessor().id();
        }

        return new Path(totalWeight, l.stream().mapToInt(i -> i).toArray());
    }

    Couple getLastCoupleRemoved() {
        return lastCoupleRemoved;
    }
}
