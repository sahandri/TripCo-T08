import React from 'react';
import Home from './Home/Home.jsx';
import Pair from './Home/Pair/Pair.jsx';



export default class App extends React.Component {
    constructor(props) {  //construct from prop.jsx
        super(props);
        this.state = {
            allPairs: [], //creates an array for pairs
            sysFile: [],
            sysFile2: [],
            allInfo: [],
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
                    //run={this.run(this,this)}
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
        //sysFile2: file2

        //iterate over allInfo global variable using a for loop
        //match start, end variables to each element in allInfo
        //build a string that has all info corresponding to the matching id
        //build a pair object and associate all info string with the object

        //this.compute.bind(file, file2)
    }

    async browseFile2(file2) {
        console.log("Got file:", file2);
        let pairs = [];
        //let lineArray =[]
        //let infoArray =[];
        for(let i=0; i<Object.values(file2).length; i++){
            let name = file2[i].name;
            let id = file2[i].id;

            let p = {
                id : id,
                name : name
            };
            pairs.push(p)
        }
        //create array for column/value pairs. call it infoArray
        this.setState({
            allInfo: pairs,
            sysFile2: file2
            //allInfo: infoArray
        });
        console.log("allPair:", this.state.allPairs);
        console.log("allInfo:", this.state.allInfo);
        //this.run.bind(this.state.allPairs,this.state.allInfo);

        let pairs2 = [];
        console.log("in run : file1:", this.state.allPairs);
        for (let i = 0; i < Object.values(this.state.allPairs).length; i++) {
            let start;
            let end;
            let startIndex = this.state.allPairs[i].start; //get start from file i
            let endIndex = this.state.allPairs[i].end; //get end from file i
            let dist = this.state.allPairs[i].dist;
            let tot = this.state.allPairs[i].tot
            let startArrayFound = false;
            let endArrayFound = false;
            for(let j=0; j<Object.values(this.state.allInfo).length; j++) {
                console.log("file2[j].id", file2[j].id);
                console.log("startIndex", startIndex);
                if (file2[j].name == startIndex) {
                    start = this.state.allInfo[j];
                    startArrayFound = true;
                }

                console.log("in loop: endIndex=", endIndex)
                if (this.state.allInfo[j].name == endIndex) {
                    end = this.state.allInfo[j];
                    endArrayFound = true;
                    console.log("in loop: in if: end=", end)
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
                endInfo: end,
                keys: Object.keys(startIndex)
            };

            pairs2.push(p); //add object to pairs array
            console.log("Pushing pair in run: ", p); //log to console

        }
        this.setState({
            all: pairs2,
            //allInfo: infoArray
        });



    }

    //async useInfo(){
    //     this.state.allInfo
    //}



}
