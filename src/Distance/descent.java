package Distance;

import Data.cruiseData;
import Data.descentData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Kasper on 28-04-2016.
 */

/**
 * contains the Distance.descent performance part of the aircraft performance data
 */
public class descent {

    /**
     * returns the distance of a Distance.descent from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the Distance.climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double distanceDescent( descentData[][] array, int FL_from, int FL_to, int ISA, int weight ){
        descentData FL1;
        descentData FL2;

        FL1 = array[FL_from][weight];
        FL2 = array[FL_to][weight];

        return FL2.Distance-FL1.Distance;
    }

    /**
     * returns the estimated time of a Distance.descent from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the Distance.climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double timeDescent(descentData[][] array, int FL_from, int FL_to, int ISA, int weight ){
        descentData FL1;
        descentData FL2;

        FL1 = array[FL_from][weight];
        FL2 = array[FL_to][weight];

        return FL1.Time-FL2.Time;
    }

    /**
     * returns the fuel consumption of a Distance.descent from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the Distance.climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double fuelDescent( descentData[][] array, int FL_from, int FL_to, int ISA, int weight ){
        descentData FL1;
        descentData FL2;

        FL1 = array[FL_from][weight];
        FL2 = array[FL_from][weight];

        return FL1.Fuel-FL2.Fuel;
    }
}
