import React, {Component} from 'react';
import Dropzone from 'react-dropzone'

class Home extends React.Component {
    render() {
		let total; //Initalize
		if(this.props.pairs == ""){ //If no json file
			total = 0;
		}
		else{ //after json call
			total = this.props.pairs[this.props.pairs.length - 1].props.tot;
		}
        return <div className="home-container">
            <div className="inner">
				<h2>T08 - The Absintees</h2>
                <h3>Itinerary</h3>	
				
                <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}>
                    <button>Open JSON File</button>
                </Dropzone>
                <table className="pair-table">
                    {this.props.pairs}
                    <tbody>
                        <tr>
                            <td colSpan="3">Total:</td>
                            <td>{total}</td>
                        </tr>
                    </tbody>
                </table>
            </div>
        </div>
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
                    this.props.browseFile(JsonObj);
                };
            })(file).bind(this);

            fr.readAsText(file);
        });
    }
}

export default Home
