import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Created by Kasper on 28-04-2016.
 */

/**
 * contains the descent performance part of the aircraft performance data
 */
public class descent {
    /**
     * returns the distance of the descent from the flight level FL to 0
     * @param file file containing data about descent performance
     * @param FL the flight leven
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double distanceDescentFromZero(File file, int FL, int ISA, int weight ){
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
     * returns the estimated time of the descent from the flight level FL to 0
     * @param file file containing data about descent performance
     * @param FL the flight leven
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double timeDescentFromZero( File file, int FL, int ISA, int weight ){
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
     * returns the fuel consumption of the descent from the flight level FL to 0
     * @param file file containing data about descent performance
     * @param FL the flight leven
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    private double fuelDescentFromZero( File file, int FL, int ISA, int weight ){
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
     * returns the distance of a descent from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double distanceDescent( File file, int FL_from, int FL_to, int ISA, int weight ) throws Exception {
        double FL1;
        double FL2;

        if((FL1 = distanceDescentFromZero(file, FL_from, ISA, weight)) == -1){
            throw new Exception(){};
        }
        else if ((FL2 = distanceDescentFromZero(file, FL_to, ISA, weight)) == -1){
            throw new Exception(){};
        }

        return FL1-FL2;
    }

    /**
     * returns the estimated time of a descent from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double timeDescent( File file, int FL_from, int FL_to, int ISA, int weight ) throws Exception {
        double FL1;
        double FL2;

        if((FL1 = timeDescentFromZero(file, FL_from, ISA, weight)) == -1){
            throw new Exception(){};
        }
        else if ((FL2 = timeDescentFromZero(file, FL_to, ISA, weight)) == -1){
            throw new Exception(){};
        }

        return FL1-FL2;
    }

    /**
     * returns the fuel consumption of a descent from a given flight level to another.
     * calculated as the difference between the two flight levels
     * @param file file containing data of the climb performance
     * @param FL_from the flight level that is being climbed from
     * @param FL_to the flight level that is being climbed to
     * @param ISA ISA
     * @param weight weight of the aircraft
     * @return
     */
    public double fuelDescent( File file, int FL_from, int FL_to, int ISA, int weight ) throws Exception {
        double FL1;
        double FL2;

        if((FL1 = fuelDescentFromZero(file, FL_from, ISA, weight)) == -1){
            throw new Exception();
        }
        else if ((FL2 = fuelDescentFromZero(file, FL_to, ISA, weight)) == -1){
            throw new Exception();
        }

        return FL1-FL2;
    }
}
