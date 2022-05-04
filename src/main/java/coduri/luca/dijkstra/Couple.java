package coduri.luca.dijkstra;

import coduri.luca.graph.VertexImpl;

class Couple implements Comparable<Couple> {
    static final long INFINITY = Long.MAX_VALUE;
    private long weight;
    private VertexImpl predecessor;
    private VertexImpl vertex;
    private boolean completed;

    Couple(VertexImpl vertex) {
        this.weight = INFINITY;
        this.predecessor = null;
        this.vertex = vertex;
        completed = false;
    }

    long getWeight() {
        return weight;
    }

    VertexImpl getPredecessor() {
        return predecessor;
    }

    void setPredecessor(VertexImpl predecessor) {
        this.predecessor = predecessor;
    }

    void setWeight(long weight) {
        this.weight = weight;
    }

    void addWeight(int weight){
        this.weight += weight;
    }

    public VertexImpl getVertex() {
        return vertex;
    }

    @Override
    public int compareTo(Couple o) {
        if(this.weight == o.weight){
            return vertex.id() - o.vertex.id();
        }
        return this.weight < o.weight ? -1 : 1;

    }

    public boolean isCompleted() {
        return completed;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    @Override
    public String toString() {
        return "(" + weight + ", " + (predecessor == null ? "null" : predecessor.id()) + ")";
    }
}
