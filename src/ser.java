import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */
public class ser {
    private static Path p = null;


    public static void serialize (Path p)
    {

        File file = new File( p.toString() );

        //List af string med navne på filerne i vores directory
        String[] names = file.list();

        //Looper over listen
        for ( String name : names ) {

            File currentFile = new File( p.toString() + "\\" + name );

            if (currentFile.isDirectory()) {
                serialize( currentFile.toPath() );
            } else {
                try {
                    FileOutputStream fileOut =
                            new FileOutputStream("Data\\" + name + ".ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(currentFile);
                    out.close();
                    fileOut.close();
                    System.out.println("Serialized data is saved in /Data/" + name);

                } catch (IOException e) {e.printStackTrace();}
            }
        }
    }

    public static File deserialize (String name) {

        File f = null;

        try
        {
            //Path skal ændres fra pc til pc, da den ville have den fulde sti til min mappe, Data, i projektet*
            File file = new File("C:\\Users\\MGund\\OneDrive\\Studie\\Datalogi 2016\\Førsteårsprojekt\\Program\\Data\\" + name + ".ser");
            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (File) in.readObject();
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
