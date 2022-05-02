package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;

class Couple {
    private int weight;
    private VertexImpl predecessor;

    Couple(int weight, VertexImpl predecessor) {
        this.weight = weight;
        this.predecessor = predecessor;
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
}
