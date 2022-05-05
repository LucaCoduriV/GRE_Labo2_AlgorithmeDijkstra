package coduri.luca.dijkstra;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PathTest {

    @Test
    void joinPath() {
        int[] a = {1,2};
        int[] b = {5,4,3};
        int[] e = {1,2,3,4,5};

        var p1 = new Path(10,a);
        var p2 = new Path(10,b);
        var r = Path.joinPath(p1,p2).getPath();

        assertArrayEquals(e,r);
    }

    @Test
    void joinPath2() {
        int[] a = {1,2,3,4,5,6,7};
        int[] b = {12,11,10,9,8};
        int[] e = {1,2,3,4,5,6,7,8,9,10,11,12};

        var p1 = new Path(10,a);
        var p2 = new Path(10,b);
        var r = Path.joinPath(p1,p2).getPath();

        assertArrayEquals(e,r);
    }
}