import React from 'react';
import { useNavigate } from 'react-router-dom';
import "./Anonymous.css";

function Anonymous() {
    const navigate = useNavigate();

    const handleAnonymousReport = () => {
        navigate('/report'); 
    };

    return (
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
    );
}

export default Anonymous;
