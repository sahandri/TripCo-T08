import React, {Component} from 'react';
import Select from 'react-select';
import ReactTable from 'react-table';
import 'react-table/react-table.css';
import Dropzone from 'react-dropzone';
import InlineSVG from 'svg-inline-react';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
        	option: 0,
            results: null,
            answer: null,
            plan: null,
            itinerary: [],
            columns: [],
            all: [],
		svgResults: null,
		input : "",
        value: ["id", "name"]
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
        let rows;
        let tripRows = [];
		
		if (this.state.results) {
                rows = this.state.results.itinerary;
				
				information = this.state.results.itinerary;
				this.state.all = information.map(info => {
                    return info.id;
                });
				amount = "Number of Results: ";
				amount += information.length;
                var arrKeys = [];
                arrKeys = Object.keys(this.state.results.itinerary[0]);
                
                for(var i=0;i<arrKeys.length;i++){
                        this.state.columns[i]={ label: arrKeys[i], value: arrKeys[i] };
                }

		}
		
        if(this.state.plan){
            information = this.state.plan.itinerary;
            this.state.itinerary = information.map(info => {
                return info.id;
            });

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
                information.map((info) => {
                    total += info.distance;
                    info.cumulativeDistance = total;
                    return info;
                });
                tripRows = information;
            
                planout = information.map(info => {
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

        const columns = [{
            Header: 'Select',
            accessor: 'id',
            Cell: row => (
                <button onClick={this.handleCode.bind(this,row.value)}>
                    Select
                </button>
            )}].concat( 
            this.state.value.map(d => {
                return {
                    Header: d,
                    accessor: d
                }
            }))

        const tripColumns = [{
            Header: 'Cumulative Distance',
            accessor: 'cumulativeDistance',
            Footer: 'Total: ' + total
            }].concat( 
            this.state.value.map(d => {
                return {
                    Header: d,
                    accessor: d
                }
            }))

        return (<div className="home-container">
            <div className="inner">
                <h2>T08 - The Absentees</h2>
				<h2> Search Phrase</h2>
				<form onSubmit={this.handleSubmit.bind(this)}>
				<input size="35" className="search-button" type="text"
					onKeyUp={this.keyUp.bind(this)} placeholder="Enter Keyword" autoFocus/>
				<input type="submit" value="Submit" />
				</form>
				<p>Select what info you want to show</p>
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
				<p> Select which you want to add to the plan, or press the select all button under this table</p>
                <ReactTable
                    data={rows}
                    columns={columns}
                    className="-striped -highlight"
                />
          <p></p>
                <button onClick={this.all.bind(this)}>Select All</button>
				
				<h2> Selected Destinations</h2>
				<p>*Note - Selected Destinations don't re-render on up,down or remove.  However, the changes are saved for when you press plan*</p>
				<table className="pair-table">
				<tbody>
				{plans}
				
				</tbody>
				</table>
				<p>Click plan to create a selected destination table</p>
				<button onClick={this.plan.bind(this)}>Plan</button>
				<p>*Mile is default*</p>
				<button onClick={this.mile.bind(this)}>Miles</button>
				<button onClick={this.km.bind(this)}>KM</button><p></p>
				
				<button onClick={this.plan1.bind(this)}>Nearest Neighbor</button>
				<button onClick={this.plan2.bind(this)}>2 Opt</button>
				<button onClick={this.plan3.bind(this)}>3 Opt</button><p></p>
				
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
                <ReactTable
                    data={tripRows}
                    columns={tripColumns}
                    defaultPageSize={100}
                    className="-striped -highlight"
                />
            </div>
        </div>
        )
    }
    
    handleColumnChange(vals){
        this.setState({value: vals.split(',')});
        console.log("this.state.value: ", this.state.vals);
    }

    keyUp(event){
			this.setState({
				input: event.target.value
			});
    }
   
    
 
       handleCode(code){
        console.log("Handling code: ", code);
    		
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
        console.log(this.state.itinerary);
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
	
	mile(event){
		this.setState({option: 0});
		console.log(this.state.option);
	}
	
	km(event){
		this.setState({option: 1});
		console.log(this.state.option);
	
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
    		let uni = this.state.option;
		let clientRequest;
        clientRequest = {
		request: name,
            	description: input,
            	units: uni
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
