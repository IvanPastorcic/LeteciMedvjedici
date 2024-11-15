import React from 'react';
import './AnonHeader.css';

function AnonHeader() {
    return (
        <header className="anon-header">
            
            <img src="/logo.jpg" alt="Logo" className="logo" />
            <text className="slogan">Need help? Want to report an emergency? Use SafeBear.</text>
            
        </header>
    );
}

export default AnonHeader;
