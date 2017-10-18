import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
import InlineSVG from 'svg-inline-react';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
			queryResults: [],
			svgResults: null,
			input : ""
		}
    };




    render() { {/*Start of the HTML side of react*/}
        let svg = "sprint2airport.svg";
		let renderedSvg;
		let serverLocations;
		let locs;
		
		if (this.state.queryResults) {
			serverLocations = this.state.queryResults;
			locs = serverLocations.map((location) => {
				console.log(location.name);
				return <li>{location.name}</li>;
			});
		}
		
		if (this.state.svgResults) {
			svg = this.state.svgResults;
			renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
		}
		


        let total;
        //let keys;
        if(this.props.pairs == ""){ {/*If no json file*/}
            total = 0;
        }
        else{ {/*after json call*/}
            total = this.props.pairs[this.props.pairs.length - 1].props.tot;
            //keys =  this.props.pairs[this.props.pairs.length - 1].props.Object.keys(startInfo);
        }
        return <div className="home-container">
            <div className="inner">
                <h2>T08 - The Absentees</h2>
                <h3>Itinerary</h3>

				<form onSubmit={this.handleSubmit.bind(this)}>
				<input size="35" className="search-button" type="text"
					onKeyUp={this.keyUp.bind(this)} placeholder="Enter Keyword" autoFocus/>
				<input type="submit" value="Submit" />
				</form>
				
				<button type="button" onClick={this.buttonClicked.bind(this)}>Click here for an SVG</button>
				
				
				<h1>
					{renderedSvg}
				</h1>

				



                <table className="pair-table"> {/*For CSS*/}
                    {this.props.pairs} {/*Calls Pair.jsx for HTML script*/}
                    <tbody>
                    <tr>
                        <td colSpan="3">Total:</td>
                        <td>{total}</td> {/*Displays Total*/}
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    }
                    
    keyUp(event){
		if (event.which === 13) {
			this.fetch("query", this.state.value);
        } else {
			this.setState({
				input: event.target.value
			});
		}
    }
	
	handleSubmit(event) {
		this.fetch("query", this.state.input);
		event.preventDefault();
	}
	
	buttonClicked(event) {
		this.fetch("svg", event.target.value);
	}
	
    async fetch(type, input) {
		let clientRequest;
		if (type === "query") {
			clientRequest = {
				request: "query",
				description: input,
			};
		} else {
			clientRequest = {
				request: "svg",
				description: ""
			}
		}
		try {
			let jsonReturned = await fetch(`http://localhost:8080/testing`,
			{
				method: "POST",
				body: JSON.stringify(clientRequest)
			});
			
			let ret = await jsonReturned.json();
			let returnedJson = JSON.parse(ret);
			console.log("Got back ",returnedJson);
			
			if(returnedJson.response === "query") {
				this.setState({
					queryResults: returnedJson.locations
				});
			} else {
				this.setState({
					svgResults: JSON.parse(ret)
				});
			}
			
		} catch (e){
			console.error("Error talking to server");
			console.error(e);
		}
    }

    drop(acceptedFiles) { {/*Calls Drop which takes the JSON file*/}
        console.log("Accepting drop");
        acceptedFiles.forEach(file => { {/*for each file accepted*/}
            console.log("Filename:", file.Name, "File:", file); {/*output filename*/}
            console.log(JSON.stringify(file)); {/*output file as a string*/}
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);

                    console.log(JsonObj); {/*Output Json object*/}
                    this.props.browseFile(JsonObj); {/*Calls Browsefile in ap.js file*/}
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }

    drop2(acceptedFiles) { {/*Calls Drop which takes the JSON file*/}
        console.log("Accepting drop");
        acceptedFiles.forEach(file2 => { {/*for each file accepted*/}
            console.log("Filename:", file2.Name, "File:", file2); {/*output filename*/}
            console.log(JSON.stringify(file2)); {/*output file as a string*/}
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    console.log(JsonObj); {/*Output Json object*/}
                    this.props.browseFile2(JsonObj); {/*Calls Browsefile in ap.js file*/}
                };
            })(file2).bind(this);

            fr.readAsText(file2);
        });
    }

}


export default Home
