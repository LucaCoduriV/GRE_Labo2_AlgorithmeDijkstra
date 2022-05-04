package coduri.luca.dijkstra;

public interface DijkstraPredicate {
    void onIterationComplete(CouplePrioQueue queue);
}
