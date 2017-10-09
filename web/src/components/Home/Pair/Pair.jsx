import React, {Component} from 'react';
let Pair = ({start, end, dist, tot, startInfo, endInfo }) => <tbody className="pair"> {/*classname for CSS*/} {/*Pair react with Start, End, Dist, Tot*/}
<tr> {/*Row*/}
    <td> {/* Columns*/}
        <h5>{start}</h5>
        <p>{Object.values(startInfo)}</p>
        {/*<p>{Object.keys(startInfo)[0]}</p>*/}
        {/*<p>{startInfo.City}</p>*/}
        {/*<p> {Object.keys(startInfo)} </p>*/}
    </td>
    <td>
        <h5>{end}</h5>
        <p>{Object.values(endInfo)}</p>
    </td>
    <td>
        <h5>{dist}</h5>
    </td>
    <td>
        <h5>{tot}</h5>
    </td>
</tr>
</tbody>;
export default Pair;
