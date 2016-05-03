import Data.*;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */

public class main {

    public static void main(String[] args) {

        //Kasper's paths
        //Path input = Paths.get( "C:\\Users\\Kasper\\flight paths\\Data\\ACP\\Falcon 7x" );
        //Path output = Paths.get( "C:\\Users\\Kasper\\flight paths\\Data\\" );

        //Mathias' paths
        Path input = Paths.get( "D:\\Documents 2\\Data" );
        Path output = Paths.get( "C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Førsteårsprojekt\\Program\\Data\\" );

        ser ez = new ser(output);

        //Behøver kun serialize 1 gang, med mindre der er sket ændringer i data'erne
        //ez.serialize(input);


        //Ligger info fra vores forskellige .ser filer ind i files
        File climbSer = ez.deserialize( "climb.csv" );
        File cruiseSer = ez.deserialize( "cruise.csv" );
        File descentSer = ez.deserialize( "descent.csv" );
        File weightlimitsSer = ez.deserialize( "weightlimits.csv" );
        //File EBBRESSA138 = ez.deserialize( "EBBR-ESSA-138.txt" );


        dataArrays d = new dataArrays();

        cruiseData[][] cruiseList = d.cruiseFill(cruiseSer);

        System.out.println( "Cruise[0][0]: " + cruiseList[0][0].FuelFlow );

        climbData[][] climbList = d.climbFill(climbSer);

        System.out.println( "Climb[1][0]: " + climbList[1][0].Fuel );

        descentData[][] descentList = d.descentFill(descentSer);

        System.out.println( "Descent[1][0]: " + descentList[1][0].Fuel );

        weightLimitsData[] weightLimitList = d.weightLimitsFill(weightlimitsSer);

        System.out.println( "Weightlimit[0]: " + weightLimitList[0].weight );


        
    }
}