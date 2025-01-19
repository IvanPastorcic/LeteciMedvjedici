import React from 'react';
import './ProfileHeader.css';
import { useNavigate } from 'react-router-dom';


function ProfileHeader() {
    const navigate = useNavigate();
    const navigateToProfile = () => {
        navigate('/profile');
    };
    return (
        <header className="profile-header">

            <img src="/logo.jpg" alt="Logo" className="logo" />
            <p className="slogan">Need help? Want to report an emergency? Use SafeBear.</p>
            <p className="profile-name">Marko Markic</p>
            <button className='to-profile' onClick={navigateToProfile}>profile</button>
        </header>
    );
}

export default ProfileHeader;
