package coduri.luca.dijkstra;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DijkstraBidirectionalTest {

    @Test
    void resolve15() {
        var graph = GraphGenerator.graph15;
        var result = new DijkstraBidirectional(graph, 0, 10).resolve().getPath();
        int[] expected = {0,7,12,10};
        long expectedWeight = 9;

        assertArrayEquals(expected, result.getPath());
        assertEquals(expectedWeight, result.getTotalWeight());
    }

    @Test
    void resolve10000_1() {
        var graph = GraphGenerator.graph10000;
        var result = new DijkstraBidirectional(graph, 0, 10).resolve().getPath();
        int[] expected = {0,2713,1493,2803,5425,8614,5559,7807,2675,7625,4870,440,9701,3373,6699,8453,7202,7877,
                9998,4468,7120,2206,2109,6458,1636,2497,8968,8289,6777,370,9,7280,8911,2137,3490,993,6718,10};
        long expectedWeight = 3641;

        assertArrayEquals(expected, result.getPath());
        assertEquals(expectedWeight, result.getTotalWeight());
    }

    @Test
    void resolve10000_2() {
        var graph = GraphGenerator.graph10000;
        var result = new DijkstraBidirectional(graph, 0, 9999).resolve().getPath();
        int[] expected = {0,2713,1493,2803,504,9732,7480,9715,8393,8381,4396,6242,5846,6243,9999};
        long expectedWeight = 1646;

        assertArrayEquals(expected, result.getPath());
        assertEquals(expectedWeight, result.getTotalWeight());
    }

    @Test
    void resolve50000_1() {
        var graph = GraphGenerator.graph50000;
        var result = new DijkstraBidirectional(graph, 0, 25000).resolve().getPath();
        int[] expected = {0,43305,9788,32,46905,28169,5741,33907,9434,15960,39650,33107,16840,35459,5078,33295,37093,
                35059,2064,31072,29430,17973,41011,49947,9335,1395,42415,15832,26793,27970,16859,3151,28084,10826,
                37067,4708,1578,46654,5972,24563,44377,23057,11935,11857,23843,39723,30164,9814,3092,30836,44692,
                40634,15601,19386,48337,19833,30339,38718,22655,35544,37807,45944,6018,1703,1278,27384,5401,31363,
                28364,17622,31613,28588,20780,41769,34781,45094,48050,23786,31926,28659,46993,29135,12422,47688,36960,
                32852,37933,2018,25000};
        long expectedWeight = 4195;

        //assertArrayEquals(expected, result.getPath());
        assertEquals(expectedWeight, result.getTotalWeight());
    }
}