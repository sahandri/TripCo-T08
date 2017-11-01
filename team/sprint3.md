# Sprint 3 - T08 - The Absentees

## Goal

### A web based solution with shorter trips and a large data source.

## Definition of Done

* Ready for the demo.
* Sprint Review and Restrospectives completed.
* Product Increment release `v3.0` created on GitHub with appropriate version number and name, a description based on the template, and an executable JAR file for the demo.
* Version in pom.xml should be `<version>3.0.0</version>`.
* Unit tests for all new features and public methods at a minimum.
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

## Plan 

User stories (epics) in the Sprint Backlog: *#97, #143, #144, #145, #98, #146*.  

Total planned tasks / issues in the Sprint Backlog: *13* 

## Daily Scrums

Date | Tasks done this time | Tasks done next time | Impediments | Confidence
:--- | :--- | :--- | :--- | :---
10/06/17 | web page shows extra info | write test files for sprint2 | meeting | Medium
10/07/17 | write test files for sprint2 | finishing refactoring | schedule for sprint3 | Medium
10/11/17 | None | Refactor | none | Low
10/13/17 | #155, #150 | Refactor & test backlog complete | demands from other classes | Medium
10/15/17 | Rearchitecture, complete Test 3, complete refactor | fast response, server setup | Low
10/16/17 | #149 | #143, #154 | Time | Medium
10/18/17 | #143, #154  | #144, #156, #162, #163, #164 | Time | Medium
 

## Review

#### Completed user stories (epics) in Sprint Backlog 
* Web Server: Complete with need of improvments
* 2-opt: Completely done
* Fast Response: Down to seconds
* Build server requests and responses
* Create view API for JSON Query
15 Completed *number of issues completed* issues associated with these user stories.

#### Incomplete user stories / epics in Sprint Backlog 
* *user story*: *Explanation...*
* Webpage provides column display options: No capability for display of selected information.
* Webpage displays selected columns: similar to above, no ability to select and display certain info.

#### What went well
* *something*
* Demo went well
* Communication and planning were good
* Completed most of required tasks

#### Problems encountered and resolutions
* *something*
* Begin making progress earlier despite already having planned, resolved by putting in too much time two days prior to demo
* Functionality in react
* Last minute changes

## Retrospective

Topic | Teamwork | Process | Tools
:--- | :--- | :--- | :---
What we will change this time | Even work distribution | more meetings, earlier start | everyone could run program, switched to IntelliJ
What we did well | work split | communication/no procrastination | correcting mistakes, keeping code cleaner
What we need to work on | swarming on incomplete portions | starting sooner, no last minute changes | more testing, better use of Travis
What we will change next time | early communication of blockers | completing tests with features | taking full advantage of travis for testing.
