package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;

public interface DijkstraCallback {
    void onIterationComplete(Couple[] state, Couple pulledOut);
}
