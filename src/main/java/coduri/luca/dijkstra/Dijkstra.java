package coduri.luca.dijkstra;

/**
 * Cette interface définit les méthodes qui doivent être implémentées par
 * les classes qui implémentent l'algorithme de Dijkstra.
 */
public interface Dijkstra {
    /**
     * Cette méthode permet de lancer la résolution de l'algorithme.
     * @return this.
     */
    Dijkstra resolve();

    /**
     * @return Un objet Path contenant le chemin le plus court entre le sommet
     * de départ et le sommet d'arrivée. Ainsi que la distance entre ces deux
     * nœuds.
     */
    Path getPath();
}
