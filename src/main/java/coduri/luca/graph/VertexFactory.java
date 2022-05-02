package coduri.luca.graph;

import graph.data.CartesianVertexData;

public class VertexFactory implements graph.core.VertexFactory<VertexImpl, CartesianVertexData> {
    @Override
    public VertexImpl makeVertex(int id, CartesianVertexData additionalData) {
        return new VertexImpl(id, additionalData.x, additionalData.y);
    }
}
