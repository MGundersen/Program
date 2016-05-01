import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by MGund on 4/24/2016.
 */
public class ser {


    private static Path output = null;
    private static Path input = null;

    public ser (Path output) {
        this.output = output;
    }

    public static void serialize (Path input) {
        File file = new File(input.toString());

        //List af string med navne på filerne i vores directory
        String[] names = file.list();

        //Looper over listen
        for (String name : names) {
                File currentFile = new File(input.toString() + "\\" + name);
                if (currentFile.isDirectory()) {
                    serialize(currentFile.toPath());
                } else {
                    try {
                        FileOutputStream fileOut =
                                new FileOutputStream(output + "\\" + name + ".ser");
                        ObjectOutputStream out = new ObjectOutputStream(fileOut);
                        out.writeObject(currentFile);
                        out.close();
                        fileOut.close();
                        System.out.println("Serialized data is saved in /Data/" + name);

                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
    }

    public static File deserialize (String name) {

        File f = null;

        try
        {
            File file = new File(output.toString() + "\\" + name + ".ser");

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