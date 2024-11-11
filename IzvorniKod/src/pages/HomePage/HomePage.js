import './HomePage.css';
import Info from './Info';
import AidActions from './AidActions'
import Report from './Report';
//import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const HomePage = () => {
    return ( 
            <div className="HomePage">
            {/*<AnonHeader /> */}
            <div className="buttonsHomePage">
                <button className="information-button"> INFORMATION </button>
                <button className="report-button"> REPORT </button>
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

                            <Report/> 
                            <Report/> 
                            <Report/> 
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