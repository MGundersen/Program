import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */
public class ser {
    private static Path p = null;

    public ser (Path p) {
        this.p = p;
    }

    public static void serialize ()
    {

        File file = new File( p.toString() );

        //List af string med navne på filerne i vores directory
        String[] names = file.list();

        //Looper over listen
        for ( String name : names ) {
            //Navnene fra excel filerne hed climb.csx(Noget i den stil), så .ser filen hed climb.csx.ser, det tiltede mig, så jeg fjernede det :^)
            String newName = name.substring(0,name.length()-4);

            File currentFile = new File( p.toString() + "\\" + name );

            try (BufferedReader br = new BufferedReader(new FileReader(currentFile))) {

                StringBuilder sb = new StringBuilder();
                String line = br.readLine();

                //Læser hver line og smider det hele i en string
                while (line != null) {
                    sb.append(line);
                    sb.append(System.lineSeparator());
                    line = br.readLine();
                }
                String everything = sb.toString();


                FileOutputStream fileOut =
                        new FileOutputStream("Data\\" + newName + ".ser");
                ObjectOutputStream out = new ObjectOutputStream(fileOut);
                out.writeObject(everything);
                out.close();
                fileOut.close();
                System.out.println("Serialized data is saved in /Data/" + newName);


            } catch (FileNotFoundException e) {
            } catch (IOException i) {
            }

        }
    }

    public static String deserialize (String name) {

        String f = null;

        try
        {
            //Path skal ændres fra pc til pc, da den ville have den fulde sti til min mappe, Data, i projektet*
            File file = new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Førsteårsprojekt\\Program\\Data\\" + name + ".ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (String) in.readObject();
            in.close();
            fileIn.close();

        }catch(IOException i)
        {
            i.printStackTrace();

        }catch(ClassNotFoundException c)
        {
            System.out.println( name + " was not found");
            c.printStackTrace();
        }


        return f;
    }
}
