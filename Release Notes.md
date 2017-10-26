## TripCo v3.1
### T08 - The Absentees

### Overview
Takes a search key given through a webpage. Search key is used in order to determine the destinations of a trip by accessing a trip database.  Based on the destinations, a trip itinerary is then computed using the 2-opt algorithm. Distance is calculated between successive destinations. That itinerary is then put into a JSON file also containing the Names of each destination that can be used by the website to create a table displaying the trip.  By default, the table displays the names of each destination on the trip and also keeps a running tally of the cumulative distance displaying the total distance of the trip at the end. The website also has the ability to display an image showing the path of the trip given a .svg file.

### What's New 
* Map (.svg file) now generated showing path of travel overlayed onto a background image, this can be loaded to the webpage using the "Click here for SVG" button.
* Uses 2-opt algorithm in order to calculate a shorter trip than previously. This is output into a .json file that is loaded into the webpage.
* The webpage now displays more relevant information such as names of destinations immediately after search key is entered.
* Webpage also displays running total of distance traveled.

### Improvements
None

### Fixes
* Server successfully displays svg and itinerary after issuing a single search query.

### Outstanding Issues
Lacking checkbox functionality to display certain data.

### Installation and Deployment
Prerequisites: Maven, Node.js
Download and unpack the source code from the release page and`cd` into the project folder.
First run the web app, and then the server.
To run the web app, first cd into `web/` and then run:

    npm install --save svg-inline-react 
    npm run dev

To run the server:

    mvn package
    java -cp T08-3.1-jar-with-dependencies.jar edu.csu2017fa314.T08.TripCoServer 

### Dependencies
Server side: Listed in pom
Client side: Listed in package.json

### References
React, Eclipse, JSON-Simple, Java, Spark, GSON
