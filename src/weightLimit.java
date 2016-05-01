/**
 * Created by Kasper on 27-04-2016.
 */

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * contains the weight limit part of the aircraft performance data
 */
public class weightLimit {


    /**
     * checks if a plane can climb from FL_now to FL_later based on the weight of the plane and current ISA
     * @param file file containing data of weight limits
     * @param FL_to flight level that should be climbed to
     * @param ISA the ISA
     * @return
     */
    public boolean canClimb( File file , int FL_to , int ISA, int weight ){
        try{
            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;

            while((line = br.readLine())!=null){
                if(line.split(";")[0].equals(Integer.toString(FL_to))){
                    if(line.split(";")[1].equals(Integer.toString(ISA))){
                        if(weight <= Integer.parseInt(line.split(";")[2])) {
                            br.close();
                            return true;
                        }
                    }//if [1]
                }//if [0]
            }//while-loop
            br.close();
        }catch(IOException e){
            System.out.println("File not available");
        }
        return false;
    }
}
