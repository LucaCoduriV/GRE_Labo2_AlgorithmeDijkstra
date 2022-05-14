package coduri.luca.performance;

import coduri.luca.dijkstra.Couple;
import coduri.luca.dijkstra.DijkstraCallback;

/**
 * Cette classe permet de compter le nombre d'itérations de Dijkstra.
 */
public class DijkstraMonitor implements DijkstraCallback {
    private long iterationCount = 0;

    @Override
    public void onIterationComplete(Couple[] state, Couple pulledOut) {
        iterationCount++;
    }

    /**
     * @return le nombre d'itérations de Dijkstra.
     */
    public Long getIterationCount() {
        return iterationCount;
    }
}
