## Atlas

TODO

## using Atlas

Clone this repository and add the library to your local Maven repos:

```bash
git clone https://github.com/bkiers/atlas.git
cd altas
mvn clean install
```
 
Add the following dependency to your project:
 
```xml
<dependency>
  <groupId>nl.big-o</groupId>
  <artifactId>atlas</artifactId>
  <version>0.1.0</version>
</dependency>
```

and use the library as follows:

```java
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
```

## (re) creating the index

In order to recreate the index, download the [`cities1000.zip`](http://download.geonames.org/export/dump/cities1000.zip) 
and unzip it in this project's `./data` folder. Optionally also copy the files:
[`admin1CodesASCII.txt`](http://download.geonames.org/export/dump/admin1CodesASCII.txt) and
[`admin2Codes.txt`](http://download.geonames.org/export/dump/admin1CodesASCII.txt) to
the `./data` folder.

To create a new index containing all cities, simply run:

    mvn -q exec:java
    mvn package
    
which will create a JAR file, `original-atlas-VERSION.jar`, inside the `./target` folder
of approximately 5.6 Megabytes containing the entire index of all cities. 

If you don't need all cities, you can create a smaller index. For example, if you want
to create an index of the counties Spain and Portugal, do the following:

    mvn -q exec:java -Dexec.args="ES,PT"
    mvn package
