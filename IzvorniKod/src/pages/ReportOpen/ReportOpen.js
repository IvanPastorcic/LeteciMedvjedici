import './ReportOpen.css';
import Info from "../../components/Info/Info";
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ReportComponent from "../../components/Report/ReportComponent";
import AidActions from "../../components/AidActions/AidActions";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import { FaArrowLeft, FaFire, FaWater, FaBolt, FaMountain, FaHome } from "react-icons/fa";
import BackButton from '../../components/BackButton/BackButton';


const ReportOpen = () => {

    
    const [showInfo, setShowInfo] = useState(false);
   // const [reports, setReports] = useState([]); 
    const [loading, setLoading] = useState(true); // Loading state
    const [error, setError] = useState(null); 
    const navigate = useNavigate();

//dummy data samo za prikaz

    const [reports, setReports] = useState([  
        {id: 1, date : "26 Oct 2024 10:35", username: "Marko", disasterType:"FIRE", area:"Zagreb", information: "informacije o pozaru"},

    ])

    const [aids, setAids] = useState([  
        {id: 1, date : "26 Oct 2024 10:35", organisationName: "THE RED CROSS", aidInfo: "informacije o sklonistima"},
        {id: 2, date : "27 Oct 2024 11:00", organisationName: "ORGANISATION2", aidInfo: "informacije o HRANI"},
        {id: 3, date : "28 Oct 2024 15:58", organisationName: "ORGANISATION3", aidInfo: "informacije o VODI"}
    ])
    
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


    

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    const navigateToMap = () => {
        navigate('/map'); 
    };

    const handleInformation = () => {
        setShowInfo(!showInfo); 
    };

    
    
    return ( 
            <div className="ReportOpen">
                <div className='header'>
                    <AnonHeader /> 
                </div>

            
                <div className="PageBodyHome">
                        

                        <div className="MiddleSectionHome">
                            <div className="BackReportOpen">
                                <BackButton/>
                            </div>
                            <div className="Report">

                                {reports.map((report) => (

                                    <div classname="ReportPreview" key ={report.id}>
                                         <div className="ReportDateName">
                                    <text className="aid-date">{report.date}</text>
                                    <text className="username">{report.username}</text>
                                </div>

                                <h2>{report.disasterType} REPORT -  {report.area} AREA</h2>
                                <text>{report.information}</text>
                                <div classname="ReportImages">
                                    </div>
                                        </div>

                               

                                ))}


                            </div>
                        
                           
                        </div>
                        
                       

                </div>
                
                
            </div>




       
        
     );
}
 
export default ReportOpen;