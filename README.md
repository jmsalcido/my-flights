my-flights
==========

My Flights:
Look up flights searching your departure and arrival airport, select your flight and confirm your reservation.

Uses
==========
* EmberJS
* SpringMVC
* Jackson & GSON
* Maven

Dependencies
==========
* requests - python
* MySQLdb - python
* MySQL

To use the application you must create a database in MySQL named: "myflights" and modify the file: jdbc.properties under project/src/main/resources.

Running the project
==========
To run the application you must use maven and python:

    python help_scripts/load_cities.py -u "mysql_user" -p "mysql_pwd" && mvn tomcat:run

*Note*: actually all the data is gathered from FlightStats.com, the stub provided is from FlightStats too.