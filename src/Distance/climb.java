package Distance; /**
 * Created by Kasper on 28-04-2016.
 */

import Data.climbData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * contains the Distance.climb performance part of the aircraft performance data
 */
public class climb {

    /**
     * returns the distance of a Distance.climb from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the Distance.climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double distanceClimb(climbData[][] array, int FL_from, int FL_to, int ISA, int weight ){
        climbData FL1;
        climbData FL2;

        FL1 = array[FL_from][weight];
        FL2 = array[FL_to][weight];

        return FL2.Distance- FL1.Distance;
    }

    /**
     * returns the estimated time of a Distance.climb from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the Distance.climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double timeClimb( climbData[][] array, int FL_from, int FL_to, int ISA, int weight ) {
        climbData FL1;
        climbData FL2;

        FL1 = array[FL_from][weight];
        FL2 = array[FL_to][weight];

        return FL2.Time- FL1.Time;
    }

    /**
     * returns the fuel consumption of a Distance.climb from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the Distance.climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double fuelClimb( climbData[][] array, int FL_from, int FL_to, int ISA, int weight ){
        climbData FL1;
        climbData FL2;

        FL1 = array[FL_from][weight];
        FL2 = array[FL_to][weight];

        return FL2.Fuel- FL1.Fuel;
    }
}
