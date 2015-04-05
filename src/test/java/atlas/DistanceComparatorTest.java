package atlas; 

import org.junit.Test; 

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class DistanceComparatorTest { 

  @Test
  public void testCompare() throws Exception { 

    final City rotterdam = new City(51.933900, 4.472554);
    final City amsterdam = new City(52.375186, 4.897244);
    final City utrecht = new City(52.090066, 5.122477);
    final City groningen = new City(53.232561, 6.554476);
    final City schiedam = new City(51.916742, 4.392050);

    DistanceComparator comparator = new DistanceComparator(rotterdam);

    assertThat(comparator.compare(amsterdam, schiedam), is(1));
    assertThat(comparator.compare(amsterdam, groningen), is(-1));
    assertThat(comparator.compare(amsterdam, amsterdam), is(0));
    assertThat(comparator.compare(schiedam, utrecht), is(-1));
    assertThat(comparator.compare(utrecht, schiedam), is(1));
    assertThat(comparator.compare(schiedam, rotterdam), is(1));
  }
} 
