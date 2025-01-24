import './ProfileComponent.css';
import axios from 'axios';

const ProfileComponent = (props) => {
    const users = props.users;

    // Function to handle user deletion
    const handleDeleteUser = async (userId) => {
        if (window.confirm(`Are you sure you want to delete user with ID: ${userId}?`)) {
            try {
                await axios.delete(`https://safebear-backend.onrender.com/user/${userId}`, { withCredentials: true });

                // Notify parent component to refresh the user list
                //props.onUserDeleted(userId);
            } catch (error) {
                console.error("Error deleting user:", error);
                alert("Failed to delete user. Please try again.");
            }
        }
    };

    return ( 
        <div>
            {users.map((user) => (
                <div key={user.id} className="User">
                    <div className="userData">
                        <p className="username">Username: {user.username}</p>
                        <p className="username">Email: {user.email}</p>
                        <p className="username">Role: {user.role}</p>
                        <p className="username">ID: {user.id}</p>
                    </div>

                    <div className="adminStatus">
                        <button 
                            className='delete-user' 
                            onClick={() => handleDeleteUser(user.id)}
                            disabled={user.role === 'ROLE_ADMIN' || user.username === 'Anonimni korisnik'}
                        >
                            {user.role === 'ROLE_ADMIN' || user.username === 'Anonimni korisnik' ? 'Cannot Delete' : 'Delete the user'}
                        </button>
                    </div>
                </div>
            ))}
        </div>  
    );
}

export default ProfileComponent;
