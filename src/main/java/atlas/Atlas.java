package atlas;

import java.util.List;

import static atlas.GeoLocationIndex.*;

public class Atlas {

  public static final double DEFAULT_MAX_DISTANCE = 25000.0;
  public static final int DEFAULT_LIMIT = 10;

  private static GeoLocationIndex index = Utils.deserialize(INDEX_FILE_NAME, GeoLocationIndex.class);

  private int limit;
  private double maxDistance;

  public Atlas() {
    this.limit = DEFAULT_LIMIT;
    this.maxDistance = DEFAULT_MAX_DISTANCE;
  }

  public Atlas withLimit(int limit) {
    this.limit = limit;
    return this;
  }

  public Atlas withMaxDistance(double maxDistance) {
    this.maxDistance = maxDistance;
    return this;
  }

  public GeoLocation find(double latitude, double longitude) {
    return index.nearestNeighbour(latitude, longitude, this.maxDistance);
  }

  public List<GeoLocation> findAll(double latitude, double longitude) {
    return index.nearestNeighbours(latitude, longitude, this.maxDistance, this.limit);
  }

  public static void main(String[] args) {

    double[][] locations = {
        {51.937176, 4.480499}, // Rotterdam, The Netherlands
        {50.851593, 4.355856}, // Brussels, Belgium
        {27.090462, -44.626175}, // North Atlantic Ocean
        {1.021461, 35.002645}, // Kitale, Kenya
        {25.212471, 55.275850}, // Dubai, United Arab Emirates
        {41.024795, 40.522663}, // Tophane Mh., Rize, Turkey
        {51.51988, -0.09446}, // Barbican, England
    };

    for (double[] location : locations) {
      System.out.println(new Atlas().find(location[0], location[1]));
      System.out.println("----------------------------------------------");
    }

//    List<GeoLocation> locations = new Atlas()
//        .withMaxDistance(25000)
//        .withLimit(5)
//        .findAll(51.937176, 4.480499);
//
//    for (GeoLocation location : locations) {
//      System.out.println(location.name);
//    }
  }
}
