package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.Arrays;

public class Dijkstra {
    private static final String CONNEXE_CIRCUIT_ERROR = "Le graph n'est pas connexe";
    private final Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph;
    private final DijkstraCallback callback;


    /** les couples poids et prédécesseur **/
    private final CouplePrioQueue pq;


    public Dijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, DijkstraCallback callback) {

        this.graph = graph;
        final VertexImpl[] vertices = graph.getVertices().toArray(VertexImpl[]::new);
        final Couple[] couples = Arrays.stream(vertices).map(Couple::new).toArray(Couple[]::new);
        this.pq = new CouplePrioQueue(couples);
        this.callback = callback;

        pq.get(sourceId).setWeight(0);
        pq.update(pq.get(sourceId));
    }

    public Dijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId){
        this(graph,sourceId,null);
    }

    public Couple[] resolve(){
        while(nextIt());
        return pq.getCouples();
    }

    public boolean nextIt(){
        Couple currentCouple;
        if((currentCouple = pq.poll()) == null){
            return false;
        }

        if(currentCouple.getWeight() == Couple.INFINITY){
            throw new IllegalArgumentException(CONNEXE_CIRCUIT_ERROR);
        }

        visitSuccessors(currentCouple);

        if(callback != null)
            callback.onIterationComplete(pq);

        return true;
    }

    public Couple[] getState(){
        return pq.getCouples();
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
}
