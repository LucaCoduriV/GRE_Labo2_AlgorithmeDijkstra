
/*
 * Le code rendu se situe uniquement dans ce package (event. sous-packages)
 */
package coduri.luca;

import coduri.luca.dijkstra.DijkstraSimple;
import coduri.luca.graph.EdgeWeighterImpl;
import coduri.luca.graph.VertexFactory;
import coduri.luca.graph.VertexImpl;
import graph.core.impl.SimpleWeightedEdge;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.IOException;

public class Main {
    /*
     * NE PAS MODIFIER
     * Les fichiers de données sont à placer à la racine de ce répertoire
     */
    private static final String DATA_FOLDER = "data/";

    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFactory(),
                new SimpleWeightedEdgeFactory<>(new EdgeWeighterImpl()),
                DATA_FOLDER + "R50000_1.txt"
        ).graph();

        DijkstraSimple dijkstra = new DijkstraSimple(graph, 0,10);

        for (int i = 0; i < graph.getNVertices(); i++) {
            for (SimpleWeightedEdge<VertexImpl> s: graph.getSuccessorList(i)) {

                System.out.println(s.from().id() + " " + s.to().id() + " " + s.weight());
            }
        }

        //var test = new BiDijkstra(graph, 0, 14);


        //dijkstra.resolve();

    }

}
