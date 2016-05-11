package Distance;

import Data.climbData;
import Data.cruiseData;
import Data.descentData;
import Distance.climb;
import Distance.coordinate;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasper on 25-04-2016.
 */
public class readPaths {


    climb climb = new climb();
    descent descent = new descent();
    cruisePerformance cruise = new cruisePerformance();

    /**
     * returns a list of coordinates
     * @param file a file containing flight path data
     */

    public List<coordinate> waypoints(File file){

        try{
            BufferedReader br = new BufferedReader(new FileReader(file));

            List<coordinate> coordinateList = new ArrayList<>();

            String line;

            while((line = br.readLine())!=null){

                if(line.startsWith("Waypoint")){

                    String[] lineArray = line.split(";");



                    coordinateList.add(
                            new coordinate(
                                    Double.parseDouble( lineArray[1].replace(",",".") ),     //lineArray[1] equals latitude, comma must be replaced by dot
                                    Double.parseDouble( lineArray[2].replace(",",".") )      //lineArray[2] equals longitude, comma must be replaced by dot
                            ) //new Distance.coordinate
                    ); //coordinateList.add
                }// if-statement
            } //while-loop

            return coordinateList;

        }catch(IOException e){e.printStackTrace();}

        return null;
    }

    /**
     *
     * takes two adjacent coordinates and calculates the distance inbetween.
     * uses the haversine formula to calcuate shortest spherical path.
     * a = sin^2*(delta_phi/2) + cos(phi1)*cos(phi2)*sin^2(delta_longitude/2)
     * c = 2*atan2( sqrt(a) , sqrt(1-a) )
     * d = R * c
     *
     * atan2 is a tan function that works 360 degrees instead of just 180
     *
     * @R radius of the earth
     * @d distance
     * @nMile constant used to convert meters to nautical miles
     */

    // lat is latitude, long is longitude
    private double deltaAdjacentWaypoint(coordinate c1, coordinate c2){

        double lat1, lat2;
        double long1, long2;

        lat1 = c1.latitude;
        long1 = c1.longitude;

        lat2 = c2.latitude;
        long2 = c2.longitude;

        double R = 6371000; //metres
        double nMile = 0.000539956803;

        double phi1 = Math.toRadians(lat1);
        double phi2 = Math.toRadians(lat2);

        double deltaLong = Math.toRadians(long2-long1);

        double deltaLat = phi2-phi1;

        double a =  Math.sin(deltaLat/2) * Math.sin(deltaLat/2) +       //sin^2*(delta_phi/2)+
                    Math.cos(phi1) * Math.cos(phi2) *                   //cos(phi1)*cos(phi2)*
                    Math.sin(deltaLong/2) * Math.sin(deltaLong/2);      //sin^2(delta_longitude/2)


        double c = 2 * Math.atan2( Math.sqrt(a), Math.sqrt(1-a) );

        return R * c * nMile;                                                  //d = R * c

    }

    /**
     * returns the distance from a waypoint to another

     * @param c1 waypoint one
     * @param c2 waypoint 2
     * @return
     * @throws Exception if none of the given coordinates exist as waypoints in the specified route
     */
    public double deltaWaypoint(List<coordinate> coordinates, coordinate c1, coordinate c2) throws Exception {

        int index1 = -1;                                        //index for c1
        int index2 = -1;                                        //index for c2

        for(int i = 0; i < coordinates.size(); i++){            //finds first waypoint
            coordinate current = coordinates.get(i);
            if (current.latitude == c1.latitude && current.longitude == c1.longitude){
                index1 = i;
            }
            else if(current.latitude == c2.latitude && current.longitude == c2.longitude){
                index2 = i;
            }
        }

        if(index1 == -1){
            System.out.println("Waypoint 1 does not exist");
            throw new Exception();
        }
        if(index2 == -1){
            System.out.println("Waypoint 2 does not exist");
            throw new Exception();
        }

        coordinate waypoint1 = coordinates.get(index1);
        coordinate waypoint2;


        int i2 = index1 + 1;                                             //index for waypoint2  -----  i2 <= coordinates.size()-1
        double dist = 0;

        while(i2 <= index2){                                    //as long as current waypoint2 does not come after c2 in the flight path
            waypoint2 = coordinates.get(i2);
            dist = dist + deltaAdjacentWaypoint(waypoint1, waypoint2);
            waypoint1 = waypoint2;
            i2++;
        }
        return dist;
    }

