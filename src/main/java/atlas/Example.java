package atlas;

import java.util.List;

public class Example {

  public static void main(String[] args) {

    // (lat, lng) somewhere in Venice
    double lat = 45.436636;
    double lng = 12.326413;

    // Find a single location
    GeoLocation location = new Atlas().find(lat, lng);

    System.out.println(location);
    //    GeoLocation{
    //      geoNameId=3164603
    //      name='Venice'
    //      latitude=45.43713
    //      longitude=12.33265
    //      countryCode='IT'
    //      timeZone='Europe/Rome'
    //      admin1='Veneto'
    //      admin2='Provincia di Venezia'
    //    }

    // Finds 3 cities around the (lat,lng) in a radius of 5 kilometers
    List<GeoLocation> locations = new Atlas()
        .withLimit(3)
        .withMaxDistance(5000)
        .findAll(lat, lng);

    for (GeoLocation loc : locations) {
      System.out.println(loc);
    }
    //      GeoLocation{
    //        geoNameId=3164603
    //        name='Venice'
    //        latitude=45.43713
    //        longitude=12.33265
    //        countryCode='IT'
    //        timeZone='Europe/Rome'
    //        admin1='Veneto'
    //        admin2='Provincia di Venezia'
    //      }
    //      GeoLocation{
    //        geoNameId=3175265
    //        name='Giudecca'
    //        latitude=45.42477
    //        longitude=12.32906
    //        countryCode='IT'
    //        timeZone='Europe/Rome'
    //        admin1='Veneto'
    //        admin2='Provincia di Venezia'
    //      }
    //      GeoLocation{
    //        geoNameId=3172456
    //        name='Murano'
    //        latitude=45.45857
    //        longitude=12.35683
    //        countryCode='IT'
    //        timeZone='Europe/Rome'
    //        admin1='Veneto'
    //        admin2='Provincia di Venezia'
    //      }
  }
}
