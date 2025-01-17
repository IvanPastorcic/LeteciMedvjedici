import './ProfilePageAdmin.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import BackButton from '../../components/BackButton/BackButton';


const ProfilePageAdmin = () => {

    const [showInfo, setShowInfo] = useState(false);
    const [reports, setReports] = useState([]); 
    const [loading, setLoading] = useState(true); // Loading state
    const [error, setError] = useState(null); 
    const navigate = useNavigate();


    useEffect(() => {
        
        const fetchReports = async () => {
            try {
                const response = await axios.get("http://localhost:8081/reports"); 
                setReports(response.data); 
                setLoading(false); 
            } catch (error) {
                console.error("Error fetching reports:", error);
                setError("Failed to load reports"); 
                setLoading(false); 
            }
        };
        fetchReports(); 
    }, []); 


    const [user, setUser] = useState([  
            {id: 1, username : "Marko", email: "marko.markic@gmail.com"}
        ])

    

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    const handleChangePassword = () =>{

    };

    return ( 
            <div className="ProfilePage">

               
                <div className='header'>
                    <AnonHeader /> 
                </div>
                
                <BackButton/>
                
                <div className="PageBodyProfile">
 
                   
                        <div className="LeftSectionProfile">
                            <div className='User'>
                                <div classname="user-icon"/>
                                
                                <div className='UserInfo'>
                                    <p className='username-profile'>Username</p>
                                    <p className='mail-profile'>mail@gmail.com</p>
                                </div>
                            </div>
                            <div className='UserEdit'>
                                <p>Edit your username:</p>
                                <input type='text' className='pass-input'></input>
                                <button className='button-profile'>
                                    change password
                                </button>
                                <hr></hr>
                                <p>Want to delete your personal data?</p>
                                <button className='button-profile'>
                                    Delete data
                                </button>
                            </div>

                            
                        </div>
                        
                        
                        <div className="RightSectionProfile">
                            <p className='my-reports'>My reports:</p>
                            {loading ? (
                                <p>Loading reports...</p> 
                            ) : error ? (
                                <p>{error}</p> 
                            ) : (
                                <ReportComponent reports={reports} /> 
                            )}
                        </div>

                    </div>
                
                
            </div>
 
     );
}
 
export default ProfilePageAdmin;