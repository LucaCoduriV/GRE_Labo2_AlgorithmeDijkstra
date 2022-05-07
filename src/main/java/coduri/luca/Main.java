
/*
 * Le code rendu se situe uniquement dans ce package (event. sous-packages)
 */
package coduri.luca;

import coduri.luca.dijkstra.Dijkstra;
import coduri.luca.dijkstra.DijkstraBidirectional;
import coduri.luca.dijkstra.DijkstraSimple;
import coduri.luca.dijkstra.Path;
import coduri.luca.graph.EdgeWeighterImpl;
import coduri.luca.graph.VertexFactory;
import coduri.luca.graph.VertexImpl;
import coduri.luca.performance.DijkstraMonitor;
import graph.core.impl.SimpleWeightedEdge;
import graph.core.impl.SimpleWeightedEdgeFactory;
import graph.reader.CartesianGraphReader;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Random;

public class Main {
    /*
     * NE PAS MODIFIER
     * Les fichiers de données sont à placer à la racine de ce répertoire
     */
    private static final String DATA_FOLDER = "data/";
    private static final int NB_VERTICES = 10000;

    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFactory(),
                new SimpleWeightedEdgeFactory<>(new EdgeWeighterImpl()),
                DATA_FOLDER + "R" + NB_VERTICES + "_1.txt"
        ).graph();

        Random rdm = new Random(20220404);

        for(int i = 0; i < 1000; i++){

            int source = rdm.nextInt(NB_VERTICES);
            int target = rdm.nextInt(NB_VERTICES);

            var monitor = new DijkstraMonitor();
            var monitor2 = new DijkstraMonitor();
            test(new DijkstraSimple(graph, source,target, monitor), monitor);
            test(new DijkstraBidirectional(graph, source,target, monitor2), monitor2);
        }



    }

    static void test(Dijkstra dijkstra, DijkstraMonitor monitor){
        try{
            Path result = dijkstra.resolve().getPath();
            System.out.println("Nombre d'itération = " + monitor.getIterationCount());
            System.out.println("Distance: " + result.getTotalWeight());
        }catch(Exception e){
            System.out.println("Pas de chemin");
        }

    }

}
