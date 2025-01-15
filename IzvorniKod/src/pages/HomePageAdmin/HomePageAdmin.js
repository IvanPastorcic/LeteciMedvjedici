import './HomePageAdmin.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import ProfileComponent from "../../components/ProfileComponent/ProfileComponent";
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
               
                <button className="see-map-button-admin" onClick={navigateToMap}>SEE MAP</button>
            </div>

            <div className="PageBodyAdmin">
                <div className="ReportSectionAdmin">
                    <div>
                        <h2>Reports</h2>
                        <div className='search'>
                            <input type='text'
                                value={searchReportName}
                                onChange={handleInputChange}
                                placeholder='Type to search the reports'/>
                        </div>
                        
                    </div>
                    
                    <div className='ReportsAdmin'>
                        {loading ? (
                            <p>Loading reports...</p>
                        ) : error ? (
                            <p>{error}</p>
                        ) : (
                            <ReportComponent reports={reports} />
                        )}

                    </div>
                    
                </div>

                

                <div className='UserSectionAdmin'>
                    
                    <h2>Users</h2>
                    <div className='searchAndEdit'>
                        <input type='text'
                            value={searchReportName}
                            onChange={handleInputChange}
                            placeholder='Type to search the users'/>

                        <button className='addUser'>Add new user</button>

                    </div>
                    <div className='UsersAdmin'>
                        {loading ? (
                            <p>Loading users...</p>
                        ) : error ? (
                            <p>{error}</p>
                        ) : (
                            <ProfileComponent reports={reports} />
                        )}

                    </div>


                </div>

                </div>
                
            </div>
    );
};

export default HomePageAdmin;
