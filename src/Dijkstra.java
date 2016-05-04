import Data.*;
import PQHeap.*;
import Distance.*;

import java.util.List;

/**
 * Created by Kasper on 04-05-2016.
 */
public class Dijkstra {
    PQHeap heap;
    List<coordinate> Q;
    climbData[][] climbData;

    public Dijkstra(PQHeap heap, List<coordinate> Q, climbData[][] climbData){
        this.heap = heap;
        this.Q = Q;
        this.climbData = climbData;
    }

    readPaths p = new readPaths();


}
