package atlas;

import java.util.Comparator;

/**
 * A comparator that sorts cities based on a provided center.
 */
public class DistanceComparator implements Comparator<City> {

  private final City center;

  public DistanceComparator(City center) {
    this.center = center;
  }

  public int compare(City city1, City city2) {

    if (city1.geoNameId > 0 && city1.geoNameId == city2.geoNameId) {
      return 0;
    }

    double distance1 = center.distanceTo(city1);
    double distance2 = center.distanceTo(city2);

    if (distance1 < distance2) {
      return -1;
    }
    if (distance1 > distance2) {
      return 1;
    }
    return 0;
  }
}
