package Distance;

import Data.cruiseData;
import Data.dataArrays;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Kasper on 28-04-2016.
 */

/**
 * contains the Data.cruiseData performance part of the aircraft performance data
 */
public class cruisePerformance {

    /**
     * the following three methods are used to calculate values of respectively weight, flight level and ISA to indices
     * @param i
     * @return
     */
    public int weightToIndex(int i){
        int result = (i-33000)/4000;
        return result;
    }

    public int FLToIndex(double i){
        double n = i*0.1;
        return( (int) n );
    }

    public int ISAToIndex(int i) {
        return (10 + i) / 5;
    }

    /**
     * returns the fuel consumption per hour based on flight level, ISA and weight of the aircraft
     * @param FL the flight level
     * @param ISA ISA
     * @param weight weight of the plane
     * @return
     */
    public double fuelCruise(cruiseData[][] array, int FL, int ISA, int weight ){
        //int FL_index = FLToIndex(FL);
        //int weight_index = weightToIndex(weight);
        return array[FL][weight].FuelFlow;
    }

    /**
     * returns the ideal speed based on flight level, ISA and weight of the aircraft
     * @param FL the flight level
     * @param ISA ISA
     * @param weight weight of the plane
     * @return
     */
    public double idealCruiseSpeed( cruiseData[][] array, int FL, int ISA, int weight ){
        //int FL_index = FLToIndex(FL);
        //int weight_index = weightToIndex(weight);
        return array[FL][weight].Speed;
    }
}
