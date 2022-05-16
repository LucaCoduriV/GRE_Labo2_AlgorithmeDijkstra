package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;

/**
 * Cette classe permet de stocker le couple (λ, p) pour les sommets de L.
 * Voir la page 150 du chapitre 5.1 du cours de GRE.
 * <a href="docs/GRE-2122b-chapitre5.1.pdf">GRE Chapitre 5.1</a>
 */
public class Couple implements Comparable<Couple> {
    static final long INFINITY = Long.MAX_VALUE;
    private long weight;
    private VertexImpl predecessor;
    private final VertexImpl vertex;
    private boolean completed;

    Couple(VertexImpl vertex) {
        this.weight = INFINITY;
        this.predecessor = null;
        this.vertex = vertex;
        completed = false;
    }

    /**
     * @return le poids. (λ)
     */
    public long getWeight() {
        return weight;
    }

    /**
     * @return le prédecesseur. (p)
     */
    public VertexImpl getPredecessor() {
        return predecessor;
    }

    /**
     * Mettre à jour le prédecesseur. (λ)
     * @param predecessor
     */
    void setPredecessor(VertexImpl predecessor) {
        this.predecessor = predecessor;
    }

    /**
     * Mettre à jour le poids. (λ)
     * @param weight
     */
    void setWeight(long weight) {
        this.weight = weight;
    }

    /**
     * @return Le sommet auquel appartient ce couple.
     */
    public VertexImpl getVertex() {
        return vertex;
    }

    /**
     *
     * @param o Le sommet avec qui comparer.
     * @implNote Ne retourne jamais 0, car si les poids sont égaux on compare l'id du sommet.
     * @return un nombre négatif si le couple courant est inférieur à o et un nombre positif si le couple courant est supérieur à o.
     */
    @Override
    public int compareTo(Couple o) {
        if(this.weight == o.weight){
            return vertex.id() - o.vertex.id();
        }
        return this.weight < o.weight ? -1 : 1;

    }

    /**
     * @return true si le sommet qui correspond à ce couple est déjà retiré de L.
     */
    public boolean isCompleted() {
        return completed;
    }

    void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "(" + weight + ", " + (predecessor == null ? "null" : predecessor.id()) + ")";
    }
}
