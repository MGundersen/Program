/**
 * Created by Kasper on 28-04-2016.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * contains the climb performance part of the aircraft performance data
 */
public class climb {

    /**
     * returns the distance of the climb from 0 to the flight level FL
     * @param file file containing data about climb performance
     * @param FL the flight leven
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double distanceClimbFromZero( File file, int FL, int ISA, int weight ){
        try{
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            String line;

            while((line = br.readLine()) != null){
                if(line.split(";")[0].equals(Integer.toString(FL))){
                    if(line.split(";")[1].equals(Integer.toString(ISA))){
                        if(line.split(";")[2].equals(Integer.toString(weight))){
                            br.close();
                            return Double.parseDouble(line.split(";")[3]);
                        }
                    }
                }
            }
        }catch(IOException e){}

        return -1;
    }

    /**
     * returns the estimated time of the climb from 0 to the flight level FL
     * @param file file containing data about climb performance
     * @param FL the flight leven
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double timeClimbFromZero( File file, int FL, int ISA, int weight ){
        try{
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            String line;

            while((line = br.readLine()) != null){
                if(line.split(";")[0].equals(Integer.toString(FL))){
                    if(line.split(";")[1].equals(Integer.toString(ISA))){
                        if(line.split(";")[2].equals(Integer.toString(weight))){
                            br.close();
                            return Double.parseDouble(line.split(";")[4]);
                        }
                    }
                }
            }
        }catch(IOException e){}

        return -1;
    }

    /**
     * returns the fuel consumption of the climb from 0 to the flight level FL
     * @param file file containing data about climb performance
     * @param FL the flight leven
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double fuelClimbFromZero( File file, int FL, int ISA, int weight ){
        try{
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            String line;

            while((line = br.readLine()) != null){
                if(line.split(";")[0].equals(Integer.toString(FL))){
                    if(line.split(";")[1].equals(Integer.toString(ISA))){
                        if(line.split(";")[2].equals(Integer.toString(weight))){
                            br.close();
                            return Double.parseDouble(line.split(";")[5]);
                        }
                    }
                }
            }
        }catch(IOException e){}

        return -1;
    }

    /**
     * returns the distance of a climb from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double distanceClimb( File file, int FL_from, int FL_to, int ISA, int weight ) throws Exception {
        double FL1;
        double FL2;

        if((FL1 = distanceClimbFromZero(file, FL_from, ISA, weight)) == -1){
            throw new Exception(){};
        }
        else if ((FL2 = distanceClimbFromZero(file, FL_to, ISA, weight)) == -1){
            throw new Exception(){};
        }

        return FL2-FL1;
    }

    /**
     * returns the estimated time of a climb from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double timeClimb( File file, int FL_from, int FL_to, int ISA, int weight ) throws Exception {
        double FL1;
        double FL2;

        if((FL1 = timeClimbFromZero(file, FL_from, ISA, weight)) == -1){
            throw new Exception(){};
        }
        else if ((FL2 = timeClimbFromZero(file, FL_to, ISA, weight)) == -1){
            throw new Exception(){};
        }

        return FL2-FL1;
    }

    /**
     * returns the fuel consumption of a climb from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double fuelClimb( File file, int FL_from, int FL_to, int ISA, int weight ) throws Exception {
        double FL1;
        double FL2;

        if((FL1 = fuelClimbFromZero(file, FL_from, ISA, weight)) == -1){
            throw new Exception(){};
        }
        else if ((FL2 = fuelClimbFromZero(file, FL_to, ISA, weight)) == -1){
            throw new Exception(){};
        }

        return FL2-FL1;
    }
}
