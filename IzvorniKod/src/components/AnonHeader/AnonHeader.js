import React from 'react';
import './AnonHeader.css';
import { Link } from "react-router-dom";



function AnonHeader() {
    return (
        <header className="anon-header">
            <img src="/logo.jpg" alt="Logo" className="logo" />
            <p className="slogan">Need help? Want to report an emergency? Use SafeBear.</p>


        </header>
    );
}

export default AnonHeader;
