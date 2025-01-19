import React from 'react';
import { useNavigate } from 'react-router-dom';
import "./Anonymous.css";

function Anonymous() {
    const navigate = useNavigate();

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    const handleTrackStatusAnon = () => {
        navigate('/track-status'); 
    };
    

    return (
        <div className='anon'>
            <div className="Anonymous">
                <p>Want to report an emergency anonymously?</p>
                <button 
                    type="submit" 
                    name='anonymous-button' 
                    onClick={handleAnonymousReport} 
                >
                    Anonymous report
                </button>
            </div>

            <div className='Anon-track-status'>
                <p>Want to check the status of your report?</p>
                <button 
                    type="submit" 
                    name='track-status-button-anon' 
                    onClick={handleTrackStatusAnon} 
                >
                    Check status
                </button>
            </div>
        </div>
    );
}

export default Anonymous;
