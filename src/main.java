import Data.*;
import Dijkstra.*;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGund on 4/24/2016.
 */

public class main {

    public static void main(String[] args) {

        // Kasper's paths
        //Path input = Paths.get( "C:\\Users\\Kasper\\flight paths\\Data\\ACP\\Falcon 7x" );
        //Path output = Paths.get( "C:\\Users\\Kasper\\flight paths\\Data\\" );

        // Mathias' paths
        Path input = Paths.get( "D:\\Documents 2\\Data" );
        Path output = Paths.get( "C:\\Users\\MGund\\Documents\\GitHub\\Program\\Data\\" );

        // Daniel's paths
        //Path input = Paths.get( "C:\\Users\\Danie\\OneDrive\\Dokumenter\\Data\\Data\\ACP\\Falcon 7x" );
        //Path output = Paths.get( "C:\\Users\\Danie\\Documents\\GitHub\\Program\\Data\\" );

        //Tim's Paths
        //Path input = Paths.get( "C:\\Users\\Kasper\\Documents\\GitHub\\Program\\Program\\Data\\ACP\\Falcon 7x" );
        //Path output = Paths.get( "C:\\Users\\Kasper\\Documents\\GitHub\\Program\\Program\\Data");

        ser ez = new ser(output);

        // Behøver kun serialize 1 gang, med mindre der er sket ændringer i data'erne
        //ez.serialize(input);


        // Ligger info fra vores forskellige .ser filer ind i files
        climbData[][] climbArray = (climbData[][]) ez.deserializeData( "climb.csv" );
        descentData[][] descentArray = (descentData[][]) ez.deserializeData( "descent.csv" );
        cruiseData[][] cruiseArray = (cruiseData[][]) ez.deserializeData( "cruise.csv" );
        weightLimitsData[] weightlimitsArray = (weightLimitsData[]) ez.deserializeWeight( "weightlimits.csv" );
        List EBBRESSA138Array = (List) ez.deserializeRoute( "EBBR-ESSA-138.txt" );

        File route1 = new File( "C:\\Users\\MGund\\Documents\\GitHub\\Program\\Data\\Route\\EBBR-ESSA-138.txt" );

        Dijkstra dijkstra = new Dijkstra(route1,100, climbArray, descentArray, cruiseArray);

        dijkstra.Dijkstra_algorithm();


    }
}