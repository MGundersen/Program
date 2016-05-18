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

    public Dijkstra(File path, climbData[][] climbData,  descentData[][] descentData, cruiseData[][] cruiseData){
        this.coordinates = p.waypoints(path);
        this.climbData = climbData;
        this.descentData = descentData;
        this.cruiseData = cruiseData;
        this.heap = new PQHeap(50*coordinates.size());
    }

<<<<<<< HEAD
=======
<<<<<<< HEAD

    private vertex initialize_single_source() {
        vertex result = new vertex(0, 0);
        result.setCost(0.);
        return result;
    }

    /**
     * used for analyzing a vertex
     * @param s1
     * @param ISA
     * @param weight
     */
    public void analyzeVertex(vertex s1, int ISA, int weight){
        FLandWP s1FLandWP = s1.getFLandWP();
        //System.out.println( "Current FL: " + s1FLandWP.getFL() + " and WP: " + s1FLandWP.getWP() );
        int max = p.largestClimbAdjacent(climbData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weight);
        int min = p.largestDescentAdjacent(descentData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weight);

        if (max > 490) {
            max = 490;
        }
        if (min < 10) {
            min = 10;
        }

        max = max / 10;
        min = min / 10;

        //System.out.println("max:    "+max+"       min:   "+min);
        Integer nrOfVertices = (max - min);

        if ( s1.getFLandWP().getWP() == coordinates.size()-2 ) {
            lastInsert( s1, weight );
        } else {
            Integer counter = min;
            while( counter <= nrOfVertices ){
                notLastInsert( s1 , counter, weight );
                counter++;
            }
        }
    }

    /**
     * used for deciding whether or not to insert a vertex that is the last into the queue
     * @param s1
     * @param weight
     */
    public void lastInsert( vertex s1 , int weight ){
        vertex q = new vertex(0,s1.getFLandWP().getWP()+1);
        q.setPredecessor(s1);
        vertex y = heap.search(q.getFLandWP());

        coordinate c1 = coordinates.get( s1.getFLandWP().getWP() );
        coordinate c2 = coordinates.get( q.getFLandWP().getWP() );

        if( p.largestDescentAdjacent(descentData, c1, c2, s1.getFLandWP().getFL(), weight, 0) == 0 ){ //checks if FL 0 can be reached from s1
            Double cCost = p.priceDescent( s1, q, 0, 5, descentData, cruiseData, coordinates );
            q.setCost( cCost + s1.getCost() );
            if ( y == null ) {
                heap.insert(q);
            } else {
                if (y.getCost() > q.getCost()) {
                    //If new vertex has lower cost, remove the old vertex and insert the new vertex with the lower cost
                    heap.remove(y.getFLandWP());
                    heap.insert(q);
                }
            }
        }
    }

    /**
     * used for deciding whether or not to insert a vertex that is not the last into the queue
     * @param s1
     */
    public void notLastInsert( vertex s1 , int FL , int weight ){
        vertex q = new vertex(FL, s1.getFLandWP().getWP()+1);
        if( s1.getFLandWP().getFL() > q.getFLandWP().getFL() ){
            double cost = p.priceDescent( s1, q, 0 , weight, descentData, cruiseData, coordinates);
            q.setCost( s1.getCost() + cost );
        }else{
            double cost = p.priceClimb( s1, q, 0, weight, climbData, cruiseData, coordinates );
            q.setCost( s1.getCost() + cost );
        }
        q.setPredecessor( s1 );
        vertex u = heap.search(q.getFLandWP());
        if(u == null){
            heap.insert(q);
        }else{
            if( q.getCost() < u.getCost() ){
                heap.remove( u.getFLandWP() );
                heap.insert( q );
            }
        }
    }

    /**
     * dijkstra's algorithm
     */
=======
    private void relax(vertex u, vertex v, Integer w){

        if (v.getCost() > (u.getCost()) + w){
            v.setCost(u.getCost() + w);
            v.setPredecessor(u);
        }

    }
