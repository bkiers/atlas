package atlas;

import java.util.List;

public class Example {

  public static void main(String[] args) {

    // (lat, lng) somewhere in Venice
    double lat = 45.436636;
    double lng = 12.326413;

    // Find a single city
    City city = new Atlas().find(lat, lng);

    System.out.println(city);
    // City{geoNameId=3164603, name='Venice', latitude=45.43713, longitude=12.33265, countryCode='IT', timeZone='Europe/Rome', admin1='Veneto', admin2='Provincia di Venezia', admin3Id=027042}

    // Finds 3 cities around the (lat,lng) in a radius of 5 kilometers
    List<City> cities = new Atlas()
        .withLimit(3)
        .withMaxDistance(5000)
        .findAll(lat, lng);

    for (City c : cities) {
      System.out.println(c);
    }
    // City{geoNameId=3164603, name='Venice', latitude=45.43713, longitude=12.33265, countryCode='IT', timeZone='Europe/Rome', admin1='Veneto', admin2='Provincia di Venezia', admin3Id=027042}
    // City{geoNameId=3175265, name='Giudecca', latitude=45.42477, longitude=12.32906, countryCode='IT', timeZone='Europe/Rome', admin1='Veneto', admin2='Provincia di Venezia', admin3Id=027042}
    // City{geoNameId=3172456, name='Murano', latitude=45.45857, longitude=12.35683, countryCode='IT', timeZone='Europe/Rome', admin1='Veneto', admin2='Provincia di Venezia', admin3Id=027042}

    // (lat, lng) somewhere in Bavaria
    double lat2 = 47.90901;
    double lng2 = 12.29795;

    // Find a single city
    City city2 = new Atlas().find(lat2, lng2);

    System.out.println(city2);
    // City{geoNameId=2930367, name='Bad Endorf', latitude=47.90901, longitude=12.29795, countryCode='DE', timeZone='Europe/Berlin', admin1='Bavaria', admin2='Upper Bavaria', admin3Id=09187}
  }
}
