import './HomePage.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
//import Report from './Report';
//import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const HomePage = () => {

    const navigate = useNavigate();

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };
    return ( 
            <div className="HomePage">
            <AnonHeader /> 
            <div className="buttonsHomePage">
                <button className="information-button"> INFORMATION </button>
                <button className="report-button" 
                onClick={handleAnonymousReport}> REPORT </button>
                <button className="see-map-button"> see map </button>
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

                            <ReportComponent/> 
                            <ReportComponent/> 
                            <ReportComponent/> 
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