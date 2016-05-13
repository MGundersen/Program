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

    public List<vertex> analyzeVertex(vertex s1, int ISA, int weight){
        FLandWP s1FLandWP = s1.getFLandWP();
        System.out.println( "Input vertex - FL: " + s1FLandWP.getFL() + " - WP: " + s1FLandWP.getWP() );
        int max = p.largestClimbAdjacent(climbData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weight);
        int min = p.largestDescentAdjacent(descentData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weight);

        int startFL = s1FLandWP.getFL();
        List<vertex> listOfPossibleVertices = new ArrayList<>();

        if ( s1FLandWP.getWP() == coordinates.size()-2 ) {
            System.out.println( "Returning our last waypoint - destination" );
            listOfPossibleVertices.add(new vertex(0, s1FLandWP.getWP()+1));
            return listOfPossibleVertices;
        }

        if (max > 490) {
            max = 490;
        }
        if (min > 10) {
            min = 10;
        }

        max = max / 10;
        min = min / 10;
        System.out.println( "FL max: " + max + " - FL min: " + min );
        Integer nrOfVertices = (max - min);

        Integer counter = min;
        while ( counter <= nrOfVertices + min) {
            vertex q = new vertex(counter,s1FLandWP.getWP()+1);
            if ( heap.search(q.getFLandWP()) == null ) {
                if ( startFL < q.getFLandWP().getFL() ) {
                    Double cCost = p.priceClimb(s1, q, 0, 5, climbData, cruiseData, coordinates );
                    if (cCost >= 0) {
                        q.setCost( cCost );
                        listOfPossibleVertices.add(q);
                    }
                } else {
                    Double cCost = p.priceDescent(s1, q, 0, 5, descentData, cruiseData, coordinates );
                    if (cCost >= 0) {
                        q.setCost( cCost );
                        listOfPossibleVertices.add(q);
                    }
                }
            } else {
                //Get Vertex from list. If new vertex has lower cost, remove the vertex
            }

            counter++;
        }

        System.out.println( "Returning a new list of new vertices at WP: " + s1FLandWP.getWP()+1 );
        return listOfPossibleVertices;
    }

    public void Dijkstra_algorithm() {
        /* Our vertex in in the first WP(Start) */
        vertex s = initialize_single_source();
        /* Our vertex in in the last WP(End) */
        vertex lastWP = new vertex(0, coordinates.size()-1);


        /* Inserting all our vertices from graph into our minHeap */

        heap.insert(lastWP);

        /* Inseting our first vertices into our minHeap */
        for (vertex v : analyzeVertex(s,0,5) ) {
            heap.insert(v);
        }

        if (heap.search(new FLandWP(33,1)) == null) {
            System.out.println( "Vertex is not in our minHeap" );
        } else {
            System.out.println( "Vertex is in our minHeap" );
        }

        while ( !heap.empty() ) {
            vertex u = heap.extractMin();
            System.out.println( "Current lowest cost was: " + u.getCost() + " at FL: " + u.getFLandWP().getFL() + " and WP: " + u.getFLandWP().getWP());

        }


    }

}
