package coduri.luca.graph;

import graph.core.EdgeWeighter;

public class EdgeWeighterImpl implements EdgeWeighter<VertexImpl> {
    @Override
    public long weight(VertexImpl from, VertexImpl to) {
        return computeDistance(from, to);
    }

    private static long computeDistance(VertexImpl from, VertexImpl to) {
        int x1 = from.getX();
        int y1 = from.getY();
        int x2 = to.getX();
        int y2 = to.getY();

        return Math.round(Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2)));
    }
}
