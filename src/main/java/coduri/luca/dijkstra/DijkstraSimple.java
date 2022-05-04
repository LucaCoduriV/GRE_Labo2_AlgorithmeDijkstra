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
    private final int sourceId;


    public DijkstraSimple(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId, DijkstraCallback callback) {
        this.destinationId = destinationId;
        this.sourceId = sourceId;
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
            callback.onIterationComplete(pq, lastCoupleRemoved);

        return lastCoupleRemoved.getVertex().id() != destinationId;
    }

    Couple[] getState(){
        return pq.getCouples();
    }
    CouplePrioQueue getPrioQueue(){
        return pq;
    }

    private void visitSuccessors(Couple couple){
        var successors = graph.getSuccessorList(couple.getVertex().id());
        final Couple[] couples = pq.getCouples();
        successors.forEach(s ->{
            if(couples[s.to().id()].getWeight() > couples[couple.getVertex().id()].getWeight() + s.weight()){
                couples[s.to().id()].setWeight(couples[couple.getVertex().id()].getWeight() + s.weight());
                couples[s.to().id()].setPredecessor(couple.getVertex());

                pq.update(couples[s.to().id()]);
            }
        });
    }

    public Path getPath(){
        var path = pq.getCouples();
        LinkedList<Integer> l = new LinkedList<>();
        long totalWeight = 0;
        int nextId = destinationId;
        while(true){
            var curr = path[nextId];
            l.addFirst(curr.getVertex().id());
            totalWeight += curr.getWeight();
            if(curr.getPredecessor() == null) break;
            nextId = curr.getPredecessor().id();
        }

        return new Path(totalWeight, l.stream().mapToInt(i -> i).toArray());
    }

    public Couple getLastCoupleRemoved() {
        return lastCoupleRemoved;
    }
}