    /**
     * calculates and returns the distance of a whole flight path
     * @param coordinates coordinates of a route
     * @return
     */
    public double distanceRoute(List<coordinate> coordinates){

        coordinate waypoint1 = coordinates.get(0);
        coordinate waypoint2;

        int i2 = 1;                                             //index for waypoint2  -----  i2 <= coordinates.size()-1
        double dist = 0;

        while(i2 < coordinates.size()){
            waypoint2 = coordinates.get(i2);
            dist = dist + deltaAdjacentWaypoint(waypoint1, waypoint2);
            waypoint1 = waypoint2;
            i2++;
        }
        return dist;
    }

    /**
     * returns the largest reachable flight level from one Distance.coordinate to another,
     * depending on weight of the plane, ISA and current flight level
     * FL_to is used to find the greatest flight level. this, however, can not be returned
     * since the while-loop stops as soon as distanceClimb for a FL_to is larger than maxDistance
     * therefore, the last checked flight level, FL_max, must be returned
     * @param climbData file containing Distance.climb data
     * @param c1 waypoint number one
     * @param c2 waypoint number two
     * @param FL_from current flight level
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public int largestClimbAdjacent(climbData[][] climbData, coordinate c1, coordinate c2, int FL_from, int ISA, int weight) {

        double maxDistance = deltaAdjacentWaypoint(c1, c2);

        int FL_to = FL_from;

        while( FL_to <= 49 && climb.distanceClimb(climbData, FL_from, FL_to, ISA, weight) <= maxDistance) {
            FL_to++;
        }
        if(FL_to == FL_from){
            return FL_to * 10;
        } else if (FL_to == 50) {
            return (FL_to - 1) * 10;
        }

        return (FL_to - 1) * 10;
    }

    public int largestDescentAdjacent(descentData[][] descentData, coordinate c1, coordinate c2, int FL_from, int ISA, int weight){
        double maxDistance = deltaAdjacentWaypoint(c1, c2);

        int FL_to = FL_from;

        while(FL_to <= 49 && descent.distanceDescent(descentData, FL_from, FL_to, ISA, weight) <= maxDistance) {
                FL_to++;
        }
        if(FL_to == FL_from){
            return FL_to;
        } else if (FL_to == 50) {
            return (FL_to - 1) * 10;
        }

        return (FL_to - 1) * 10;
    }

    /**
     * used by largestClimb. in the return list it's necessary to have a map with a Distance.coordinate and a current flight level
     */
    private class coordinateAndFL{
        coordinate c;
        int FL;

        public coordinateAndFL(coordinate c, int FL){
            this.c = c;
            this.FL = FL;
        }
    }

    /**
     * if a plane wants to Distance.climb it might not be able to make it between just two waypoints.
     * this method returns a list of flight levels at coordinates.
     * in other words, gives a climbing route for the plane to reach a destined flight level.
     * @param coordinates the coordinates of a route
     * @param climbData
     * @param c1
     * @param FL_from
     * @param FL_to
     * @param ISA
     * @param weight
     * @return
     * @throws Exception
     */
    public List<coordinateAndFL> climbRoute(List<coordinate> coordinates, climbData[][] climbData, coordinate c1, int FL_from, int FL_to, int ISA, int weight) {
        List<coordinateAndFL> result = new ArrayList<>();

        int index1 = -1;                                        //index for c1
        int index2;                                             //index for c2

        for(int i = 0; i < coordinates.size(); i++){            //finds first waypoint
            coordinate current = coordinates.get(i);
            if (current.latitude == c1.latitude && current.longitude == c1.longitude){
                index1 = i;
            }
        }

        if(index1 == -1){
            System.out.println("Waypoint 1 does not exist");
            return null;
        }

        index2 = index1 + 1;        //ok to assume that index1 is not that last index since this would mean the last waypoint, therefore also the landing point

        coordinate waypoint1 = coordinates.get(index1);
        coordinate waypoint2 = coordinates.get(index2);

        //if FL_to can be reached by flying from waypoint1 to the adjacent waypoint2
        if ( FL_to <= largestClimbAdjacent(climbData, waypoint1, waypoint2, FL_from, ISA, weight) ){
            result.add( new coordinateAndFL( waypoint2, FL_to ) );
            return result;
        }

        int current_FL;     //used to hold the greatest reachable flight level of a waypoint

        //else plan a flight route
        //adds every waypoint but the last
        while( FL_to > (current_FL = largestClimbAdjacent(climbData, waypoint1, waypoint2, FL_from, ISA, weight) ) ){
            result.add( new coordinateAndFL( waypoint2, current_FL ) );

            index1++;
            index2++;

            //in case that there are no more coordinates left
            try{
                waypoint1 = coordinates.get(index1);
                waypoint2 = coordinates.get(index2);
            }catch(ArrayIndexOutOfBoundsException e){System.out.println("Flight level can not be reached");}

        }

        //adds the last waypoint
        waypoint2 = coordinates.get(index2 + 1);
        result.add( new coordinateAndFL( waypoint2, current_FL ) );

        return result;
    }

