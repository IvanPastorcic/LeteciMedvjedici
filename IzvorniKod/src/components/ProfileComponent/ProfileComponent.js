const ProfileComponent = (props) => {

    const reports = props.reports;
    const isAdmin = 1;
    console.log(props, reports);
    return ( 
        <div>
            
                {reports.map((report)=>(
                    <div key={report.id} className="Report">
                        <div className="ReportDateName">
                            <text className="username">{report.user.username}</text>
                        </div>
                            <h2>{report.disaster.disasterType} REPORT -  {report.disaster.settlement.settlementName} AREA</h2>
                            <text>{report.shortDescription}</text>


                        {isAdmin && (

                            <div className="adminStatus">
                                <select  name="reportStatus">
                                        <option value="Accepted">Accepted</option>
                                        <option value="Processing">Processing...</option>
                                        <option value="Denied">Denied</option>
                                </select>
                                <button className='changeStatus'>Change status</button>
                            </div>
                        )}
                    </div>
                ))}
    </div>  
     );
}
 
export default ProfileComponent;