import Data.*;
import Distance.coordinate;
import Distance.readPaths;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by MGund on 4/24/2016.
 */
public class ser {


    private Path output = null;
    private Path input = null;

    public ser (Path output) {
        this.output = output;
    }

    public void serialize (Path input) {

        File file = new File(input.toString());

        //List af string med navne på filerne i vores directory
        String[] names = file.list();

        //Looper over listen
        for (String name : names) {
                File currentFile = new File(input.toString() + "\\" + name);
                if (currentFile.isDirectory()) {
                    serialize(currentFile.toPath());
                } else {
                    if (currentFile.getName().split("-").length == 3) {
                        System.out.println( "Route: " + currentFile.getName());
                        serializeWaypoints(currentFile);
                    } else if (currentFile.getName().equals("climb.csv")) {
                        System.out.println( "Climb: " + currentFile.getName());
                        serializeClimb(currentFile);
                    } else if (currentFile.getName().equals("cruise.csv")) {
                        System.out.println( "Cruise: " + currentFile.getName());
                        serializeCruise(currentFile);
                    } else if (currentFile.getName().equals("descent.csv")) {
                        System.out.println( "descent: " + currentFile.getName());
                        serializeDescent(currentFile);
                    } else if (currentFile.getName().equals("weightlimits.csv")) {
                        System.out.println( "Weightlimit: " + currentFile.getName());
                        serializeWeightlimits(currentFile);
                    } else {
                        System.out.println( "File: " + currentFile.getName());
                        serializeFile(currentFile);
                    }

                }
            }
    }

    public Object[][] deserializeData (String name) {

        Object[][] f;

        try
        {
            File file = new File(output.toString() + "\\" + name + ".ser");

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (Object[][]) in.readObject();
            in.close();
            fileIn.close();
            return f;

        }catch(IOException i) { i.printStackTrace();}
        catch(ClassNotFoundException c) {
            System.out.println( name + " was not found");
            c.printStackTrace();
        }


        return null;
    }

    public Object[] deserializeWeight (String name) {

        Object[] f;

        try
        {
            File file = new File(output.toString() + "\\" + name + ".ser");

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (Object[]) in.readObject();
            in.close();
            fileIn.close();
            return f;

        }catch(IOException i) { i.printStackTrace();}
        catch(ClassNotFoundException c) {
            System.out.println( name + " was not found");
            c.printStackTrace();
        }


        return null;
    }

    public List deserializeRoute (String name) {

        List f;

        try
        {
            File file = new File(output.toString() + "\\" + name + ".ser");

            FileInputStream fileIn = new FileInputStream(file);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            f = (List) in.readObject();
            in.close();
            fileIn.close();
            return f;

        }catch(IOException i) { i.printStackTrace();}
        catch(ClassNotFoundException c) {
            System.out.println( name + " was not found");
            c.printStackTrace();
        }


        return null;
    }


    private void serializeClimb (File file) {
        dataArrays d = new dataArrays();
        climbData[][] array = d.climbFill(file);

        ArrayList<String> list = new ArrayList<>();

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(output + "\\" + file.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject( array );
            out.flush();
            fileOut.close();
        } catch (IOException e) {e.printStackTrace();
            System.out.println( "Was unable to serialize climb" );}
    }
    private void serializeCruise (File file) {
        dataArrays d = new dataArrays();
        cruiseData[][] array = d.cruiseFill(file);
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(output + "\\" + file.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(array);
            out.flush();
            fileOut.close();
        } catch (IOException e) {e.printStackTrace();
            System.out.println( "Was unable to serialize cruise" );}
    }
    private void serializeDescent (File file) {
        dataArrays d = new dataArrays();
        descentData[][] array = d.descentFill(file);
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(output + "\\" + file.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(array);
            out.flush();
            fileOut.close();
        } catch (IOException e) {e.printStackTrace();
            System.out.println( "Was unable to serialize descent" );}
    }
    private void serializeWeightlimits (File file) {
        dataArrays d = new dataArrays();
        weightLimitsData[] array = d.weightLimitsFill(file);
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(output + "\\" + file.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(array);
            out.flush();
            fileOut.close();
        } catch (IOException e) {e.printStackTrace();
            System.out.println( "Was unable to serialize weightlimits" );}
    }
    private void serializeWaypoints (File file) {
        readPaths rp = new readPaths();
        List<coordinate> wp = rp.waypoints(file);

        try {
            FileOutputStream fileOut =
                    new FileOutputStream(output + "\\" + file.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(wp);
            out.flush();
            fileOut.close();
        } catch (IOException e) {e.printStackTrace();
            System.out.println( "Was unable to serialize route: " + file.getName() );}
    }
    private void serializeFile (File file) {
        try {
            FileOutputStream fileOut =
                    new FileOutputStream(output + "\\" + file.getName() + ".ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(file);
            out.flush();
            fileOut.close();
        } catch (IOException e) {e.printStackTrace();
            System.out.println( "Was unable to serialize file: " + file.getName() );}
    }
}