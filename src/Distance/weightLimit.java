package Distance; /**
 * Created by Kasper on 27-04-2016.
 */

import Data.weightLimitsData;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * contains the weight limit part of the aircraft performance data
 */
public class weightLimit {

    /**
     * used for converting flight levels to indices
     * @param i
     * @return
     */
    public int FLToIndex(double i){
        double n = i*0.1;
        return( (int) n );
    }


    /**
     * checks if a plane can Distance.climb from FL_now to FL_later based on the weight of the plane and current ISA
     * @param file file containing data of weight limits
     * @param FL_to flight level that should be climbed to
     * @param ISA the ISA
     * @return
     */
    public boolean canClimb(weightLimitsData[] array , int FL_to , int ISA, int weight ){
        int FL_index = FLToIndex(FL_to);
        if(weight<array[FL_index].weight){
            return true;
        }
        return false;
    }
}
