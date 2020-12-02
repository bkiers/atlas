## Atlas  &nbsp; [![Build Status](https://travis-ci.org/bkiers/atlas.png)](https://travis-ci.org/bkiers/atlas)

Atlas, an offline reverse Geo-coding library written in Java, inspired by 
[reverse-geocoder](https://github.com/thampiman/reverse-geocoder).

The index is a double `SortedMap<Double, Set<City>>` that keeps an index
on both the latitudes and longitudes of the cities.

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
  <version>0.2.0</version>
</dependency>
```

and use the library as follows:

```java
// (lat, lng) somewhere in Venice
double lat = 45.436636;
double lng = 12.326413;

// Find a single city
City city = new Atlas().find(lat, lng);

System.out.println(city);
//    City{
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
List<City> cities = new Atlas()
    .withLimit(3)
    .withMaxDistance(5000)
    .findAll(lat, lng);

for (City c : cities) {
  System.out.println(c);
}
//      City{
//        geoNameId=3164603
//        name='Venice'
//        latitude=45.43713
//        longitude=12.33265
//        countryCode='IT'
//        timeZone='Europe/Rome'
//        admin1='Veneto'
//        admin2='Provincia di Venezia'
//      }
//      City{
//        geoNameId=3175265
//        name='Giudecca'
//        latitude=45.42477
//        longitude=12.32906
//        countryCode='IT'
//        timeZone='Europe/Rome'
//        admin1='Veneto'
//        admin2='Provincia di Venezia'
//      }
//      City{
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

In order to recreate the index, download the [`cities1000.zip`](https://download.geonames.org/export/dump/cities1000.zip) 
and unzip it in this project's `./data` folder. Optionally also copy the files:
[`admin1CodesASCII.txt`](https://download.geonames.org/export/dump/admin1CodesASCII.txt) and
[`admin2Codes.txt`](https://download.geonames.org/export/dump/admin2Codes.txt)
to the `./data` folder.

You can also run `update.sh` in the `./data` folder, which downloads these files via `curl`.

To create a new index containing all cities, simply run:

    mvn -q exec:java
    mvn package
    
which will create a JAR file, `atlas-VERSION.jar`, inside the `./target` folder
of approximately 5.6 Megabytes containing the entire index of all cities. 

If you don't need all cities, you can create a smaller index. For example, if you want
to create an index of the counties Spain and Portugal, do the following:

    mvn -q exec:java -Dexec.args="ES,PT"
    mvn package

## credits

* data from [GeoNames](https://www.geonames.org)
* *blazing* fast (de)serialization using [Kryo](https://github.com/EsotericSoftware/kryo)

## license

[MIT](https://opensource.org/licenses/MIT)
