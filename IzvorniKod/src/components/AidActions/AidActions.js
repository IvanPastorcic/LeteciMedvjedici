//import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const AidActions = (props) => {

    const aids = props.aids;
    console.log(props, aids);

    return (  
        <div>
            {aids.map((aid)=>(  
                <div className="AidActionItem">
                    <div className="AidDateName">
                        <text className="aid-date">{aid.date}</text>
                        <h4>{aid.organisationName}</h4>
                    </div>
                        <ul>
                        <li>{aid.aidInfo}</li>
                        <li>hrana</li>
                        <li>...</li>
                        </ul>
                </div>
            ))}
        </div>
  
     );
}
 
export default AidActions;