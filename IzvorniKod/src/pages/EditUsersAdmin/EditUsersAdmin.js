import './EditUsersAdmin.css';
import AnonHeader from "../../components/AnonHeader/AnonHeader";
import ProfileComponent from "../../components/ProfileComponent/ProfileComponent";
import BackButton from "../../components/BackButton/BackButton";
import { useNavigate } from 'react-router-dom';
import React, { useState, useEffect } from 'react';
import axios from 'axios';
import ProfileHeader from '../../components/ProfileHeader/ProfileHeader';

const EditUsersAdmin = () => {
    const [users, setUsers] = useState([]);
    const [loading, setLoading] = useState(true);
    const [error, setError] = useState(null);
    const navigate = useNavigate();

    const [searchUserEmail, setSearchUserEmail] = useState(''); // State for search input
    const [searchedUser, setSearchedUser] = useState(null); // State for the searched report

    const [newUser, setNewUser] = useState({
        username: '',
        email: '',
        role: 'ROLE_USER', // defaultna je  ROLE_USER
    });
    
    // Fetch reports on mount
    useEffect(() => {
        const fetchUsers= async () => {
            try {
                const response = await axios.get("http://localhost:8081/user/all", { withCredentials: true });
                console.log(response.data);
                setUsers(response.data);
            } catch (error) {
                console.error("Error fetching users:", error);
                setError("Failed to load data");
            } finally {
                setLoading(false);
            }
        };
        fetchUsers();
    }, []);

   

    // Handle search input change
    const handleInputChange = (e) => {
        setSearchUserEmail(e.target.value);
    };

    // Handle search by user email
    const handleSearch = async () => {
        if (!searchUserEmail.trim()) {
            alert("Please enter a valid user email.");
            return;
        }

        try {
            setLoading(true);
            const response = await axios.get(`http://localhost:8081/user/email/${searchUserEmail}`, {
                withCredentials: true,
            });
            setSearchedUser(response.data);
            setError(null); // ako je uspjesno, ocisti errore
        } catch (error) {
            console.error("Error searching user:", error);
            setError("No user found with the given email.");
            setSearchedUser(null); // ocisti searchedUser ako se ne pronade
        } finally {
            setLoading(false);
        }
    };

    // Clear search and show all users
    const handleClearSearch = () => {
        setSearchUserEmail(''); // Clear the search input field
        setSearchedUser(null); // Reset the searched user state
        setError(null); // Clear any error message
    };

    // form changes for new user
    const handleFormChange = (e) => {
        const { name, value } = e.target;
        setNewUser({
            ...newUser,
            [name]: value,
        });
    };

    // stvaranje novoga/update
    const handleUserSubmit = async (e) => {
        e.preventDefault();
        try {
            const response = await axios.post('http://localhost:8081/user/create', newUser, { withCredentials: true });
            alert('User created/updated successfully!');
        } catch (error) {
            setError('Failed to create/update user');
            console.error(error);
        }
    };

    const navigateToMap = () => {
        navigate('/map');
    };
    const navigateToHomeAdmin = () => {
        navigate('/homeadmin');
    };

    return (
        <div className="EditUsersAdmin">
            <div className="header">
                <ProfileHeader />
            </div>

            <div className="buttonsHomePageAdmin">
              
                <button className="edit-users" onClick={navigateToHomeAdmin}>
                    EDIT REPORTS
                </button>
                <button className="see-map-button-admin" onClick={navigateToMap}>
                    SEE MAP
                </button>
            </div>

            <div className="PageBodyAdmin">
                <div className="UserSectionAdmin">
                    <div>
                        <h2>Users</h2>
                        <div className="search">
                            <input className='inputbox'
                                type="text"
                                value={searchUserEmail}
                                onChange={handleInputChange}
                                placeholder="Search users by email"
                            />
                            <button onClick={handleSearch} className="search-button">
                                Search
                            </button>
                            {(searchUserEmail || error) && (
                                <button onClick={handleClearSearch} className="clear-search-button">
                                    Clear Search
                                </button>
                            )}
                        </div>
                    </div>
                </div>
                    
                    <div className="UsersAdmin">
                        {searchedUser ? (
                            <div className="SearchedUser">
                                <h3>Searched User:</h3>
                                {/*<ProfileComponent users={searchedUser} />*/}
                                <ProfileComponent users={Array.isArray(searchedUser) ? searchedUser : [searchedUser]} />

                            </div>
                        ) : (
                            <>
                                <div className="ValidUsers">
                                    <h4>Users:</h4>
                                    {loading ? (
                                        <p>Loading the users...</p>
                                    ) : error ? (
                                        <p>{error}</p>
                                    ) : (
                                        <ProfileComponent users={users} />
                                    )}
                                </div>

                            </>
                        )}
                    </div>



                {/* stvaranje novog */}
                <div className="UserForm">
                    <h3>Create or Update User</h3>
                    <form  onSubmit={handleUserSubmit}>
                        <div>
                            <label htmlFor="username">Username</label>
                            <input className='inputadmin'
                                type="text"
                                id="username"
                                name="username"
                                value={newUser.username}
                                onChange={handleFormChange}
                                required
                            />
                        </div>

                        <div>
                            <label htmlFor="email">Email</label>
                            <input className='inputadmin'
                                type="email"
                                id="email"
                                name="email"
                                value={newUser.email}
                                onChange={handleFormChange}
                                required
                            />
                        </div>

                        <div>
                            <label htmlFor="role">Role</label>
                            <select className='inputadmin'
                                id="role"
                                name="role"
                                value={newUser.role}
                                onChange={handleFormChange}
                                required
                            >
                                <option value="ROLE_USER">ROLE_USER</option>
                                <option value="ROLE_AUTHORITY">ROLE_AUTHORITY</option>
                                <option value="ROLE_HUMANITARIAN">ROLE_HUMANITARIAN</option>
                                <option value="ROLE_ADMIN">ROLE_ADMIN</option>
                            </select>
                        </div>
                
                        <div>
                            <button type="submit" className="submit-button">
                                Submit
                            </button>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    );
};

export default EditUsersAdmin;
