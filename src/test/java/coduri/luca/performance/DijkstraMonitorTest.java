package coduri.luca.performance;

import coduri.luca.dijkstra.DijkstraBidirectional;
import coduri.luca.GraphGenerator;
import coduri.luca.dijkstra.DijkstraSimple;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DijkstraMonitorTest {

    @Test
    void getIterationCountDijkstraSimple() {
        var graph = GraphGenerator.graph10000;
        var monitor = new DijkstraMonitor();
        new DijkstraSimple(graph, 0, 100, monitor).resolve();
        System.out.println(monitor.getIterationCount());
        assertNotEquals(0,monitor.getIterationCount());
    }

    @Test
    void getIterationCountDijkstraBi() {
        var graph = GraphGenerator.graph10000;
        var monitor = new DijkstraMonitor();
        new DijkstraBidirectional(graph, 0, 100, monitor).resolve();
        System.out.println(monitor.getIterationCount());
        assertNotEquals(0,monitor.getIterationCount());
    }
}