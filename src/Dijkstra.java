import Distance.*;
import PQHeap.*;

import java.io.File;
import java.util.List;

/**
 * Created by Kasper on 04-05-2016.
 */
public class Dijkstra {
    readPaths p = new readPaths();
    List<coordinate> coordinates;
    PQHeap heap;

    private double fuelTo

    public Dijkstra(File path, int heapSize){
        coordinates = p.waypoints(path);
        heap = new PQHeap(heapSize);
    }


    private void initializeSingleSource(List<coordinate> coordinates){
        int clistSize = coordinates.size();

    }
}
