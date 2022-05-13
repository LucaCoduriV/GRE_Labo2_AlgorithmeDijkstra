
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
import coduri.luca.performance.DijkstraMonitor;
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
    private static final List<Long> simpleExecTimes = new ArrayList<>();
    private static final List<Long> itBidirectional = new ArrayList<>();
    private static final List<Long> bidirectionalExecTimes = new ArrayList<>();
    private  static final List<Integer> sources = new ArrayList<>();
    private  static final List<Integer> targets = new ArrayList<>();
    private  static final List<Path> pathsSimple = new ArrayList<>();
    private  static final List<Path> pathsBidir = new ArrayList<>();



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
            sources.add(source);
            targets.add(target);

            var monitor = new DijkstraMonitor();
            var monitor2 = new DijkstraMonitor();

            test(new DijkstraSimple(graph, source,target, monitor), monitor, itSimple, pathsSimple, simpleExecTimes);
            test(new DijkstraBidirectional(graph, source,target, monitor2), monitor2, itBidirectional, pathsBidir, bidirectionalExecTimes);
        }

        PrintStream out = new PrintStream(new FileOutputStream("results.md"));
        out.println(toMarkdownTable());
        out.close();
    }

    static String toMarkdownTable(){
        StringBuilder result = new StringBuilder();

        result.append("| n°   | source | destination | nb itérations simple | nb itérations bidir | temps simple (ms) | temps bidir (ms) |\n");
        result.append("|------|--------|-------------|----------------------|---------------------|-------------------|------------------|\n");

        double moyenneTempsSimple = 0;
        double moyenneTempsBidir = 0;
        double moyenneItSimple = 0;
        double moyenneItBidir = 0;
        int nbItBidiSup = 0;
        int nbTempsBidiSup = 0;

        for(int i = 0; i < sources.size(); i++){
            result.append("| ")
                    .append(padRight(String.valueOf((i + 1)), 4, ' ')).append(" | ")
                    .append(padRight(String.valueOf(sources.get(i)), 6, ' ')).append(" | ")
                    .append(padRight(String.valueOf(targets.get(i)), 11, ' ')).append(" | ")
                    .append(padRight(String.valueOf(itSimple.get(i)),20, ' ')).append(" | ")
                    .append(padRight(String.valueOf(itBidirectional.get(i)), 19, ' ')).append(" | ")
                    .append(padRight(String.valueOf((double)(simpleExecTimes.get(i)) / 1000000), 17, ' ')).append(" | ")
                    .append(padRight(String.valueOf((double)(bidirectionalExecTimes.get(i)) / 1000000), 16, ' ')).append(" |\n");

            moyenneTempsSimple += simpleExecTimes.get(i);
            moyenneTempsBidir += bidirectionalExecTimes.get(i);
            moyenneItSimple += itSimple.get(i);
            moyenneItBidir += itBidirectional.get(i);
            if(itBidirectional.get(i) < itSimple.get(i)) nbItBidiSup++;
            if(bidirectionalExecTimes.get(i) < simpleExecTimes.get(i)) nbTempsBidiSup++;
        }

        moyenneTempsSimple /= sources.size();
        moyenneTempsBidir /= sources.size();
        moyenneItSimple /= sources.size();
        moyenneItBidir /= sources.size();

        result.insert(0, "Temps moyen simple: " + moyenneTempsSimple / 1000000 + "ms\n\n");
        result.insert(0, "Temps moyen bidirectionnel: " + moyenneTempsBidir / 1000000 + "ms\n\n");
        result.insert(0, "Nombre d'itération moyen simple: " + moyenneItSimple + "\n\n");
        result.insert(0, "Nombre d'itération moyen bidirectionnel: " + moyenneItBidir + "\n\n");
        result.insert(0, "Nombre de fois que Bidirectionnel à moins d'itérations : " + nbItBidiSup + "\n\n");
        result.insert(0, "Nombre de fois que bidirectionnel prend moins de temps : " + nbTempsBidiSup + "\n\n");

        return result.toString();
    }

    static void test(Dijkstra dijkstra, DijkstraMonitor monitor, List<Long> itList, List<Path> paths, List<Long> execTimes){

        try{
            long starTime = System.nanoTime();
            var results = dijkstra.resolve().getPath();
            execTimes.add(System.nanoTime() - starTime);
            paths.add(results);
            itList.add(monitor.getIterationCount());
        }catch(Exception e){
            System.out.println("Pas de chemin");
        }

    }

    public static String padRight(String inputString, int length, char c) {
        if (inputString.length() >= length) {
            return inputString;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(inputString);
        while ((sb.length() - inputString.length()) < length - inputString.length()) {
            sb.append(c);
        }

        return sb.toString();
    }

}
