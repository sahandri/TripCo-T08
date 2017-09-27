import React, {Component} from 'react';

class Pair extends React.Component{

    constructor(props){
        super(props);
        this.state = {
            Pair:{}

        }
    };

    render(){
        Pair:{this.props.start, this.props.end, this.props.dist, this.props.tot }
        return(
            <tbody className="pair">
                <tr> {/*Row*/}
                    <td> {/* Columns*/}
                        <h5>{this.props.start}</h5>
                    </td>
                    <td>
                        <h5>{this.props.end}</h5>
                    </td>
                    <td>
                        <h5>{this.props.dist}</h5>
                    </td>
                    <td>
                        <h5>{this.props.tot}</h5>
                    </td>
                </tr>
            </tbody>
        )
    }
}


export default Pair;
