import './HomePage.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import { useState } from 'react';
//import Report from './Report';
//import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const HomePage = () => {

    const [reports, setReport] = useState([  
        {date : "26 Oct 2024 10:35", username: "username", disasterType: "FLOOD", area: "X", description: "opis korisnika"},
        {date : "27 Oct 2024 11:00", username: "Pero", disasterType: "EARTHQUAKE", area: "ZAGREB", description: "tresle su se kuce"},
        {date : "28 Oct 2024 15:58", username: "User123", disasterType: "FIRE", area: "OSIJEK", description: "istocna strana"}
    ]

    )

    const navigate = useNavigate();

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    const navigateToMap = () => {
        navigate('/map'); 
    };
    return ( 
            <div className="HomePage">
            <AnonHeader /> 
            <div className="buttonsHomePage">
                <button className="information-button"> INFORMATION </button>
                <button className="report-button" 
                onClick={handleAnonymousReport}> REPORT </button>
                <button className="see-map-button"
                onClick={navigateToMap}> see map </button>
            </div>
                    <div className="PageBody">
                        
                    
                        <div className="LeftSection">
                            
                            <Info />
                            
                        </div>

                        <div className="MiddleSection">
                            <div className="Filter">
                                <text>Filter by:</text>
                                <button className="filter-button"> date posted </button>
                                <button className="filter-button"> location </button>

                            </div>

                            <ReportComponent reports={reports}/> 
                        </div>
                        
                        <div className="RightSection">
                            <h2>AID ACTIONS:</h2>
                            <br />
                            <AidActions/>
                            <AidActions/>
                            <AidActions/>
                        </div>

                    </div>
                
                
            </div>




       
        
     );
}
 
export default HomePage;