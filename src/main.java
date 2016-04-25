import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */
public class main {

    public static void main(String[] args) {

        Path p1 = Paths.get( "D:\\Documents 2\\Data\\" );

        ser ez = new ser();

        //Behøver kun serialize 1 gang, med mindre der er sket ændringer i data'erne
        //ez.serialize(p1);


        //Ligger info fra vores forskellige .ser filer ind i strings
        File climb = ez.deserialize("climb.csv");
        File cruise = ez.deserialize("cruise.csv");
        File descent = ez.deserialize("descent.csv");
        File weightlimits = ez.deserialize("weightlimits.csv");
        File EBBRESSA138 = ez.deserialize("EBBR-ESSA-138.txt");


    }
}