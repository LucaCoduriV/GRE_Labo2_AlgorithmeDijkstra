package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class Dijkstra {
    private static final String CONNEXE_CIRCUIT_ERROR = "Le graph n'est pas connexe";
    private final Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph;
    private final DijkstraCallback callback;


    /** les couples poids et prédécesseur **/
    private final CouplePrioQueue pq;
    private final int destinationId;
    private final int sourceId;


    public Dijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId, DijkstraCallback callback) {
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

    public Dijkstra(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId){
        this(graph,sourceId,destinationId,null);
    }

    public Couple[] resolve(){
        while(nextIt());
        printPath(pq.getCouples(),sourceId,destinationId);
        return pq.getCouples();
    }

    boolean nextIt(){
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

        if(currentCouple.getVertex().id() == destinationId){
            return false;
        }

        return true;
    }

    Couple[] getState(){
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

    static void printPath(Couple[] path,int sourceId ,int destinationId){
        LinkedList<Integer> l = new LinkedList<>();
        do{
            var curr = path[destinationId];
            l.addFirst(curr.getVertex().id());
            System.out.println();
            destinationId = curr.getPredecessor().id();
        }while(destinationId != sourceId);
        l.addFirst(sourceId);

        System.out.println(l);
    }
}
