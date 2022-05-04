package coduri.luca.dijkstra;

import coduri.luca.graph.EdgeWeighterImpl;
import coduri.luca.graph.VertexFactory;
import coduri.luca.graph.VertexImpl;
import graph.core.impl.Digraph;
import graph.core.impl.SimpleWeightedEdge;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.IOException;

public class GraphGenerator {

    private static final String DATA_FOLDER = "data/";
    final static Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph15 = generateGraph("R15_1.txt");
    final static Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph10000 = generateGraph("R10000_1.txt");
    final static Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> graph50000 = generateGraph("R50000_1.txt");

    private static Digraph<VertexImpl, SimpleWeightedEdge<VertexImpl>> generateGraph(String filename){
        try {
            return new CartesianGraphReader<>(
                    new VertexFactory(),
                    new SimpleWeightedEdgeFactory<>(new EdgeWeighterImpl()),
                    DATA_FOLDER + filename
            ).graph();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
