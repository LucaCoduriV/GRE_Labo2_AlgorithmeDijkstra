package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;

class Couple {
    static final int INFINITY = Integer.MAX_VALUE;
    private int weight;
    private VertexImpl predecessor;

    Couple() {
        this.weight = INFINITY;
        this.predecessor = null;
    }

    int getWeight() {
        return weight;
    }

    VertexImpl getPredecessor() {
        return predecessor;
    }

    void setPredecessor(VertexImpl predecessor) {
        this.predecessor = predecessor;
    }

    void setWeight(int weight) {
        this.weight = weight;
    }

    void addWeight(int weight){
        this.weight += weight;
    }
}