    /**
     * calculates and returns the fuel needed for climbing between two waypoints. only used by the method "fuelClimbCruise".
     * @param climbData climb data
     * @param cruiseData cruise data
     * @param c1 coordinate number 1
     * @param c2 coordinate number 2
     * @param FL_from flight level that is being climbed from
     * @param FL_to flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double fuelAdjacentClimb(climbData[][] climbData, cruiseData[][] cruiseData, coordinate c1, coordinate c2, int FL_from, int FL_to, int ISA, int weight){
        double climbFuel = climb.fuelClimb(climbData, FL_from, FL_to, ISA, weight);
        double climbDistance = climb.distanceClimb(climbData, FL_from, FL_to, ISA, weight);
        double cruiseFuel = cruise.fuelCruise(cruiseData, FL_to, ISA, weight);
        double cruiseSpeed = cruise.idealCruiseSpeed(cruiseData, FL_to, ISA, weight);

        double distance = deltaAdjacentWaypoint(c1, c2);
        double deltaDistance = distance - climbDistance;

        double cruiseTime = deltaDistance/cruiseSpeed;

        return cruiseTime*cruiseFuel+climbFuel;
    }


    /**
     * calculates and returns the fuel needed for climbing from a flight level to another at a given starting coordinate
     * @param route file containing the route data
     * @param climbData climb data
     * @param cruiseData cruise data
     * @param c1 starting coordinate
     * @param FL_from flight level that is being climbed from
     * @param FL_to flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight
     * @return
     */

    public double fuelClimbCruise(List<coordinate> route, climbData[][] climbData, cruiseData[][] cruiseData, coordinate c1, int FL_from, int FL_to, int ISA, int weight){
        List<coordinateAndFL> path = climbRoute(route, climbData, c1, FL_from, FL_to, ISA, weight);
        double result = 0;

        int curr_FL_from = FL_from;
        int curr_FL_to;

        coordinate curr_c1 = c1;
        coordinate curr_c2;

        for(coordinateAndFL c : path){
            curr_c2 = c.c;
            curr_FL_to = c.FL;

            result = result + fuelAdjacentClimb(climbData, cruiseData, curr_c1, curr_c2, curr_FL_from, curr_FL_to, ISA, weight);

            curr_c1 = curr_c2;
            curr_FL_from = curr_FL_to;
        }

        return result;
    }

    /**
     * returns the time that it takes to climb between two coordinates
     * @param climbData climb data
     * @param cruiseData cruise data
     * @param c1 starting coordinate
     * @param c2 ending coordinate
     * @param FL_from flight level that is being climbed from
     * @param FL_to flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight
     * @return
     */
    private double timeAdjacentClimb(climbData[][] climbData, cruiseData[][] cruiseData, coordinate c1, coordinate c2, int FL_from, int FL_to, int ISA, int weight){

        double climbTime = climb.timeClimb(climbData, FL_from, FL_to, ISA, weight);
        double climbDistance = climb.distanceClimb(climbData, FL_from, FL_to, ISA, weight);
        double cruiseSpeed = cruise.idealCruiseSpeed(cruiseData, FL_to, ISA, weight);

        double distance = deltaAdjacentWaypoint(c1, c2);
        double deltaDistance = distance - climbDistance;

        double cruiseTime = deltaDistance/cruiseSpeed;

        return climbTime + cruiseTime;


    }

    /**
     * calculates and returns the time spent climbing from a flight level to another at a given starting coordinate
     * @param route the file containing data of the route
     * @param climbData climb data
     * @param cruiseData cruise data
     * @param c1 starting coordinate
     * @param FL_from flight level that is being climbed from
     * @param FL_to flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight
     * @return
     */
    public double timeClimbCruise(List<coordinate> route, climbData[][] climbData, cruiseData[][] cruiseData, coordinate c1, int FL_from, int FL_to, int ISA, int weight){
        List<coordinateAndFL> path = climbRoute(route, climbData, c1, FL_from, FL_to, ISA, weight);
        double result = 0;

        int curr_FL_from = FL_from;
        int curr_FL_to;

        coordinate curr_c1 = c1;
        coordinate curr_c2;

        for(coordinateAndFL c : path){
            curr_c2 = c.c;
            curr_FL_to = c.FL;

            result = result + timeAdjacentClimb(climbData, cruiseData, curr_c1, curr_c2, curr_FL_from, curr_FL_to, ISA, weight);

            curr_c1 = curr_c2;
            curr_FL_from = curr_FL_to;
        }

        return result;
    }



    /**
     * TO DO ---------------------------------------------------------------------------------
     * make the same methods for descending
     */
}

