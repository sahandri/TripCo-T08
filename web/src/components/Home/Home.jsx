import React, {Component} from 'react';
import Dropzone from 'react-dropzone'

class Home extends React.Component {
    render() { //Start of the HTML side of react
		let total; //Initalize
		if(this.props.pairs == ""){ //If no json file
			total = 0;
		}
		else{ //after json call
			total = this.props.pairs[this.props.pairs.length - 1].props.tot;
		}
        return <div className="home-container">
            <div className="inner">
				<h2>T08 - The Absentees</h2>
                <h3>Itinerary</h3>	
				
                <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}> //How to open the JSON file
                    <button>Open JSON File</button>
                </Dropzone>
                <table className="pair-table"> //For CSS
                    {this.props.pairs} //Calls Pair.jsx for HTML script
                    <tbody>
                        <tr>
                            <td colSpan="3">Total:</td>
                            <td>{total}</td> //Displays Total
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
    }

    drop(acceptedFiles) { //Calls Drop which takes the JSON file
        console.log("Accepting drop");
        acceptedFiles.forEach(file => { //for each file accepted
            console.log("Filename:", file.name, "File:", file); //output filename
            console.log(JSON.stringify(file)); //output file as a string
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log(JsonObj); //Output Json object
                    this.props.browseFile(JsonObj); //Calls Browsefile in ap.js file
                };
            })(file).bind(this); //?

            fr.readAsText(file); //?
        });
    }
}

export default Home
