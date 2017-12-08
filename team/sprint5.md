# Sprint 5 - T08 - The Absentees

## Goal

### Reliable first release with clean code!

## Definition of Done

* Ready for demo / customer release.
* Sprint Review and Restrospectives completed.
* Product Increment release `v5.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>5.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
* Coverage at least 50% overall and for each class.
* Clean continuous integration build/test on master branch.

## Policies

* Tests and Javadoc are written before/with code.  
* All pull requests include tests for the added or modified code.
* Master is never broken.  If broken, it is fixed immediately.
* Always check for new changes in master to resolve merge conflicts locally before committing them.
* All changes are built and tested before they are committed.
* Continuous integration always builds and tests successfully.
* All commits with more than 1 line of change include a task/issue number.
* All Java dependencies in pom.xml.


# Metrics

Statistic | Planned | Completed
--- | ---: | ---:
Tasks |  36   | *value* 
Story Points |  49  | *value* 


Statistic | Start | End
--- | ---: | ---:
Overall Test Coverage | 37 | 69 
Smells | 34 | 24 
Duplication | 32 | 30 
Technical Debt Ratio | %21.2 | %18 

* Begining of sprint:
![alt text](https://github.com/csu2017fa314/T08/blob/master/images/Capture.PNG)
* End of sprint:
![alt text](https://github.com/csu2017fa314/T08/blob/master/images/Capture2.PNG)

# Code coverage:
Class | Method Coverage(START) | Line Coverage(START) | Method Coverage(END) | Line Coverage(END)
:--- | :--- | :--- |---: | ---:
Tripco | 0% | 0% | 0% | 0%
TripCoServer | 0% | 0% | )% | 0%
DataBase | 73.3% | 50.9% | 83% | 67%
Distance  | 100% | 100% | 100% | 100%
Model | 28.6% | 28.6% | 83% | 88%
Trip | 72.7% | 66.7% | 72% | 66%
TripManager | 80% | 80.3% | 90% | 78%
TripWorker | 85.7% | 70.8% |85% | 71%
Location | 0% | 0% | 100% | 100%
Server | 0% | 0% | 0% | 0%
ServerRequest | 0% | 0% |100% | 100%
Itinerary | 0% | 0% | 66% | 64%
makeSvg | 0% | 0% | 0% | 0%

## Plan

Epics planned for this release.

* *## title*
*

## Daily Scrums

Date | Tasks done now | Tasks done next | Impediments | Coverage | Smells | Duplication | Technical Debt Ratio
:--- | :--- | :--- | :--- | ---: | ---: | ---: | ---:
*date* | *issue numbers only* | *issue numbers only* | *High* | *50* | *10* | *20* | *15*
11/13/17 | Setting up times for meeting this week | Complete Sprint Planning | Medium | 40 | 34 | 32 | 21.2 |
12/1/17 | #242 #243 #244 #261 #263 | #281 #208 #188 | Medium | 60 | 34 | 32 | 21.2 |
12/4/17 | #281 #208 | #277 #278 #279 #258| High | 60 | 32 | 30 | 21.2 |
12/6/17 | #267 #268 #269 #270 #271 #272 | All | High | 60 | 30 | 28 | 18 |
 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* *user story*:  *comments*
* Website: Completed Website
* Added map coverage: Completed the across map svg

#### Incomplete user stories / epics in Sprint Backlog 
* *user story*: *Explanation...*
* Google Map: Couldn't get KML working
* Zoom and Pan: not enough time to enable

#### What went well
* *something*
* Website turned into something that someone can understand
* SVG works correctly and goes across sides correctly

#### Problems encountered and resolutions
* *something*
* Issue with react's radio buttons, so switched back to normal buttons
* Finished selection method with correct selection instead of broken selection.

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | Have a team meeting earlier in the project | Complete work in an order that builds rather than subtracts | Learn React better 
What we did well | End game communication | Polished website to complete working | Added server side fixes 
What we need to work on | Working sooner than the last weekend | Having a correct list of issues to complete | Use the Zen boards the way they are actually supposed to be used
What we will change next time | Physical meetings rather than virtual | Finish coding 2 days before release | Work on zen hub faster and sooner
