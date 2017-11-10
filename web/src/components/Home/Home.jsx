import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
import InlineSVG from 'svg-inline-react';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            results: null,
            selected: [],
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
		let selected;
		
		if (this.state.results) {
		
			information = this.state.results.itinerary;
			output = information.map(info => {
			total += info.distance;
			keys = Object.keys(info);
			values = Object.values(info);
			let out = "";
			values.forEach( val => {
				out += val + " , ";
			});
		 	return (
		 	<tr>
		 	<td><h5>{values[0]}</h5>
		 	<p>{out}</p></td>
		 	<button onClick={handleCode.bind(this,info.code)}>Select</button>
		 	
		 	{/*<td>{info.distance}</td>
		 	<td>{total}</td>*/}
		 	</tr>
		 	
		 	);
		 	})
			
			svg = this.state.results.svg;
			renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
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
                    <tr>
                        <td colSpan="3">Total:</td>
                        <td>{total}</td> {/*Displays Total*/}
                    </tr>
                    </tbody>
                </table>
				
				<h2> Selected Destinations</h2>
				<table className="pair-table">
				<tbody>
				{selected}
				</tbody>
				</table>
				<button onlClick={this.plan1.bind(this)}>Plan 1</button>
				<button onlClick={this.plan2.bind(this)}>Plan 2</button>
				<button onlClick={this.plan3.bind(this)}>Plan 3</button><p></p>
				
				<button onlClick={this.save.bind(this)}>Save</button>
				<Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <button>Load</button>
                </Dropzone>
				<button onlClick={this.clear.bind(this)}>Clear</button><p></p>
				<h2>Map</h2>
				<h1> 
					{renderedSvg}
				</h1>
				<div>
				
				</div>

				



                <table className="pair-table"> {/*For CSS*/}
                <h3>Itinerary</h3>
                    <tbody>
                    {output}
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
    
    handleCode(event, code){
    
		let bool = true;
		let sel = this.state.selected;
		for(let i = 0; i < sel.length; ++i){
			if( sel[i] === code){
				bool = false;
			}
		}
		if(sel && bool){
			sel.push(code);
			this.setState({
				selected: sel,
			})
		}
    }
    
	
	handleSubmit(event) {
		let input = this.state.input;
		this.fetch(input,"query");
		event.preventDefault();
	}
	plan1(event) {
		let input = this.state.selected;
		this.fetch(input,"plan1");
		event.preventDefault();
	}
	plan2(event) {
		let input = this.state.selected;
		this.fetch(input,"plan2");
		event.preventDefault();
	}
	plan3(event) {
		let input = this.state.selected;
		this.fetch(input,"plan3");
		event.preventDefault();
	}
	clear(event) {
		this.state.results = null;
		this.state.svgResults = null;
		this.state.selected = [];
		this.state.input = "";
		event.preventDefault();
	}
	save(event) {
		let input = this.state.selected;
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
                    
                    console.log(JsonObj);
                    
                    this.fetch(JsonObj,"load");
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
	
	
    async fetch(input, name) {
		let clientRequest;
        clientRequest = {
			method: name,
            description: input,
        };

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
