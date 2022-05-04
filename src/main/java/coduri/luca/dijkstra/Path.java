package coduri.luca.dijkstra;

import java.util.Arrays;

public class Path {
    private final long totalWeight;
    private final int[] path;

    Path(long totalWeight, int[] path) {
        this.totalWeight = totalWeight;
        this.path = path;
    }

    public long getTotalWeight() {
        return totalWeight;
    }

    public int[] getPath() {
        return path;
    }

    Path joinPath(Path forward, Path backward){
        
        return new Path();
    }

    @Override
    public String toString() {
        return Arrays.toString(path) + " w: " + totalWeight;
    }
}
