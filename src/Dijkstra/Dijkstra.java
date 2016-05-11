package Dijkstra;

import Data.climbData;
import Data.descentData;
import Distance.*;
import PQHeap.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import Data.climbData;

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

    private void initialize_single_source (List<vertex> graph, vertex s) {
        for (vertex v : graph) {
            //Setting D(our estimated shortest path from s to v) to ~infinite
            v.setD(Integer.MAX_VALUE);
            //Sætter dens forrige til null
            v.setPredecessor(null);
        }
        //Sætter source(første vertex) til at have afstand = 0(til sig selv)
        s.setD(0);
    }

    private void relax(vertex u, vertex v, Integer w){

        if (v.getD() > (u.getD()) + w){
            v.setD(u.getD() + w);
            v.setPredecessor(u);
        }

    }


    public void analyzeVertex(vertex s1, vertex s2, climbData[][] climbData, descentData[][] descentData, int ISA, int weight){
        FLandWP s1FLandWP = s1.getFLandWP();
        FLandWP s2FLandWP = s2.getFLandWP();
        int max = p.largestClimbAdjacent(climbData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s2FLandWP.getWP()), s1FLandWP.getFL(), ISA, weight);
        int min = p.largestDescentAdjacent(descentData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s2FLandWP.getWP()), s1FLandWP.getFL(), ISA, weight);

        System.out.println( "FL max: " + max + " - FL min: " + min );
    }

    public void Dijkkstra_algorithm(List<vertex> graph, vertex s) {
        initialize_single_source(graph, s);

        //Empty set of vertices
        ArrayList<vertex> setOfVertices = new ArrayList<>();

        //Inserting all our vertices from graph into our minHeap
        PQHeap q = new PQHeap(100);
        for (vertex v : graph) {
            q.insert(v);
        }

        while (!q.empty()) {
            vertex u = q.extractMin();
            setOfVertices.add(u);
            //MANGLER FOR LOOP(Side. 658)
//            for ()
        }


    }

}
