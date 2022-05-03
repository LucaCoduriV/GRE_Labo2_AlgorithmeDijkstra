
/*
 * Le code rendu se situe uniquement dans ce package (event. sous-packages)
 */
package coduri.luca;

import coduri.luca.dijkstra.Dijkstra;
import coduri.luca.graph.EdgeWeighterImpl;
import coduri.luca.graph.VertexFactory;
import coduri.luca.graph.VertexImpl;
import graph.core.Vertex;
import graph.core.impl.SimpleWeightedEdge;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Main {
    /*
     * NE PAS MODIFIER
     * Les fichiers de données sont à placer à la racine de ce répertoire
     */
    private static final String DATA_FOLDER = "data/";

    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFactory()                                         /* TODO: Fournir une fabrique de sommets (il
                                                                            s'agit d'une interface fonctionnelle) */,
                new SimpleWeightedEdgeFactory<>(new EdgeWeighterImpl()      /* TODO: Fournir une fonction de pondération
                                                                            renvoyant la distance euclidienne (arrondie
                                                                            à l'entier le plus proche) entre l'extrémité
                                                                            initiale et l'extrémité finale de l'arête */),
                DATA_FOLDER + "R15_1.txt"                                   /* TODO: Chemin des fichiers */
        ).graph();

        Dijkstra dijkstra = new Dijkstra(graph);

        dijkstra.resolve();

    }
}
