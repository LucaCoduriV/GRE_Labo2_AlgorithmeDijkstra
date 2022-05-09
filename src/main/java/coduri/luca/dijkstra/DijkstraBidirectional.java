package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.LinkedList;

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

    private boolean adHoc(int[] lastPulledOut, DijkstraSimple dijkstra1, DijkstraSimple dijkstra2) {
        dijkstra1.nextIt();
        lastPulledOut[0] = dijkstra1.getLastCoupleRemoved().getVertex().id();

        graph.getSuccessorList(lastPulledOut[0]).forEach(v ->{
            final long weight = forward.getState()[v.to().id()].getWeight() + backward.getState()[v.to().id()].getWeight();

            if(dijkstra2.getState()[v.to().id()].isCompleted() && weight < bestPath.getTotalWeight()){
                final Path newPathF = getPath(forward.getState(), v.to().id(), false);
                final Path newPathB = getPath(backward.getState(), v.to().id(), true);
                bestPath = Path.joinPath(newPathF, newPathB);
            }


        });

        return dijkstra2.getPrioQueue().get(lastPulledOut[0]).isCompleted();
    }

    @Override
    public Path getPath() {
        return bestPath;
    }

    private static Path getPath(Couple[] couples, int destinationId, boolean removeLast){
        if(couples[destinationId].getPredecessor() == null){
            return new Path(0, new int[0]);
        }

        LinkedList<Integer> l = new LinkedList<>();

        long totalWeight = couples[destinationId].getWeight();
        int nextId = removeLast ? couples[destinationId].getPredecessor().id() : destinationId;

        while(true){
            var curr = couples[nextId];
            l.addFirst(curr.getVertex().id());
            if(curr.getPredecessor() == null) break;
            nextId = curr.getPredecessor().id();
        }

        return new Path(totalWeight, l.stream().mapToInt(i -> i).toArray());
    }
}
