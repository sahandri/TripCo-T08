import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
import ReactModal from 'react-modal';

class Home extends React.Component {


    handleChange(event) {
        alert('hi');
    }



    render() { {/*Start of the HTML side of react*/}
		let total; {/*Initalize*/}
		if(this.props.pairs == ""){ {/*If no json file*/}
			total = 0;
		}
		else{ {/*after json call*/}
			total = this.props.pairs[this.props.pairs.length - 1].props.tot;
		}
        return <div className="home-container">
            <div className="inner">
				<h2>T08 - The Absentees</h2>
                <h3>Itinerary</h3>


                <modal>
                    <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}> {/*How to open the JSON file*/}
                        <button>Open JSON File 1</button>
                    </Dropzone>
                    <Dropzone className="dropzone-style" onDrop={this.drop2.bind(this)}> {/*How to open the JSON file*/}
                        <button>Open JSON File 2</button>
                    </Dropzone>
                </modal>

                <Dropzone className="dropzone-style" onDrop={this.drop_svg.bind(this)}> {/*How to open the JSON file*/}
                    <button>Open SVG File</button>
                </Dropzone>

                <form>
                    <label>
                        name
                        <input
                            name="name"
                            type="checkbox"
                            onChange={this.handleChange}/>
                    </label>
                    <label>
                        last name
                        <input
                            name="lastName"
                            type="checkbox"/>
                    </label>
                    <label>
                        other
                        <input
                            name="other"
                            type="checkbox"/>
                    </label>
                </form>



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

    drop(acceptedFiles) { {/*Calls Drop which takes the JSON file*/}
        console.log("Accepting drop");
        acceptedFiles.forEach(file => { {/*for each file accepted*/}
            console.log("Filename:", file.name, "File:", file); {/*output filename*/}
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
            console.log("Filename:", file2.name, "File:", file2); {/*output filename*/}
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


    drop_svg(acceptedFiles){
        console.log("Accepting drop");
        acceptedFiles.forEach(file => { {/*for each file accepted*/}
            console.log("Filename:", file.name, "File:", file); {/*output filename*/}
            console.log(JSON.stringify(file)); {/*output file as a string*/}
            let fr = new FileReader();
            fr.onload = (function () {
                return function (e) {
                    var url = e.target.result;
                    this.props.HandleImage(url);
                };
            })(file).bind(this);

            fr.readAsDataURL(file);
        });
    }
}

export default Home
