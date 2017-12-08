import React, {Component} from 'react';
import Select from 'react-select';
import ReactTable from 'react-table';
import Dropzone from 'react-dropzone';
import InlineSVG from 'svg-inline-react';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            results: null,
            answer: null,
            plan: null,
            itinerary: [],
            columns: [],
            all: [],
		svgResults: null,
		input : "",
        value: ["ID", "Name"]
		}
    };




    render() { {/*Start of the HTML side of react*/}
        let svg;
		let renderedSvg;
		let information;
		let total = 0;
		let planout;
		let amount;
		let plans = null;
		
		if (this.state.results) {
				
				information = this.state.results.itinerary;
				this.state.all = [];
				amount = "Number of Results: ";
				amount += information.length;
                var arrKeys = [];
                arrKeys = Object.keys(this.state.results.itinerary[0]);
                
                for(var i=0;i<arrKeys.length;i++){
                        this.state.columns[i]={ label: arrKeys[i], value: arrKeys[i] };
                }

		}
		
			if(this.state.plan){
			let number = -1;
            plans = information.map(info => {
                number++;
                return (
                <tr>
                <td><h5>{info.name}</h5>
                <p>{info.id}</p></td>
                <button onClick={this.up.bind(this,number)}>Up</button>
                <button onClick={this.down.bind(this,number)}>Down</button>
                <button onClick={this.remove.bind(this,number)}>Remove</button>
                </tr>
        
                );
        
            })
            
            if(this.state.plan.svg){
                svg = this.state.plan.svg;
                renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
            
            
            }
            }
            if(this.state.answer){
                information = this.state.answer.itinerary;
            
                planout = information.map(info => {
                    total += info.distance;
                    return (
                        <tr>
                        <td><h5>{info.name}</h5>
                        <p>{info.id}</p></td><td>{info.distance}</td> <td>{total}</td>
                        </tr>
                    );
        
                })
                
                if(this.state.answer.svg){
                    svg = this.state.answer.svg;
                    renderedSvg = <InlineSVG src={svg.contents}></InlineSVG>;
                }
            }

        return (<div className="home-container">
            <div className="inner">
                <h2>T08 - The Absentees</h2>
				<h2> Search Phrase</h2>
				<form onSubmit={this.handleSubmit.bind(this)}>
				<input size="35" className="search-button" type="text"
					onKeyUp={this.keyUp.bind(this)} placeholder="Enter Keyword" autoFocus/>
				<input type="submit" value="Submit" />
				</form>
                <Select
                    closeOnSelect={true}
                    disabled={false}
                    multi
                    onChange={this.handleColumnChange.bind(this)}
                    options={this.state.columns}
                    placeholder="Select data columns"
                    removeSelected={true}
                    rtl={false}
                    simpleValue
                    value={this.state.value}
                />
                    
                
				<h2> Search Results</h2>
				<h3> {amount} </h3>
                <ReactTable
                    data={information}
                    columns={columns}
                />
				
				<h2> Selected Destinations</h2>
				<table className="pair-table">
				<tbody>
				{plans}
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

                <h3>Itinerary</h3>
                <table className="pair-table"> {/*For CSS*/}
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
    
    handleColumnChange(value){
        console.log("Value: ", value);
        console.log("this.state: ", this.state);
        console.log("this.state.value: ", this.state.value);
        this.setState({value});
    }

    keyUp(event){
			this.setState({
				input: event.target.value
			});
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
	up(num){
		let arr = this.state.itinerary;
		if(num > 0){
			let temp = arr[num-1];
			arr[num-1] = arr[num];
			arr[num] = temp;
		}
		this.setState({itinerary: arr });
		console.log(this.state.itinerary);
	}
	down(num){
	let arr = this.state.itinerary;

		if(num < (arr.length - 1)){
			let temp = arr[num+1];
			arr[num+1] = arr[num];
			arr[num] = temp;
		}
		this.setState({itinerary: arr });
		console.log(this.state.itinerary);
	
	}
	remove(num){
		let arr = this.state.itinerary;
		arr.splice(num, 1);
		this.setState({itinerary: arr });
		console.log(this.state.itinerary);
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
		event.preventDefault();
	}
	plan2(event) {
		let input = this.state.itinerary;
		let str = this.arrToString(input);
		this.fetch(str,"plan2");
		event.preventDefault();
	}
	plan3(event) {
		let input = this.state.itinerary;
		let str = this.arrToString(input);
		this.fetch(str,"plan3");
		event.preventDefault();
	}
	clear(event) {


		this.state.results = null;
		this.state.answer = null;
		this.state.plan = null;
		this.state.svgResults = null;
		this.state.itinerary = [];
		this.state.all = [];
		this.state.input = "";
		this.state.svgResults = null;
		this.forceUpdate();
		event.preventDefault();
	}
	
	arrToString(arr){
		let str = arr.join();
		return str;
	
	}
	
	save(event) {
		this.getFile();
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
                    var hold = this.arrToString(JsonObj.destinations);
                    this.fetch(hold,"plan");
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
    
        async getFile() {
         // assign all the airport codes of the displayed locations to an array
         let locs = this.state.itinerary

        // create an object in the format of the download file:
        let locationFile = {
            title : "selection",
            destinations: locs
        };

        // stringify the object
        let asJSONString = JSON.stringify(locationFile);
        
        // Javascript code to create an <a> element with a link to the file
        let pom = document.createElement('a');
        pom.setAttribute('href', 'data:text/plain;charset=utf-8,' + encodeURIComponent(asJSONString));
        // Download the file instead of opening it:
        pom.setAttribute('download', "download.json");
        
        // Javascript to click the hidden link we created, causing the file to download
        if (document.createEvent) {
            let event = document.createEvent('MouseEvents');
            event.initEvent('click', true, true);
            pom.dispatchEvent(event);
        } else {
            pom.click();
        }
        
        // remove hidden link from page
        pom.parentNode.removeChild(pom);
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
			if(name == "search"){
				
				this.setState({
				    results: ret
				});
			}
			if(name == "plan1" || name == "plan2" || name == "plan3"){
				this.setState({
					answer: ret
				});
			}
			if(name == "plan"){
				this.setState({
					plan: ret
				});
			
			}

		} catch (e){
			console.error("Error talking to server");
			console.error(e);
		}
    }

    

}


export default Home
