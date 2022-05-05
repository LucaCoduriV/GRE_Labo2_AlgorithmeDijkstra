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
        this.bestPath = new Path(Couple.INFINITY, new int[0]);
    }

    public Dijkstra resolve(){
        this.forward = new DijkstraSimple(graph, sourceId, destinationId);
        this.backward = new DijkstraSimple(graph, destinationId, destinationId);

        int lastForward;
        int lastBackward = -1;
        Path result = null;

        while(true){
            forward.nextIt();
            lastForward = forward.getLastCoupleRemoved().getVertex().id();

            graph.getSuccessorList(lastForward).forEach(v ->{
                if(backward.getState()[v.to().id()].isCompleted()){
                    final Path newPathF = getPath(forward.getState(), v.to().id(), false);
                    final Path newPathB = getPath(backward.getState(), v.to().id(), true);
                    System.out.println(newPathF);
                    System.out.println(newPathB);
                    final Path newPath = Path.joinPath(newPathF, newPathB);
                    System.out.println(newPath);
                    bestPath = newPath.getTotalWeight() < bestPath.getTotalWeight() ? newPath : bestPath;
                }


            });

            if(backward.getPrioQueue().get(lastForward).isCompleted()){
                break;
            }


            backward.nextIt();
            lastBackward = backward.getLastCoupleRemoved().getVertex().id();

            graph.getSuccessorList(lastBackward).forEach(v ->{
                if(forward.getState()[v.to().id()].isCompleted()){
                    final Path newPathF = getPath(forward.getState(), v.to().id(), false);
                    final Path newPathB = getPath(backward.getState(), v.to().id(), true);
                    System.out.println(newPathF);
                    System.out.println(newPathB);
                    final Path newPath = Path.joinPath(newPathF, newPathB);
                    System.out.println(newPath);
                    bestPath = newPath.getTotalWeight() < bestPath.getTotalWeight() ? newPath : bestPath;
                }
            });

            if(forward.getPrioQueue().get(lastBackward).isCompleted()){
                break;
            }

        }

        return this;
    }

    @Override
    public Path getPath() {
        return bestPath;
    }

    private static Path getPath(Couple[] couples, int destinationId, boolean removeLast){
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
