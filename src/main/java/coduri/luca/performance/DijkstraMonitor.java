package coduri.luca.performance;

import coduri.luca.dijkstra.Couple;
import coduri.luca.dijkstra.DijkstraCallback;

public class DijkstraMonitor implements DijkstraCallback {
    private long iterationCount = 0;

    @Override
    public void onIterationComplete(Couple[] state, Couple pulledOut) {
        iterationCount++;
    }

    public Long getIterationCount() {
        return iterationCount;
    }
}
