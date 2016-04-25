import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */
public class main {

    public static void main(String[] args) {

        Path p1 = Paths.get( "D:\\Documents 2\\Data\\ACP\\Falcon 7x" );

        ser ez = new ser(p1);

        //Behøver kun serialize 1 gang, med mindre der er sket ændringer i data'erne
        ez.serialize();


        //Ligger info fra vores forskellige .ser filer ind i strings
        String climb = ez.deserialize("climb");
        String cruise = ez.deserialize("cruise");
        String descent = ez.deserialize("descent");
        String weightlimits = ez.deserialize("weightlimits");


        //Laver vores string til en liste af linjer, så vi let kan loope over den(for eksempel)
        String[] lines = cruise.split(System.getProperty("line.separator"));

        //Eksempel på brug af data.
        System.out.println( lines[3] );
        System.out.println(  );

        for (int i = 0 ; i < lines.length ; i++) {
            //Checker efter Flightlevel = 300 og weight = 37000 pund
            if (lines[i].split(";")[0].equals("300") && lines[i].split(";")[2].equals("37000")){
                System.out.println( lines[i] );
            }
        }



    }
}