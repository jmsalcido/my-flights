#!/usr/bin/env python
#
# Get all the info from FS.

import sys
import MySQLdb
import requests
import json
import getopt

appId = "c2eb128c"
appKey = "298a8715d0c24aab56b97850f2403a31"
apiUrl = "https://api.flightstats.com/flex/airports/rest/v1/json/active?appId=%s&appKey=%s"
create_tables_file = "mysql_scripts/create_tables.sql"

def connect(host, user, passwd, db):
    db = MySQLdb.connect(host=host, user=user, passwd=passwd, db=db)
    return db

def doConnection(usr, pwd):
    print "[[TRYING TO CONNECT...]]"
    print "========================"
    database_name = "myflights"
    database_host = "localhost"
    database_user = usr
    database_passwd = pwd
    try:
        db = connect(database_host, database_user, database_passwd, database_name)
    except MySQLdb.Error, e:
        raise e
    print "[[CONNECTION TO", database_host, database_name, "AS", database_user, "]]"
    print "[[OK]]"
    return db

def executeQueries(db, queries):
    '''Execute all the queries in a list.'''
    print "[[MYSQL QUERIES]]"
    print "================="
    cursor = db.cursor()
    try:
        for query in queries:
            print "[[MYSQL]] Executing:%s"% query
            cursor.execute(query)
        db.commit()
    except MySQLdb.Error, e:
        db.rollback()
        raise e

def getAirports():
    url = apiUrl % (appId, appKey)
    print "[[REQUEST]] I will request the airports from US to %s" % url
    req = requests.get(url)
    print "[[REQUEST]] DONE"
    return req.content

def createInsertQueries(airport_list):
    _list = []
    for airport in airport_list:
        _airport = "INSERT INTO airports VALUES(NULL, '%s','%s','%s','%s','%s','%s','%s','%s','%s',%s,%s,%s,%s);" % (airport.get('fs', ""), airport.get('iata', ""), airport.get('icao', ""), airport.get('faa', ""), airport['name'].replace("'",""), airport['city'].replace("'",""), airport['countryCode'],airport['countryName'].replace("'",""), airport['timeZoneRegionName'], airport['utcOffsetHours'], airport['classification'], airport['latitude'], airport['longitude'])
        _list.append(_airport)
    return _list

def main(argv):
    try:
        opts, args = getopt.getopt(argv, "u:p:")
    except getopt.GetoptError:
        print "try -u root -p yourpwd";
        sys.exit(1);
    for opt, arg in opts:
        if opt == "-u":
            usr = arg
        elif opt == "-p":
            pwd = arg
    db = doConnection(usr, pwd)
    fopen = open(create_tables_file, "r")
    queries = fopen.read().split(";")
    queries.pop() # remove the last element
    executeQueries(db, queries)
    airports_json = getAirports()
    airports_list = json.loads(airports_json)['airports']
    queries = createInsertQueries(airports_list)
    executeQueries(db, queries)

if __name__ == "__main__":
    print "[[Loading cities from FlightStats into our MySQL database]]"
    print "==========================================================="
    print "[[INFO]] appId = %s" % appId
    print "[[INFO]] appKey = %s" % appKey
    main(sys.argv[1:])
    print "[[DONE]]"