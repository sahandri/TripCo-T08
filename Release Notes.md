## TripCo v1.0 
### T08 - The Absintees

### Overview
Takes a .csv file with an itenerary that calculates the distance between two bars.  That distance is then put into a JSON file that can be used by the website to create a table.  This table computes the total distance and current total distance during the trip.

### What's New 
Everything

### Improvements
None

### Fixes
None

### Outstanding Issues
None

### Installation and Deployment
Prerequisites: Maven, Node.js
Download and unpack the source code from the release page and`cd` into the project folder.

To run the server:

    mvn package
    java -cp target/T08-1.0.jar edu.csu2017fa314.T08.TripCo <CSV_FILENAME> <OUTPUT_FILENAME>
    
To run the web app, first cd into `web/` and then run:

    npm install
    npm run dev

### Dependencies
server side: Listed in pom
Cliente side: package.json

### References
React, Eclipse, JSON-Simple, Java

