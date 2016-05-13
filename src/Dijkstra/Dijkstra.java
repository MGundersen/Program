package Dijkstra;

import Data.climbData;
import Data.cruiseData;
import Data.descentData;
import Distance.*;
import PQHeap.*;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasper on 04-05-2016.
 */
public class Dijkstra {

    readPaths p = new readPaths();
    List<coordinate> coordinates;
    PQHeap heap;
    climbData[][] climbData;
    descentData[][] descentData;
    cruiseData[][] cruiseData;

    public Dijkstra(File path, int heapSize, climbData[][] climbData,  descentData[][] descentData, cruiseData[][] cruiseData){
        this.coordinates = p.waypoints(path);
        this.climbData = climbData;
        this.descentData = descentData;
        this.cruiseData = cruiseData;
        this.heap = new PQHeap(50*coordinates.size());
    }

    private void relax(vertex u, vertex v, Integer w){

        if (v.getCost() > (u.getCost()) + w){
            v.setCost(u.getCost() + w);
            v.setPredecessor(u);
        }

    }

    private vertex initialize_single_source() {
        return new vertex(0,0);
    }

    public void analyzeVertex(vertex s1, int ISA, int weight){
        FLandWP s1FLandWP = s1.getFLandWP();
        //System.out.println( "Current FL: " + s1FLandWP.getFL() + " and WP: " + s1FLandWP.getWP() );
        int max = p.largestClimbAdjacent(climbData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weight);
        int min = p.largestDescentAdjacent(descentData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weight);

        int startFL = s1FLandWP.getFL();

        if (max > 490) {
            max = 490;
        }
        if (min > 10) {
            min = 10;
        }

        max = max / 10;
        min = min / 10;
        Integer nrOfVertices = (max - min);


        if ( s1.getFLandWP().getWP() == coordinates.size()-2 ) {
            vertex q = new vertex(0,s1FLandWP.getWP()+1);
            q.setPredecessor(s1);
            vertex y = heap.search(q.getFLandWP());
            Double cCost = p.priceDescent(s1, q, 0, 5, descentData, cruiseData, coordinates );
            q.setCost( cCost );
            if ( y == null ) {
                heap.insert(q);
            } else {
                if (y.getCost() > q.getCost()) {
                    //If new vertex has lower cost, remove the old vertex and insert the new vertex with the lower cost
                    q.setCost( q.getCost() + y.getCost() );
                    heap.remove(y.getFLandWP());
                    heap.insert(q);
                }
            }
        } else {
            Integer counter = min;
            while ( counter <= nrOfVertices + min) {
                vertex q = new vertex(counter,s1FLandWP.getWP()+1);
                q.setPredecessor(s1);
                vertex y = heap.search(q.getFLandWP());
                if ( startFL < q.getFLandWP().getFL() ) {
                    Double cCost = p.priceClimb(s1, q, 0, 5, climbData, cruiseData, coordinates );
                    if (cCost >= 0) {
                        q.setCost( cCost );
                    }
                } else {
                    Double cCost = p.priceDescent(s1, q, 0, 5, descentData, cruiseData, coordinates );
                    if (cCost >= 0) {
                        q.setCost( cCost );
                    }
                }
                if ( y == null ) {
                    heap.insert(q);
                } else {
                    //System.out.println( "Old cost: " + y.getCost() + " - new cost: " + q.getCost() );
                    if (y.getCost() > q.getCost()) {
                        //If new vertex has lower cost, remove the old vertex and insert the new vertex with the lower cost
                        q.setCost( q.getCost() + y.getCost() );
                        heap.remove(y.getFLandWP());
                        heap.insert(q);
                    }
                }
                counter++;
            }
        }
    }

    public void Dijkstra_algorithm() {
        /* Our vertex in in the first WP(Start) */
        vertex s = initialize_single_source();
        System.out.println( "Start FL: " + s.getFLandWP().getFL() + " and WP: " + s.getFLandWP().getWP() );

        /* Our vertex in in the last WP(End) */
        vertex lastWP = new vertex(0, coordinates.size()-1);
        System.out.println( "End FL: " + lastWP.getFLandWP().getFL() + " and WP: " + lastWP.getFLandWP().getWP() );

        /* Manually inserting our last WP into our minHeap, so we have a reference to the last WP */
        heap.insert(lastWP);
        analyzeVertex(s,0,5);

        Double currentFinalCost = Double.POSITIVE_INFINITY;
        vertex finalVertex = new vertex(0,0);
        while ( !heap.empty() ) {
            vertex u = heap.extractMin();

            if ( u.getFLandWP().getFL() == lastWP.getFLandWP().getFL() && u.getFLandWP().getWP() == lastWP.getFLandWP().getWP() ) {
                if ( u.getCost() < currentFinalCost ) {
                    System.out.println( u.getCost() + " was smaleller than " + currentFinalCost );
                    currentFinalCost = u.getCost();
                    finalVertex = u;
                    heap.remove( u.getFLandWP() );
                    heap.insert( u );
                }
            } else analyzeVertex(u,0,5);
        }
        System.out.println( "This is the last vertex - FL:" + finalVertex.getFLandWP().getFL() + " and WP: " + finalVertex.getFLandWP().getWP() + " and the final cost was: " + finalVertex.getCost() );
        backtracking(finalVertex);
    }

    private void backtracking (vertex v) {
        vertex pi = v;
        while ( pi != null ) {
            System.out.println( "FL: " + pi.getFLandWP().getFL() + " and WP: " + pi.getFLandWP().getWP() + " and current cost: " + pi.getCost() );
            pi = pi.getPredecessor();
        }

    }

}
