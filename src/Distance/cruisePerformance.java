package Distance;

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
     * returns the fuel consumption per hour based on flight level, ISA and weight of the aircraft
     * @param file the file with Data.cruiseData performance data
     * @param FL the flight level
     * @param ISA ISA
     * @param weight weight of the plane
     * @return
     */
    public double fuelCruise( File file, int FL, int ISA, int weight ) throws Exception {
        try{
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            String line;

            while( (line = br.readLine()) != null ){
                if( line.split(";")[0].equals(Integer.toString(FL)) ){
                    if( line.split(";")[1].equals(Integer.toString(ISA)) ){
                        if( line.split(";")[2].equals(Integer.toString(weight)) ) {
                            System.out.println(line.split(";")[2]);
                            br.close();
                            return Double.parseDouble(line.split(";")[3]); //needs to be converted to gallons
                        }
                    }
                }
            }
        }catch( IOException e ){}
        throw new Exception(){};
    }

    /**
     * returns the ideal speed based on flight level, ISA and weight of the aircraft
     * @param file the file with Data.cruiseData performance data
     * @param FL the flight level
     * @param ISA ISA
     * @param weight weight of the plane
     * @return
     */
    public double idealCruiseSpeed( File file, int FL, int ISA, int weight ) throws Exception {
        try{
            BufferedReader br = new BufferedReader( new FileReader( file ) );
            String line;

            while( (line = br.readLine()) != null ){
                if( line.split(";")[0].equals(Integer.toString(FL)) ){
                    if( line.split(";")[1].equals(Integer.toString(ISA)) ){
                        if( line.split(";")[2].equals(Integer.toString(weight)) ){
                            br.close();
                            return Double.parseDouble(line.split(";")[4]); //needs to be converted to gallons
                        }
                    }
                }
            }
        }catch( IOException e ){}
        throw new Exception(){};
    }
}
