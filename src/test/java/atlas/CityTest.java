package atlas; 

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CityTest { 

  @Test
  public void testDistanceTo() throws Exception {

    final City rotterdam = new City(51.933900, 4.472554);
    final City amsterdam = new City(52.375186, 4.897244);
    final City utrecht = new City(52.090066, 5.122477);
    final City groningen = new City(53.232561, 6.554476);

    // http://www.nhc.noaa.gov/gccalc.shtml
    assertTrue(approximateOk(rotterdam.distanceTo(amsterdam), 57));
    assertTrue(approximateOk(rotterdam.distanceTo(groningen), 201));
    assertTrue(approximateOk(utrecht.distanceTo(utrecht), 0));
  }

  private static boolean approximateOk(double distance, double expected) {
    double delta = Math.abs((distance / 1000.0) - expected);

    // Must be accurate within 3 km.
    return delta <= 3;
  }
} 
