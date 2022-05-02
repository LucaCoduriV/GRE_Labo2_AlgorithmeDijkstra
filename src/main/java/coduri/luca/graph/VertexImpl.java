package coduri.luca.graph;

import graph.core.Vertex;

public class VertexImpl implements Vertex {
    private final int id;
    private final int x;
    private final int y;

    public VertexImpl(int id, int x, int y){
        this.id = id;
        this.x = x;
        this.y = y;
    }

    public int getId() {
        return id;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public int id() {
        return 0;
    }

    @Override
    public String toString() {
        return "" + id;
    }
}
