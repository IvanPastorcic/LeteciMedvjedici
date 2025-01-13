import './HomePageAdmin.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';

const HomePageAdmin = () => {
    const [reports, setReports] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const [actions, setActions] = useState([]);
    const [actionsLoading, setActionsLoading] = useState(true);
    const [actionsError, setActionsError] = useState(null);

    const [searchReportName, setSearchReportName] = useState('')
    const handleInputChange = (e) =>{
        const searchTerm = e.target.value;
        setSearchReportName(searchTerm)
    }


    useEffect(() => {
        const fetchReports = async () => {
            try {
                const reportsResponse = await axios.get("http://localhost:8081/reports");
                setReports(reportsResponse.data);
                setLoading(false);
            } catch (error) {
                console.error("Error fetching data:", error);
                setError("Failed to load data");
                setLoading(false);
            }
        };
        // Fetch Actions
        const fetchActions = async () => {
            try {
                const response = await axios.get("http://localhost:8081/actions");
                setActions(response.data);
            } catch (error) {
                console.error("Error fetching actions:", error);
                setActionsError("Failed to load actions.");
            } finally {
                setActionsLoading(false);
            }
        };
        fetchActions();
        fetchReports();
    }, []);

    const navigateToMap = () => {
        navigate('/map');
    };

    const handleAnonymousReport = () => {
        navigate('/report');
    };

    return (
        <div className="HomePageAdmin">
            <div className="header">
                <AnonHeader />
            </div>

            <div className="buttonsHomePageAdmin">
               
                <button className="see-map-button" onClick={navigateToMap}>SEE MAP</button>
            </div>

            <div className="PageBodyAdmin">
                <div className='LeftSectionAdmin'>
                    
                    <h2>Users</h2>
                    <button className='addUser'>Add new user</button>


                </div>

                <div className="MiddleSectionAdmin">
                    <div className='search'>
                        <input type='text'
                        value={searchReportName}
                        onChange={handleInputChange}
                        placeholder='Type to search the reports'/>

                        <select  name="reportStatus">
                                <option value="Accepted">Accepted</option>
                                <option value="Processing">Processing...</option>
                                <option value="Denied">Denied</option>
                        </select>
                        <button className='changeStatus'>Change status</button>
                        
                    </div>
                    <h2>Reports</h2>
                    {loading ? (
                        <p>Loading reports...</p>
                    ) : error ? (
                        <p>{error}</p>
                    ) : (
                        <ReportComponent reports={reports} />
                    )}
                </div>
                <div className="RightSectionHome">
                    <div className='aid-section-name'>
                        <h2>AID ACTIONS:</h2>
                    </div>
                    {actionsLoading ? (
                        <p>Loading actions...</p>
                    ) : actionsError ? (
                        <p className="error">{actionsError}</p>
                    ) : (
                        <AidActions actions={actions} />
                    )}
                </div>
                </div>
                
            </div>
    );
};

export default HomePageAdmin;
