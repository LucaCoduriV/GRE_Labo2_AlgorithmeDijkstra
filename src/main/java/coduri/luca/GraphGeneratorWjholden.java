
/*
 * Le code rendu se situe uniquement dans ce package (event. sous-packages)
 */
package coduri.luca;

import coduri.luca.graph.EdgeWeighterImpl;
import coduri.luca.graph.VertexFactory;
import coduri.luca.graph.VertexImpl;
import graph.core.impl.SimpleWeightedEdge;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;

/**
 * Cette classe permet de générer un fichier contenant [vertex1] [vertex2] [weight]
 * Cette à été utilisé uniquement dans le but de pouvoir comparer mes résultats avec ceux du site <a href="https://wjholden.com/dijkstra">Dijkstra resolver</a>
 */
public class GraphGeneratorWjholden {
    private static final String DATA_FOLDER = "data/";
    private static final int NB_VERTICES = 15;


    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFactory(),
                new SimpleWeightedEdgeFactory<>(new EdgeWeighterImpl()),
                DATA_FOLDER + "R" + NB_VERTICES + "_1.txt"
        ).graph();

        PrintStream out = new PrintStream(
                new FileOutputStream("data/outputR" + NB_VERTICES + ".txt"));
        System.setOut(out);

        for (int i = 0; i < graph.getNVertices(); i++) {
            for (SimpleWeightedEdge<VertexImpl> s: graph.getSuccessorList(i)) {

                System.out.println(s.from().id() + " " + s.to().id() + " " + s.weight());
            }
        }
    }

}
