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
        //Path input = Paths.get( "D:\\Documents 2\\Data" );
        //Path output = Paths.get( "C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Førsteårsprojekt\\Program\\Data\\" );

        // Daniel's paths
        //Path input = Paths.get( "C:\\Users\\Danie\\OneDrive\\Dokumenter\\Data\\Data\\ACP\\Falcon 7x" );
        //Path output = Paths.get( "C:\\Users\\Danie\\Documents\\GitHub\\Program\\Data\\" );

        //Tim's Paths
        Path input = Paths.get( "C:\\Users\\tim\\Desktop\\Data\\Data\\ACP\\Falcon 7x" );
        Path output = Paths.get( "C:\\Users\\tim\\Documents\\GitHub\\Program\\Data\\" );

        ser ez = new ser(output);

        // Behøver kun serialize 1 gang, med mindre der er sket ændringer i data'erne
         //ez.serialize(input);


        // Ligger info fra vores forskellige .ser filer ind i files
        climbData[][] climbArray = (climbData[][]) ez.deserializeData( "climb.csv" );
        descentData[][] descentArray = (descentData[][]) ez.deserializeData( "descent.csv" );
        cruiseData[][] cruiseArray = (cruiseData[][]) ez.deserializeData( "cruise.csv" );
        weightLimitsData[] weightlimitsArray = (weightLimitsData[]) ez.deserializeWeight( "weightlimits.csv" );
        List EBBRESSA138Array = (List) ez.deserializeRoute( "EBBR-ESSA-138.txt" );

        File route1 = new File( "C:\\Users\\tim\\Documents\\GitHub\\Program\\Data\\Route\\EBBR-VABB-40.txt" );



        // List<climbData> list = (List<climbData>) climbSer;
         System.out.println( climbArray[1][1].Fuel );

        Dijkstra dijkstra = new Dijkstra(route1,100);

//        dijkstra.giveCoordinates(climbArray,descentArray);

        List<vertex> graphList = new ArrayList<>();

        graphList.add(new vertex(1, 1));
        graphList.add(new vertex(7, 2));
        graphList.add(new vertex(5, 3));
        graphList.add(new vertex(2, 3));
        graphList.add(new vertex(4, 1));

        dijkstra.Dijkkstra_algorithm(graphList, graphList.get(0));


        /*
        dataArrays d = new dataArrays();

        cruiseData[][] cruiseList = d.cruiseFill(cruiseSer);

        System.out.println( "Cruise[0][0]: " + cruiseList[0][0].FuelFlow );

        climbData[][] climbList = d.climbFill(climbSer);

        System.out.println( "Climb[1][0]: " + climbList[1][0].Fuel );

        descentData[][] descentList = d.descentFill(descentSer);

        System.out.println( "Descent[1][0]: " + descentList[1][0].Fuel );

        weightLimitsData[] weightLimitList = d.weightLimitsFill(weightlimitsSer);

        System.out.println( "Weightlimit[0]: " + weightLimitList[0].weight );
        */


    }
}