>>>>>>> origin/master

    private vertex initialize_single_source() {
        vertex result = new vertex(0,0);
        result.setWeight(33000);
        result.setCost(0.);
        return result;
    }

    /**
     * used for analyzing a vertex
     * @param s1
     * @param ISA
     * @param weight
     */
    public void analyzeVertex(vertex s1, int ISA){
        FLandWP s1FLandWP = s1.getFLandWP();
        int weightIndex = (int) Math.floor((s1.getWeight()-33000)/4000);
        //System.out.println( "Current FL: " + s1FLandWP.getFL() + " and WP: " + s1FLandWP.getWP() );
        int max = p.largestClimbAdjacent(climbData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weightIndex);
        int min = p.largestDescentAdjacent(descentData, coordinates.get(s1FLandWP.getWP()), coordinates.get(s1FLandWP.getWP()+1), s1FLandWP.getFL(), ISA, weightIndex);

        if (max > 490) {
            max = 490;
        }
        if (min < 10) {
            min = 10;
        }

        max = max / 10;
        min = min / 10;

        //System.out.println("max:    "+max+"       min:   "+min);
        Integer nrOfVertices = (max - min);

        if ( s1.getFLandWP().getWP() == coordinates.size()-2 ) {
            lastInsert( s1 );
        } else {
            Integer counter = min;
            while( counter <= nrOfVertices ){
                notLastInsert( s1 , counter );
                counter++;
            }
        }
    }

    /**
     * used for deciding whether or not to insert a vertex that is the last into the queue
     * @param s1
     * @param weight
     */
    public void lastInsert( vertex s1 ){
        vertex q = new vertex(0,s1.getFLandWP().getWP()+1);
        q.setPredecessor(s1);
        vertex y = heap.search(q.getFLandWP());

        coordinate c1 = coordinates.get( s1.getFLandWP().getWP() );
        coordinate c2 = coordinates.get( q.getFLandWP().getWP() );

        int weightIndex = (int) Math.ceil((s1.getWeight()-33000)/4000);

        if(weightIndex < 0){
            weightIndex = 0;
        }

        if( p.largestDescentAdjacent(descentData, c1, c2, s1.getFLandWP().getFL(), s1.getWeight(), 0) == 0 ){ //checks if FL 0 can be reached from s1
            Double cCost = p.priceDescent( s1, q, 0, weightIndex, descentData, cruiseData, coordinates );
            q.setCost( cCost + s1.getCost() );

            int deltaWeightIndex = (int) Math.ceil((s1.getWeight() - 33000)/4000);

            double deltaWeight = p.fuelAdjacentClimb( climbData, cruiseData, c1, c2, s1.getFLandWP().getFL(), q.getFLandWP().getFL(), 0, deltaWeightIndex);

            Integer qWeight = (int)(s1.getWeight() - deltaWeight);

            if(qWeight < 0){        //if the plane can't lose any more weight
                return;
            }

            q.setWeight( qWeight );

            if ( y == null ) {
                heap.insert(q);
            } else {
                if (y.getCost() > q.getCost()) {
                    //If new vertex has lower cost, remove the old vertex and insert the new vertex with the lower cost
                    heap.remove(y.getFLandWP());
                    heap.insert(q);
                }
            }
        }
    }

    /**
     * used for deciding whether or not to insert a vertex that is not the last into the queue
     * @param s1
     * @param min
     */
    public void notLastInsert( vertex s1 , int FL ){


        int weightIndex = (int) Math.ceil((s1.getWeight() - 33000)/4000);
        if(weightIndex < 0){
            weightIndex = 0;
        }
        vertex q = new vertex(FL, s1.getFLandWP().getWP()+1);
        if( s1.getFLandWP().getFL() > q.getFLandWP().getFL() ){

            coordinate c1 = coordinates.get( s1.getFLandWP().getWP() );
            coordinate c2 = coordinates.get( s1.getFLandWP().getWP() );

            double cost = p.priceDescent( s1, q, 0 , weightIndex, descentData, cruiseData, coordinates);
            q.setCost( s1.getCost() + cost );

            double deltaWeight = p.fuelAdjacentDescent( descentData, cruiseData, c1, c2, s1.getFLandWP().getFL(), q.getFLandWP().getFL(), 0, weightIndex);

            Integer qWeight = (int)(s1.getWeight() - deltaWeight);

            if(qWeight < 0){        //if the plane can't lose any more weight
                return;
            }

            q.setWeight( qWeight );

        }else{


            coordinate c1 = coordinates.get( s1.getFLandWP().getWP() );
            coordinate c2 = coordinates.get( s1.getFLandWP().getWP() );

            double cost = p.priceClimb( s1, q, 0, weightIndex, climbData, cruiseData, coordinates );
            q.setCost( s1.getCost() + cost );

            double deltaWeight = p.fuelAdjacentClimb( climbData, cruiseData, c1, c2, s1.getFLandWP().getFL(), q.getFLandWP().getFL(), 0, weightIndex);

            Integer qWeight = (int)(s1.getWeight() - deltaWeight);

            if(qWeight < 0){        //if the plane can't lose any more weight
                return;
            }

            q.setWeight( qWeight );

        }
        q.setPredecessor( s1 );
        vertex u = heap.search(q.getFLandWP());
        if(u == null){
            heap.insert(q);
        }else{
            if( q.getCost() < u.getCost() ){
                heap.remove( u.getFLandWP() );
                heap.insert( q );
            }
        }
    }

