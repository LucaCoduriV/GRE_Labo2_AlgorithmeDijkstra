package coduri.luca.dijkstra;

/**
 * Cette interface permet d'avoir l'état de progression de l'algorithme de Dijkstra.
 */
public interface DijkstraCallback {
    void onIterationComplete(Couple[] state, Couple pulledOut);
}
