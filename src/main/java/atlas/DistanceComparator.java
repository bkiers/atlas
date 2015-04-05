package atlas;

import java.util.Comparator;

/**
 * A comparator that sorts locations TODO
 */
public class DistanceComparator implements Comparator<GeoLocation> {

  private final GeoLocation center;

  public DistanceComparator(GeoLocation center) {
    this.center = center;
  }

  public int compare(GeoLocation location1, GeoLocation location2) {

    if (location1.geoNameId == location2.geoNameId) {
      return 0;
    }

    double distance1 = center.distanceTo(location1);
    double distance2 = center.distanceTo(location2);

    if (distance1 < distance2) {
      return -1;
    }
    if (distance1 > distance2) {
      return 1;
    }
    return 0;
  }
}
