import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */

public class main {

    public static void main(String[] args) {


        Path input = Paths.get( "D:\\Documents 2\\Data\\" );

        Path output = Paths.get( "C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Førsteårsprojekt\\Program\\Data\\" );

        ser ez = new ser(output);

        //Behøver kun serialize 1 gang, med mindre der er sket ændringer i data'erne
        //ez.serialize(input);


        //Ligger info fra vores forskellige .ser filer ind i files
        File climb = ez.deserialize( "climb.csv" );
        File cruise = ez.deserialize( "cruise.csv" );
        File descent = ez.deserialize( "descent.csv" );
        File weightlimits = ez.deserialize( "weightlimits.csv" );
        File EBBRESSA138 = ez.deserialize( "EBBR-ESSA-138.txt" );


    }
}