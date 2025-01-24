import './ProfilePage.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import BackButton from '../../components/BackButton/BackButton';


const ProfilePage = () => {

    const [showInfo, setShowInfo] = useState(false);
    const [reports, setReports] = useState([]); 
    const [loading, setLoading] = useState(true); // Loading state
    const [error, setError] = useState(null); 
    const [user, setuUser] = useState([]);
    const navigate = useNavigate();


    useEffect(() => {
        
        const fetchUser = async () => {
            try {
                const response = await axios.get("http://localhost:8081/user", {withCredentials: true});
                setuUser(response.data);
                console.log(response.data);

                
             
            } catch (error) {
                console.error("Error fetching users: ", error);
                setError("Failed to load user");
            }

          
        }
        /*const fetchReports = async () => {
            try {
                const response = await axios.get(`http://localhost:8081/user/${user.id}/reports`, {withCredentials: true}); 
                
                setReports(response.data); 
                setLoading(false); 
            } catch (error) {
                console.error("Error fetching reports:", error);
                setError("Failed to load reports"); 
                
                setLoading(false); 
            }
        };
       */
         
        fetchUser();
       // fetchReports();
        
    }, []); 


   
    const fetchReports = async (id) => {
        try {
            const response = await axios.get(`http://localhost:8081/user/${id}/reports`, {withCredentials: true}); 
            
            setReports(response.data); 
            setLoading(false); 
        } catch (error) {
            console.error("Error fetching reports:", error);
            setError("Failed to load reports"); 
            
            setLoading(false); 
        }
    };
   

 

    

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    const handleChangeUsername = async () => {
        const newUsername = document.querySelector('.pass-input').value;
        if (!newUsername) {
            alert('Please enter a new username');
            return;
        }
    
        try {
            const response = await axios.patch("http://localhost:8081/user/edit", {
                username: newUsername
            }, { withCredentials: true });
            
            setuUser(prevUser => ({ ...prevUser, username: response.data.username }));
            alert('Username updated successfully');
        } catch (error) {
            console.error("Error updating username: ", error);
            alert('Failed to update username');
        }
    };

    const handleDeleteAccount = async () => {
        try{
        await axios.delete(`http://localhost:8081/user/${user.id}`, { withCredentials: true });
        } catch (error) {
            console.error("Error deleting your account. ", error);
            alert("Failed to delete your account.");
        }
    }

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
                                    <p className='username-profile'>{user.username}</p>
                                    <p className='mail-profile'>{user.email}</p>
                                    <p className="userRole">{user.role}</p>
                                </div>
                            </div>
                            <div className="UserEdit">
                                <div className="EditUsernameSection">
                                    <p>Edit your username:</p>
                                    <input type='text' className='pass-input'></input>
                                    <button className='button-profile' onClick={handleChangeUsername}>
                                        Change username
                                    </button>
                                </div>

                                <hr />

                
                                <div className="DeleteAccountSection">
                                    <p>Want to delete your account?</p>
                                    <button className='button-profile delete-btn' onClick={handleDeleteAccount}>
                                        Delete data
                                    </button>
                                </div>
                            </div>

                            
                        </div>
                        
                        
                        <div className="RightSectionProfile">
                            <button onClick={() =>fetchReports(user.id)}>Fetch my reports</button>
                            <p className='my-reports'>My reports:</p>
                            {loading ? (
                                <p></p> 
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
 
export default ProfilePage;