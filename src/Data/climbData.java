package Data;

import java.io.Serializable;

/**
 * Created by MGund on 5/1/2016.
 */
public class climbData implements Serializable{

    public double Distance;
    public double Time;
    public double Fuel;

    public climbData(double Distance, double Time, double Fuel){
        this.Distance = Distance;
        this.Time = Time;
        this.Fuel = Fuel;
    }

}
