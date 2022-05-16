package coduri.luca.dijkstra;

import java.util.Arrays;
import java.util.LinkedList;

public class Path {
    private final long totalWeight;
    private final int[] path;

    Path(long totalWeight, int[] path) {
        this.totalWeight = totalWeight;
        this.path = path;
    }

    /**
     * Le poids total du chemin.
     * @return distance.
     */
    public long getDistance() {
        return totalWeight;
    }

    /**
     * @return un taleau d'entiers représentant le chemin.
     */
    public int[] getPath() {
        return path;
    }

    /**
     * Permet de lier deux chemins pour l'algorithme de Dijkstra bidirectionnel.
     * @param forward chemin du point de départ vers la destination
     * @param backward chemin de la destination vers le point de départ
     * @return un nouveau chemin.
     */
    static Path joinPath(Path forward, Path backward){
        final int[] newPath = new int[forward.path.length + backward.path.length];
        System.arraycopy(forward.path,0,newPath,0,forward.path.length);

        int j = forward.path.length;
        for (int i = (backward.path.length - 1); i >= 0; i--){
            newPath[j++] = backward.path[i];
        }

        return new Path(forward.totalWeight + backward.totalWeight, newPath);
    }

    /**
     * Permet de construire le chemin à partir d'un tableau de couples.
     * @param couples tableau de couples
     * @param destinationId id de la destination
     * @param removeLast true si on doit supprimer le dernier élément du chemin
     * @return chemin
     */
    static Path buildPath(Couple[] couples, int destinationId, boolean removeLast){
        if(couples[destinationId].getPredecessor() == null){
            return new Path(0, new int[0]);
        }

        LinkedList<Integer> l = new LinkedList<>();

        long totalWeight = couples[destinationId].getWeight();
        int nextId = removeLast ? couples[destinationId].getPredecessor().id() : destinationId;

        while(true){
            var curr = couples[nextId];
            l.addFirst(curr.getVertex().id());
            if(curr.getPredecessor() == null) break;
            nextId = curr.getPredecessor().id();
        }

        return new Path(totalWeight, l.stream().mapToInt(i -> i).toArray());
    }

    @Override
    public String toString() {
        return Arrays.toString(path) + " w: " + totalWeight;
    }
}
