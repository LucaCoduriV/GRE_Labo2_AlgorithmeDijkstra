package coduri.luca.dijkstra;

public interface DijkstraCallback {
    void onIterationComplete(Couple[] state, Couple pulledOut);
}
