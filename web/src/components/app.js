import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';
require('react-select/dist/react-select.css');



export default class App extends React.Component {
    constructor(props) {  //construct from prop.jsx
        super(props);
        this.state = {
            allPairs: [], //creates an array for pairs
            sysFile: [],
            all: []
        }
    };

    render() {
        let pairs = this.state.all; //sets the array as pairs
        let ps = pairs.map((pp) => { //pair map
            return <Pair {...pp}/>; //?
        });
        return (
            <div className="app-container">
                <Home
                    browseFile={this.browseFile.bind(this)} //?
                    browseFile2={this.browseFile2.bind(this)} //?
                    pairs={ps}
                />
            </div>
        )
    }


    async browseFile(file) {
        console.log("Got file:", file);
        //For loop that goes through all pairs,
        let pairs = [];
        let tot = 0; //start total
        for (let i = 0; i < Object.values(file).length; i++) {
            let start = file[i].start; //get start from file i
            let end = file[i].end; //get end from file i
            let dist = file[i].distance;
            tot = tot + dist; //current total
            let p = { //create object with start, end, and dist variable
                start: start,
                end: end,
                dist: dist,
                tot: tot // print total
            };
            pairs.push(p); //add object to pairs array
            console.log("Pushing pair: ", p); //log to console
        }

        //Here we will update the state of app.
        // Anything component (i.e. pairs) referencing it will be re-rendered
        this.setState({
            allPairs: pairs,
            sysFile: file
        });

        //iterate over allInfo global variable using a for loop
        //match start, end variables to each element in allInfo
        //build a string that has all info corresponding to the matching id
        //build a pair object and associate all info string with the object

    }

    async browseFile2(file2) {
        console.log("Got file:", file2);
        let nameIndex;
        var keys = [];
        //check the keys in json file
        for(var k in file2[0]) keys.push(k);
        for(let i=0; i<keys.length; i++){
            if(keys[i].toUpperCase()=="NAME"){
                nameIndex = keys[i];
            }
        }
        //search the name key in json file and store all it's information
        let pairs2 = [];
        for (let i = 0; i < Object.values(this.state.allPairs).length; i++) {
            let start=[];
            let end=[];
            let startIndex = this.state.allPairs[i].start; //get start from file i
            let endIndex = this.state.allPairs[i].end; //get end from file i
            let dist = this.state.allPairs[i].dist;
            let tot = this.state.allPairs[i].tot
            let startArrayFound = false;
            let endArrayFound = false;
            for(let j=0; j<Object.values(file2).length; j++) {
                if (file2[j][nameIndex] == startIndex) {
                    start = file2[j];
                    startArrayFound = true;
                }
                if (file2[j][nameIndex] == endIndex) {
                    end = file2[j];
                    endArrayFound = true;
                }
                if (startArrayFound && endArrayFound) {
                    break;
                }
            }
            let p = { //create object with start, end, and dist variable
                start: startIndex,
                end: endIndex,
                dist: dist,
                tot: tot, // print total
                startInfo: start,
                endInfo: end
            };
            pairs2.push(p); //add object to pairs array
            console.log("Pushing pair in run: ", p); //log to console
        }
        this.setState({
            all: pairs2,
        });



    }

}
