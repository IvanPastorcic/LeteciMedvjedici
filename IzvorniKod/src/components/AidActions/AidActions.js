//import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const AidActions = (props) => {

    const actions = props.actions;
    console.log(props, actions);

    return (  
        <div>
            {actions.map((action)=>(  
                <div className="AidActionItem">
                    <div className="AidDateName">
                        
                        <h4>{action.actionName}</h4>
                    </div>
                        <p>{action.actionDescription}</p>
                        <p>At location: {action.settlement.settlementName}</p>
                        <p>Organised by: {action.humanitarianOrganization.organizationName}</p>
                </div>
            ))}
        </div>
  
     );
}
 
export default AidActions;