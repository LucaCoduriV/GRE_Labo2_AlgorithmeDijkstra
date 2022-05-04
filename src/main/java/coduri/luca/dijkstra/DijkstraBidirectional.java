package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;

import java.util.LinkedList;

public class DijkstraBidirectional implements Dijkstra{
    private DijkstraSimple forward;
    private DijkstraSimple backward;
    private Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph;
    private final int sourceId;
    private final int destinationId;

    private Path bestPath;
    public DijkstraBidirectional(Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph, int sourceId, int destinationId){
        this.graph = graph;
        this.sourceId = sourceId;
        this.destinationId = destinationId;
    }

    public Dijkstra resolve(){
        this.forward = new DijkstraSimple(graph, sourceId, destinationId);
        this.backward = new DijkstraSimple(graph, destinationId, destinationId);
        boolean[] computedVertex = new boolean[graph.getNVertices()];

        int lastForward;
        int lastBackward = -1;

        do{
            forward.nextIt();
            lastForward = forward.getLastCoupleRemoved().getVertex().id();
            if(computedVertex[lastForward]){
                final Path newPathF = getPath(forward.getState(), sourceId, lastForward);
                final Path newPathB = getPath(backward.getState(), sourceId, lastBackward);
                final Path newPath =
                bestPath = bestPath == null ? newPath :
                        bestPath.getTotalWeight() <= newPath.getTotalWeight() ? bestPath : newPath ;
                break;
            }else{
                computedVertex[lastForward] = true;
            }

            backward.nextIt();
            lastBackward = backward.getLastCoupleRemoved().getVertex().id();
            if(computedVertex[lastBackward]){
                final Path newPath = getPath(backward.getState(), sourceId, lastBackward);
                bestPath = bestPath == null ? newPath :
                        bestPath.getTotalWeight() <= newPath.getTotalWeight() ? bestPath : newPath ;
                break;
            }else{
                computedVertex[lastBackward] = true;
            }

        }while(true);

        System.out.println(getPath(forward.getState(), sourceId, lastForward));
        System.out.println(getPath(backward.getState(), destinationId, lastBackward));

        return this;
    }

    private void onPathFound(int intermediateId){
        System.out.println("Found path");
        bestPath =
        getPath(forward.getState(), sourceId, intermediateId);
        getPath(backward.getState(), destinationId, intermediateId);

    }

    @Override
    public Integer[] getPath() {
        return new Integer[0];
    }

    private static Path getPath(Couple[] path, int sourceId, int destinationId){
        LinkedList<Integer> l = new LinkedList<>();
        long totalWeight = 0;
        int nextId = destinationId;
        while(nextId != sourceId){
            var curr = path[nextId];
            l.addFirst(curr.getVertex().id());
            totalWeight += curr.getWeight();
            nextId = curr.getPredecessor().id();
        }
        l.addFirst(sourceId);

        return new Path(totalWeight, l.stream().mapToInt(i -> i).toArray());
    }
}
