package Distance;

import java.io.Serializable;

/**
 * holds the longitude and latitude of a Distance.coordinate
 */
public class coordinate implements Serializable {

    double latitude;
    double longitude;

    public coordinate(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }

}