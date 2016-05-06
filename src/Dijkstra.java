import Data.climbData;
import Data.descentData;
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

    //private double fuelTo

    public Dijkstra(File path, int heapSize){
        coordinates = p.waypoints(path);
        heap = new PQHeap(heapSize);
    }

    public void giveCoordinates (climbData[][] climbDatas, descentData[][] descentDatas) {

        coordinate coordinateFROM = coordinates.get(0);
        coordinate coordinateTO = coordinates.get(3);
        System.out.println( "From " + coordinateFROM.getLongitude() + ":" + coordinateFROM.getLatitude() );
        System.out.println( "To " + coordinateTO.getLongitude() + ":" + coordinateTO.getLatitude() );


        initializeSingleSource(climbDatas,descentDatas,coordinateFROM,coordinateTO,0,2);
    }


    public void initializeSingleSource(climbData[][] climbDatas, descentData[][] descentDatas, coordinate coordinateFROM, coordinate coordinateTO, Integer FL, Integer weight){
        int clistSize = coordinates.size();

        Integer maxFL = p.largestClimbAdjacent(climbDatas,coordinateFROM,coordinateTO,FL,0,weight);

        Integer minFL = p.largestDescentAdjacent(descentDatas, coordinateFROM,coordinateTO,FL,0,weight);

        Integer FLRange = maxFL - minFL;

        System.out.println( "From -" + minFL + " to " + maxFL );

    }
}
