package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.LinkedList;

/**
 * Implémentation de l'algorithme de Dijkstra en bidirectional.
 * @implNote Cette implémentation est basée sur l'implémentation de Dijkstra en unidirectionnel.
 */
public class DijkstraBidirectional implements Dijkstra{
    private DijkstraSimple forward;
    private DijkstraSimple backward;
    private final Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph;
    private final int sourceId;
    private final int destinationId;

    private final DijkstraCallback callback;

    private Path bestPath;
    public DijkstraBidirectional(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId, DijkstraCallback callback){
        this.callback = callback;
        this.graph = graph;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.bestPath = new Path(Couple.INFINITY, new int[0]);
    }

    public DijkstraBidirectional(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId){
        this(graph, sourceId, destinationId, null);
    }

    public Dijkstra resolve(){
        this.forward = new DijkstraSimple(graph, sourceId, destinationId, callback);
        this.backward = new DijkstraSimple(graph, destinationId, destinationId, callback);

        int[] lastForward = {-1};
        int[] lastBackward = {-1};

        while(true){
            if (adHoc(lastForward, forward, backward)) break;
            if (adHoc(lastBackward, backward, forward)) break;
        }

        return this;
    }

    /**
     * Permet de faire une itération de l'algorithme de Dijkstra en avant ou en arrière.
     * Puis de vérifier si le chemin le plus court est déjà trouvé.
     * @param lastPulledOut
     * @param dijkstra1
     * @param dijkstra2
     * @return True si l'algorithme est terminé, false sinon.
     */
    private boolean adHoc(int[] lastPulledOut, DijkstraSimple dijkstra1, DijkstraSimple dijkstra2) {
        dijkstra1.nextIt();
        lastPulledOut[0] = dijkstra1.getLastCoupleRemoved().getVertex().id();

        graph.getSuccessorList(lastPulledOut[0]).forEach(v ->{
            final long weight = forward.getState()[v.to().id()].getWeight() + backward.getState()[v.to().id()].getWeight();

            if(dijkstra2.getState()[v.to().id()].isCompleted() && weight < bestPath.getTotalWeight()){
                final Path newPathF = Path.buildPath(forward.getState(), v.to().id(), false);
                final Path newPathB = Path.buildPath(backward.getState(), v.to().id(), true);
                bestPath = Path.joinPath(newPathF, newPathB);
            }
        });

        return dijkstra2.getPrioQueue().get(lastPulledOut[0]).isCompleted();
    }

    @Override
    public Path getPath() {
        return bestPath;
    }
}
