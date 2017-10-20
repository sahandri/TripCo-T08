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
		let information;
		let output;
		let total = 0;
		
		if (this.state.queryResults) {
		
			information = this.state.queryResults;	
			output = information.map(info => {
			total += info.distance;
		 	return ( 
		 	<tr>
		 	<td><h5>{info.name}</h5><p>{info.id},{info.latitude},{info.longitude},{info.elevation},{info.municipality}</p></td>
		 	<td>{info.distance}</td>
		 	<td>{total}</td>
		 	</tr>
		 	
		 	);
		 	})
			
			
		
		}
		
		if (this.state.svgResults) {
			svg = this.state.svgResults;
			renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
		}
		


        //let keys;
        
        return (<div className="home-container">
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
				<div>
				
				</div>

				



                <table className="pair-table"> {/*For CSS*/}
                
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
				description: " "
			}
		}

		try {

			let jsonReturned = await fetch(`http://localhost:4567/testing`,
			{
				method: "POST",
				body: JSON.stringify(clientRequest)
			});
			
			let ret = await jsonReturned.json();
			
			console.log("Got back ",ret);

			this.setState({
				queryResults: ret
			});
			

			
		} catch (e){
			console.error("Error talking to server");
			console.error(e);
		}
    }
    

}


export default Home
