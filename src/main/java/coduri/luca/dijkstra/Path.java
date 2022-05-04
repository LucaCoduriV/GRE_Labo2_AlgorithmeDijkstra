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

    static Path joinPath(Path forward, Path backward){
        final int[] newPath = new int[forward.path.length + backward.path.length - 1];
        System.arraycopy(forward.path,0,newPath,0,forward.path.length);

        int j = forward.path.length;
        for (int i = (backward.path.length - 2); i >= 0; i--){
            newPath[j++] = backward.path[i];
        }

        return new Path(forward.totalWeight + backward.totalWeight, newPath);
    }

    @Override
    public String toString() {
        return Arrays.toString(path) + " w: " + totalWeight;
    }
}
