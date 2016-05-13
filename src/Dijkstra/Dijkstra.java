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
        this.heap = new PQHeap(heapSize);
        this.climbData = climbData;
        this.descentData = descentData;
        this.cruiseData = cruiseData;
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
            if ( startFL < q.getFLandWP().getFL() ) {
                q.setCost( p.priceClimb(s1, q, 0, 5, climbData, cruiseData, coordinates ) );
            } else {
                q.setCost( p.priceDescent(s1, q, 0, 5, descentData, cruiseData, coordinates ) );
            }
            listOfPossibleVertices.add(q);
            counter++;
        }

        System.out.println( "Returning our list of new vertices" );

        return listOfPossibleVertices;
    }

    public void Dijkstra_algorithm() {
        vertex s = initialize_single_source();
        vertex lastWP = new vertex(0, coordinates.size()-1);
        //Empty set of vertices
        //ArrayList<vertex> setOfVertices = new ArrayList<>();

        //Inserting all our vertices from graph into our minHeap
        PQHeap q = new PQHeap(50*coordinates.size());
        q.insert(lastWP);


        for (vertex v : analyzeVertex(s,0,5) ) {
            q.insert(v);
        }

        while ( !q.empty() ) {
            vertex u = q.extractMin();
            System.out.println( "Current lowest cost was: " + u.getCost() + " at FL: " + u.getFLandWP().getFL() + " and WP: " + u.getFLandWP().getWP());
        }

    }

}
