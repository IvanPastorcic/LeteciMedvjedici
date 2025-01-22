import './ProfileComponent.css';

const ProfileComponent = (props) => {

    const users = props.users;
    console.log(props, users);
    return ( 
        <div>
            
                {users.map((user)=>(
                    <div key={user.id} className="User">
                        <div className="userData">
                            <text className="username">Username: {user.username}</text>
                            <text className="username">Email: {user.email}</text>
                            <text className="username">Role: {user.role}</text>
                            <text className="username">ID: {user.id}</text>
                        </div>
                        
                            

                        <div className="adminStatus">
                                <button className='delete-user'>Delete the user</button>
                        </div>
                    </div>
                ))}
    </div>  
     );
}
 
export default ProfileComponent;