import './HomePage.css';
import Info from './Info';
import AnonHeader from "./components/AnonHeader/AnonHeader";
import { BrowserRouter as Router, Route, Routes} from "react-router-dom";

const HomePage = () => {
    return ( 
        <Router>
            <div className="HomePage">
            <AnonHeader /> 
            <div className="buttonsHomePage">
                <button className="information-button"> click me </button>
            </div>
            <Routes>
                <Route 
                path="/" 
                element={
                    <div className="PageBody">
                    
                        <div className="LeftSection">
                            
                            <Info />
                            
                        </div>

                        <div className="MiddleSection">
                            
                            <h1>HI IAM MIDDLE</h1>
                            
                        </div>
                        
                        <div className="RightSection">

                        </div>

                    </div>
                }
                />
            </Routes>
            </div>
        </Router>




       
        
     );
}
 
export default HomePage;