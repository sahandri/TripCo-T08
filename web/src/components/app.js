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
        let pairs = this.state.allInfo; //sets the array as pairs
        let ps = pairs.map((pp) => { //pair map
            return <Pair {...pp}/>; //?
        });
        return (
            <div className="app-container">
                <Home
                    browseFile={this.browseFile.bind(this)} //?
                    browseFile2={this.browseFile2.bind(this)} //?
                    HandleImage={this.HandleImage(this)}
                    pairs={ps}
                />
            </div>
        )
    }

    async HandleImage(url){
        return (
            <img src={url}/>,
                document.getElementById('root')
        );
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
        this.run.bind(this.state.sysFile,this.state.sysFile2);

    }

 //async useInfo(){
   //     this.state.allInfo
 //}


    async run(file1, file2){
        //For loop that goes through all pairs,
        let pairs = [];
        let tot = 0; //start total
        for (let i = 0; i < Object.values(file1).length; i++) {
            let start;
            let end;
            let startIndex = file1[i].start; //get start from file i
            let endIndex = file1[i].end; //get end from file i
            let dist = file1[i].distance;
            let startArrayFound = false;
            let endArrayFound = false;
            tot = tot + dist; //current total
            for(let j=0; j<Object.values(file2).length; j++){
                if (file2[j].id === startIndex) {
                    start = file2[j];
                    startArrayFound = true;
                }
                if (file2[j].id === endIndex) {
                    end = file2[j];
                    endArrayFound = true;
                }
                if (startArrayFound && endArrayFound) {
                    break;
                }
            }
            let p = { //create object with start, end, and dist variable
                start : startIndex,
                end : endIndex,
                dist: dist,
                tot: tot, // print total
                startInfo: start,
                endInfo: end,
            };
            pairs.push(p); //add object to pairs array
            console.log("Pushing pair: ", p); //log to console
        }
        this.setState({
            all: pairs,
            //allInfo: infoArray
        });

        //Here we will update the state of app.
        // Anything component (i.e. pairs) referencing it will be re-rendered
    }


}
