package atlas;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Map;

import static atlas.Utils.*;

public class City implements Serializable {

  public static final int COLUMNS = 19;

  public static final String DELIMITER= "\t";

  /** integer id of record in geonames database */
  public final int geoNameId;

  /** name of geographical point (utf8) varchar(200) */
  public final String name;

  /** latitude in decimal degrees (wgs84) */
  public final double latitude;

  /** longitude in decimal degrees (wgs84) */
  public final double longitude;

  /** ISO-3166 2-letter country code, 2 characters */
  public final String countryCode;

  /** the timezone id (see file timeZone.txt) varchar(40) */
  public final String timeZone;

  /** name for administrative subdivision 1 */
  public final String admin1;

  /** name for administrative subdivision 2 */
  public final String admin2;

  // Public no-args constructor needed for serialization.
  @SuppressWarnings("unused")
  public City() {
    this(0.0, 0.0);
  }

  protected City(String line, Map<String, String> adminMap) {

    String[] tokens = line.split(DELIMITER);

    if (tokens.length != COLUMNS) {
      throw new RuntimeException("unexpected number of columns: " + tokens.length +
          ", expected: " + COLUMNS + ", column data: " + Arrays.asList(tokens));
    }

    this.geoNameId = toInt(tokens[0], null);
    this.name = tokens[1];
    this.latitude = toDouble(tokens[4], null);
    this.longitude = toDouble(tokens[5], null);
    this.countryCode = tokens[8];
    this.timeZone = tokens[17];

    String admin1Code = tokens[10];
    String admin2Code = tokens[11];

    this.admin1 = adminMap.get(String.format("%s.%s", this.countryCode, admin1Code));
    this.admin2 = adminMap.get(String.format("%s.%s.%s", this.countryCode, admin1Code, admin2Code));
  }

  protected City(double latitude, double longitude) {
    this.geoNameId = -1;
    this.name = null;
    this.latitude = latitude;
    this.longitude = longitude;
    this.countryCode = null;
    this.timeZone = null;
    this.admin1 = null;
    this.admin2 = null;
  }

  /**
   * Returns the absolute distance (as the crow flies) between 2 cities.
   *
   * @param that the other city.
   *
   * @return the absolute distance (as the crow flies) between 2 cities.
   */
  public double distanceTo(City that) {

    double R = 6371000;

    double phi1 = Math.toRadians(this.latitude);
    double phi2 = Math.toRadians(that.latitude);
    double phiDeltaLat = Math.toRadians(that.latitude - this.latitude);
    double phiDeltaLng = Math.toRadians(that.longitude - this.longitude);

    double a = (Math.sin(phiDeltaLat / 2) * Math.sin(phiDeltaLat / 2)) +
        (Math.cos(phi1) * Math.cos(phi2) * Math.sin(phiDeltaLng / 2) * Math.sin(phiDeltaLng / 2));

    double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));

    return R * c;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;

    City that = (City) o;

    return this.geoNameId == that.geoNameId;
  }

  @Override
  public int hashCode() {
    return this.geoNameId;
  }

  @Override
  public String toString() {
    return "City{" +
        "\n geoNameId=" + geoNameId +
        "\n name='" + name + '\'' +
        "\n latitude=" + latitude +
        "\n longitude=" + longitude +
        "\n countryCode='" + countryCode + '\'' +
        "\n timeZone='" + timeZone + '\'' +
        "\n admin1='" + admin1 + '\'' +
        "\n admin2='" + admin2 + '\'' +
        "\n}";
  }
}