<<<<<<< HEAD
    /**
     * dijkstra's algorithm
     */
=======
>>>>>>> origin/master
>>>>>>> origin/master
    public void Dijkstra_algorithm() {
        /* Our vertex in in the first WP(Start) */
        vertex s = initialize_single_source();
        System.out.println( "Start FL: " + s.getFLandWP().getFL() + " and WP: " + s.getFLandWP().getWP() );

        /* Our vertex in in the last WP(End) */
        vertex lastWP = new vertex(0, coordinates.size()-1);
        System.out.println( "End FL: " + lastWP.getFLandWP().getFL() + " and WP: " + lastWP.getFLandWP().getWP() );

        /* Manually inserting our last WP into our minHeap, so we have a reference to the last WP */
        heap.insert(lastWP);
        analyzeVertex(s,0);

<<<<<<< HEAD
        vertex lastVertex = null;
        vertex u;
=======
<<<<<<< HEAD
        vertex lastVertex = null;
        vertex u;
        while ( !heap.empty() ) {
            u = heap.extractMin();
            if ( u.getFLandWP().getWP() == lastWP.getFLandWP().getWP() ) {
                lastVertex = u;
                heap.removeAllAbove( u.getCost() );
            } else analyzeVertex(u,0,5);
        }
        System.out.println( "This is the last vertex - FL:" + lastVertex.getFLandWP().getFL() + " and WP: " + lastVertex.getFLandWP().getWP() + " and the final cost was: " + lastVertex.getCost() );
        backtracking(lastVertex);
    }


    private void backtracking (vertex v) {
        vertex pi = v;
        while ( pi != null ) {
            System.out.println( "FL: " + pi.getFLandWP().getFL() + " and WP: " + pi.getFLandWP().getWP() + " and current cost: " + pi.getCost() );
            pi = pi.getPredecessor();
        }

=======
        Double currentFinalCost = Double.POSITIVE_INFINITY;
        vertex finalVertex = new vertex(0,0);
>>>>>>> origin/master
        while ( !heap.empty() ) {
            u = heap.extractMin();
            if ( u.getFLandWP().getWP() == lastWP.getFLandWP().getWP() ) {
                lastVertex = u;
                heap.removeAllAbove( u.getCost() );
            } else analyzeVertex(u,0);
        }
        System.out.println( "This is the last vertex - FL:" + lastVertex.getFLandWP().getFL() + " and WP: " + lastVertex.getFLandWP().getWP() + " and the final cost was: " + lastVertex.getCost() );
        backtracking(lastVertex);
    }




    private void backtracking (vertex v) {
        vertex pi = v;
        while ( pi != null ) {
            System.out.println( "FL: " + pi.getFLandWP().getFL() + " and WP: " + pi.getFLandWP().getWP() + " and current cost: " + pi.getCost() );
            pi = pi.getPredecessor();
        }

>>>>>>> origin/master
    }
    /**
     * write method to remove all paths in queue that cost more
     */


}
