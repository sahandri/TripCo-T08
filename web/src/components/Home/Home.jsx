import React, {Component} from 'react';
import Dropzone from 'react-dropzone'
import ReactModal from 'react-modal';

class Home extends React.Component {
    constructor(props) {
        super(props);
        this.state = {file: '',imagePreviewUrl: ''};
    }
    handleImageChange(e) {
        e.preventDefault();
        let reader = new FileReader();
        let file = e.target.files[0];
        reader.onloadend = () => {
            this.setState({
                file: file,
                imagePreviewUrl: reader.result
            });
        }
        reader.readAsDataURL(file)
    }




    render() { {/*Start of the HTML side of react*/}
        {/*for image*/}
        let {imagePreviewUrl} = this.state;
        let $imagePreview = null;
        if (imagePreviewUrl) {
            $imagePreview = (<img src={imagePreviewUrl} />);
        } else {
            $imagePreview = (<div className="previewText">Please select a csv file for Preview</div>);
        }


        let total; {/*Initalize*/}
        let keys;
        if(this.props.pairs == ""){ {/*If no json file*/}
            total = 0;
        }
        else{ {/*after json call*/}
            total = this.props.pairs[this.props.pairs.length - 1].props.tot;
            keys =  this.props.pairs[this.props.pairs.length - 1].props.keys;
        }
        return <div className="home-container">
            <div className="inner">
                <h2>T08 - The Absentees</h2>
                <h3>Itinerary</h3>
                <img src={this.props.pairs.url}/>


                <modal>
                    <Dropzone className="dropzone-style" onDrop={this.drop.bind(this)}> {/*How to open the JSON file*/}
                        <button>Short trips</button>
                    </Dropzone>
                    <Dropzone className="dropzone-style" onDrop={this.drop2.bind(this)}> {/*How to open the JSON file*/}
                        <button>Full csv</button>
                    </Dropzone>
                </modal>




                {/*for image*/}
                <div className="previewComponent">

                    <input className="CSV file Input"
                           type="file"
                           onChange={(e)=>this.handleImageChange(e)} />
                    <div className="imgPreview">
                        {$imagePreview}
                    </div>
                </div>





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
