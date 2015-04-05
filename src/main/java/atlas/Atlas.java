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

    if (args.length < 2 || args.length > 4) {
      System.err.println("usage: java -jar atlas-0.1.0.jar LAT LNG [maxDistance] [limit]");
      System.exit(1);
    }

    double lat = Double.valueOf(args[0]);
    double lng = Double.valueOf(args[1]);
    double maxDistance = args.length >= 3 ? Double.valueOf(args[2]) : DEFAULT_MAX_DISTANCE;
    int limit = args.length == 4 ? Integer.valueOf(args[3]) : DEFAULT_LIMIT;

    List<GeoLocation> locations = new Atlas()
        .withMaxDistance(maxDistance)
        .withLimit(limit)
        .findAll(lat, lng);

    System.out.printf("Found %s location(s) around (%s,%s) in a radius of %s meters:\n\n",
        locations.size(), lat, lng, maxDistance);

    for (GeoLocation location : locations) {
      System.out.printf("%s\n\n", location);
    }
  }
}
