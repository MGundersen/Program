package Data;

import Data.climbData;
import Data.cruiseData;
import Data.descentData;
import Data.weightLimitsData;

import java.io.*;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kasper on 29-04-2016.
 */
public class dataArrays {



    private cruiseData[][] cruiseArray(File cruiseFile){


        List<Integer> FL = new ArrayList<>();
        List<Integer> ISA = new ArrayList<>();
        List<Integer> weight = new ArrayList<>();

        try{
            BufferedReader br = new BufferedReader(new FileReader(cruiseFile));

            String line;

            String[] curr_line;

            int lineCounter = 0;

            while((line = br.readLine()) != null ) {
                if (lineCounter > 3) {
                    curr_line = line.split(";");

                    if (!FL.contains(curr_line[0])) {
                        FL.add(Integer.parseInt(curr_line[0]));
                    }


                    if (!FL.contains(curr_line[1])) {
                        ISA.add(Integer.parseInt(curr_line[1]));
                    }

                    if (!FL.contains(curr_line[2])) {
                        weight.add(Integer.parseInt(curr_line[2]));
                    }
                }
                lineCounter++;
            }

            int counter0 = FL.size();
            int counter1 = ISA.size();
            int counter2 = weight.size();

            cruiseData[][] result = new cruiseData[counter0][counter2];

            return result;

        }catch(IOException e){e.printStackTrace();}

        return null;
    }

    private int weightToIndex(int i){
        int result = (i-33000)/4000;
        return result;
    }

    private int FLToIndex(double i){
        double n = i*0.1;
        return( (int) n );
    }

    private int ISAToIndex(int i) {
        return (10 + i) / 5;
    }

    private int weightFact(int i){
        return 33000 + i*4000;
    }

    public cruiseData[][] cruiseFill(File cruise){
        cruiseData[][] result = cruiseArray(cruise);

        try{
            BufferedReader br = new BufferedReader(new FileReader(cruise));
            String line;
            String[] curr_line;

            int indexFL;
            int indexWeight;

            double fuelFlow;
            double speed;

            int lineCounter = 0;
            while((line = br.readLine())!=null) {
                if (lineCounter > 3) {
                    curr_line = line.split(";");

                    if (curr_line[1].equals("0")) {
                        indexFL = FLToIndex(Integer.parseInt(curr_line[0]));
                        indexWeight = weightToIndex(Integer.parseInt(curr_line[2]));

                        fuelFlow = Double.parseDouble(curr_line[3]);
                        speed = Double.parseDouble(curr_line[4]);
                        //System.out.println("adding: " + fuelFlow + "   ----   " + speed + "   to index" + indexFL + " " + indexWeight);
                        result[indexFL][indexWeight] = new cruiseData(fuelFlow, speed);
                    }
                }
                lineCounter++;
            }
        }catch(IOException e){e.printStackTrace();}

        return result;

    }

    private climbData[][] climbArray(File cruiseFile) {

        try {
            List<String> lines = Files.readAllLines( cruiseFile.toPath() );
            int fl = lines.get(0).split(";").length;
            //int isa = lines.get(1).split(";").length;
            int weight = lines.get(2).split(";").length;

            climbData[][] result = new climbData[fl][weight];

            return result;

        } catch (IOException e) {e.printStackTrace();}

        return null;
    }

    public climbData[][] climbFill(File cruise){

        climbData[][] result = climbArray(cruise);

        try{
            BufferedReader br = new BufferedReader(new FileReader(cruise));
            String line;
            String[] curr_line;

            int indexFL;
            int ISA;
            int indexWeight;

            double Distance;
            double Time;
            double Fuel;

            int lineCounter = 0;
            while((line = br.readLine())!=null) {
                if (lineCounter > 3) {
                    curr_line = line.split(";");

                        indexFL = FLToIndex(Integer.parseInt(curr_line[0]));
                        ISA = ISAToIndex(Integer.parseInt(curr_line[1]));
                        indexWeight = weightToIndex(Integer.parseInt(curr_line[2]));

                        Distance = Double.parseDouble(curr_line[3]);
                        Time = Double.parseDouble(curr_line[4]);
                        Fuel = Double.parseDouble(curr_line[5]);
                        result[indexFL][indexWeight] = new climbData(Distance, Time, Fuel);

                }
                lineCounter++;
            }
            return result;
        }catch(IOException e){e.printStackTrace();}

        return  null;
    }

    private descentData[][] descentArray(File cruiseFile) {

        try {
            List<String> lines = Files.readAllLines( cruiseFile.toPath() );
            int fl = lines.get(0).split(";").length;
            //int isa = lines.get(1).split(";").length;
            int weight = lines.get(2).split(";").length;

            descentData[][] result = new descentData[fl][weight];

            return result;

        } catch (IOException e) {e.printStackTrace();}

        return null;
    }

    public descentData[][] descentFill(File cruise){

        descentData[][] result = descentArray(cruise);

        try{
            BufferedReader br = new BufferedReader(new FileReader(cruise));
            String line;
            String[] curr_line;

            int indexFL;
            int indexWeight;

            double Distance;
            double Time;
            double Fuel;

            int lineCounter = 0;
            while((line = br.readLine())!=null) {
                if (lineCounter > 3) {
                    curr_line = line.split(";");

                    if (curr_line[1].equals("0")) {
                        indexFL = FLToIndex(Integer.parseInt(curr_line[0]));
                        indexWeight = weightToIndex(Integer.parseInt(curr_line[2]));

                        Distance = Double.parseDouble(curr_line[3]);
                        Time = Double.parseDouble(curr_line[4]);
                        Fuel = Double.parseDouble(curr_line[5]);
                        result[indexFL][indexWeight] = new descentData(Distance, Time, Fuel);
                    }
                }
                lineCounter++;
            }
            return result;
        }catch(IOException e){e.printStackTrace();}

        return  null;
    }

    private weightLimitsData[] weightLimitsArray(File cruiseFile) {

        try {
            List<String> lines = Files.readAllLines( cruiseFile.toPath() );
            int fl = lines.get(0).split(";").length;
            //int isa = lines.get(1).split(";").length;
            //int weight = lines.get(2).split(";").length;

            weightLimitsData[] result = new weightLimitsData[fl];

            return result;

        } catch (IOException e) {e.printStackTrace();}

        return null;
    }

    public weightLimitsData[] weightLimitsFill(File cruise){

        weightLimitsData[] result = weightLimitsArray(cruise);

        try{
            BufferedReader br = new BufferedReader(new FileReader(cruise));
            String line;
            String[] curr_line;

            int indexFL;

            double weightLimit;

            int lineCounter = 0;
            while((line = br.readLine())!=null) {
                if (lineCounter > 3) {
                    curr_line = line.split(";");

                    if (curr_line[1].equals("0")) {
                        indexFL = FLToIndex(Integer.parseInt(curr_line[0]));

                        weightLimit = Double.parseDouble(curr_line[2]);
                        result[indexFL] = new weightLimitsData(weightLimit);
                    }
                }
                lineCounter++;
            }
            return result;
        }catch(IOException e){e.printStackTrace();}

        return  null;
    }

}
