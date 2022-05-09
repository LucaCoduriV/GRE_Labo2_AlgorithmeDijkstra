
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
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {
    /*
     * NE PAS MODIFIER
     * Les fichiers de données sont à placer à la racine de ce répertoire
     */
    private static final String DATA_FOLDER = "data/";
    private static final int NB_VERTICES = 10000;
    private static final List<Long> itSimple = new ArrayList<>();
    private static final List<Long> itBidirectional = new ArrayList<>();


    public static void main(String[] args) throws IOException {
        var graph = new CartesianGraphReader<>(
                new VertexFactory(),
                new SimpleWeightedEdgeFactory<>(new EdgeWeighterImpl()),
                DATA_FOLDER + "R" + NB_VERTICES + "_1.txt"
        ).graph();

        //PrintStream file = new PrintStream(new FileOutputStream("data/output.txt"));


        Random rdm = new Random(20220404);

        for(int i = 0; i < 1000; i++){

            int source = rdm.nextInt(NB_VERTICES);
            int target = rdm.nextInt(NB_VERTICES);

            var monitor = new DijkstraMonitor();
            var monitor2 = new DijkstraMonitor();
            test(new DijkstraSimple(graph, source,target, monitor), monitor, itSimple);
            test(new DijkstraBidirectional(graph, source,target, monitor2), monitor2, itBidirectional);
            System.out.println(source + " " + target);
        }

        PrintStream out = new PrintStream(new FileOutputStream("data/results.txt"));
        out.println(itBidirectional);
        out.println(itSimple);
        out.println("Moyenne de l'algorithme simple: " + itSimple.stream().mapToDouble(a -> a).average().orElse(0));
        out.println("Moyenne de l'algorithme bidirectional: " + itBidirectional.stream().mapToDouble(a -> a).average().orElse(0));
        out.println("Min de l'algorithme simple: " + itSimple.stream().mapToDouble(a -> a).min().orElse(0));
        out.println("Min de l'algorithme bidirectional: " + itBidirectional.stream().mapToDouble(a -> a).min().orElse(0));
        out.println("Max de l'algorithme simple: " + itSimple.stream().mapToDouble(a -> a).max().orElse(0));
        out.println("Max de l'algorithme bidirectional: " + itBidirectional.stream().mapToDouble(a -> a).max().orElse(0));



        out.close();
    }

    static void test(Dijkstra dijkstra, DijkstraMonitor monitor, List<Long> itList){
        try{
            dijkstra.resolve();
            itList.add(monitor.getIterationCount());
        }catch(Exception e){
            System.out.println("Pas de chemin");
        }

    }

}
