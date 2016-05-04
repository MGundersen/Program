package Data;

import java.io.Serializable;

/**
 * Created by MGund on 5/1/2016.
 */
public class cruiseData implements Serializable {
    public double FuelFlow;
    public double Speed;

    public cruiseData(double FuelFlow, double Speed){
        this.FuelFlow = FuelFlow;
        this.Speed = Speed;
    }
}