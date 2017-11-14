import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
import InlineSVG from 'svg-inline-react';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            results: null,
            plan: null,
            itinerary: [],
            all: [],
			svgResults: null,
			input : ""
		}
    };




    render() { {/*Start of the HTML side of react*/}
        let svg;
		let renderedSvg;
		let information;
		let keys;
		let output;
		let total = 0;
		let itinerary;
		let planout;
		
		if (this.state.results) {
				
				information = this.state.results.itinerary;
				console.log(information);
				this.state.all = [];
				output = information.map(info => {
				total += info.distance;
				this.state.all.push(info.id);
				keys = Object.keys(info);
			 	return (
			 	<tr>
			 	<td><h5>{info.name}</h5>
			 	<p>{info.id}</p></td>
			 	<button onClick={this.handleCode.bind(this,info.id)}>Select</button>
			 
			 	</tr>
			 	
			 	);
			 	})
				if(this.state.results.svg){
				svg = this.state.results.svg;
				renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
				}
				if(this.state.plan){
				total = 0;
					planout = information.map(info => {
					total += info.distance;
						return (
						<tr>
						<td><h5>{info.name}</h5>
						<p>{info.id},{info.elevation},{info.region},{info.country},{info.continent}</p></td>
						<td>{info.distance}</td>
						<td>{total}</td>
						</tr>
				
						);
				
					})
				
				}
				
		}
		


        //let keys;
        
        return (<div className="home-container">
            <div className="inner">
                <h2>T08 - The Absentees</h2>
                

				<h2> Search Phrase</h2>
				<form onSubmit={this.handleSubmit.bind(this)}>
				<input size="35" className="search-button" type="text"
					onKeyUp={this.keyUp.bind(this)} placeholder="Enter Keyword" autoFocus/>
				<input type="submit" value="Submit" />
				</form>
				<h2> Search Results</h2>
				<table className="pair-table"> {/*For CSS*/}
                    <tbody>
                    {output}
                    </tbody>
                </table>
				
				<h2> Selected Destinations</h2>
				<table className="pair-table">
				<tbody>
				{itinerary}
				<button onClick={this.all.bind(this)}>Select All</button>
				</tbody>
				</table>
				<button onClick={this.plan.bind(this)}>Plan</button>
				<button onClick={this.plan1.bind(this)}>Plan 1</button>
				<button onClick={this.plan2.bind(this)}>Plan 2</button>
				<button onClick={this.plan3.bind(this)}>Plan 3</button><p></p>
				
				<button onClick={this.save.bind(this)}>Save</button>
				<Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <button>Load</button>
                </Dropzone>
				<button onClick={this.clear.bind(this)}>Clear</button><p></p>
				<h2>Map</h2>
				<h1> 
					{renderedSvg}
				</h1>
				<div>
				
				</div>

				



                <table className="pair-table"> {/*For CSS*/}
                <h3>Itinerary</h3>
                    <tbody>
                    {planout}
                    <tr>
                        <td colSpan="3">Total:</td>
                        <td>{total}</td> {/*Displays Total*/}
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
        )
    }

                    
    keyUp(event){
		if (event.which === 13) {
			this.fetch(this.state.value);
        } else {
			this.setState({
				input: event.target.value
			});
		}
    }
    
 
       handleCode(code){
    		
		let bool = true;
		let sel = this.state.itinerary;
		for(let i = 0; i < sel.length; ++i){
			if( sel[i] === code){
				bool = false;
			}
		}
		if(sel && bool){
			sel.push(code);
			this.setState({
				itinerary: sel,
			})
		}
    }
	
	handleSubmit(event) {
		let input = this.state.input;
		this.fetch(input,"search");
		event.preventDefault();
	}
	all(event) {
		this.state.itinerary = this.state.all;
	}
	plan(event) {
		let input = this.state.itinerary;
		let str = this.arrToString(input);
		this.fetch(str, "plan");
		event.preventDefault();
	}
	plan1(event) {
		let input = this.state.itinerary;
		let str = this.arrToString(input);
		this.fetch(str,"plan1");
		this.state.plan = "plan1";
		event.preventDefault();
	}
	plan2(event) {
		let input = this.state.itinerary;
		console.log(input);
		let str = this.arrToString(input);
		this.fetch(str,"plan2");
		this.state.plan = "plan2";
		event.preventDefault();
	}
	plan3(event) {
		let input = this.state.itinerary;
		let str = this.arrToString(input);
		this.fetch(str,"plan3");
		this.state.plan = "plan3";
		event.preventDefault();
	}
	clear(event) {
		this.state.results = null;
		this.state.svgResults = null;
		this.state.itinerary = [];
		this.state.input = "";
		this.forceUpdate();
		event.preventDefault();
	}
	
	arrToString(arr){
		let str = arr.join();
		return str;
	
	}
	
	save(event) {
		let input = this.state.itinerary;
		this.fetch(input,"save");
		event.preventDefault();
	}
	drop(acceptedFiles) {
        console.log("Accepting drop");
        acceptedFiles.forEach(file => {
            console.log("Filename:", file.name, "File:", file);
            console.log(JSON.stringify(file));
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    let JsonObj = JSON.parse(e.target.result);
                    
                    console.log(JsonObj.destinations);
                    this.arrToString(JsonObj.destinations);
                    this.fetch(JsonObj.destinations,"plan");
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
	
	
    async fetch(input, name) {
		let clientRequest;
        clientRequest = {
		request: name,
            	description: input,
        };
        console.log(clientRequest);

		try {
            let serverUrl = window.location.href.substring(0, window.location.href.length - 6) + ":4567/testing";
            console.log(serverUrl);
			let jsonReturned = await fetch(serverUrl,
			{
				method: "POST",
				body: JSON.stringify(clientRequest)
			});
			
			let ret = await jsonReturned.json();
			
			console.log("Got back ",ret);
			this.setState({
			    results: ret
			});

		} catch (e){
			console.error("Error talking to server");
			console.error(e);
		}
    }

    

}


export default Home
