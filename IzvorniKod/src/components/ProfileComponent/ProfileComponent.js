import './ProfileComponent.css';

const ProfileComponent = (props) => {

    const reports = props.reports;
    const isAdmin = 1;
    console.log(props, reports);
    return ( 
        <div>
            
                {reports.map((report)=>(
                    <div key={report.user.id} className="User">
                        <div className="userData">
                            <text className="username">Username: {report.user.username}</text>
                            <text className="username">Email: {report.user.email}</text>
                            <text className="username">Role: {report.user.role}</text>
                            <text className="username">ID: {report.user.id}</text>
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