const ReportComponent = (props) => {

    const reports = props.reports;
    console.log(props, reports);
    return ( 
        <div>
            
                {reports.map((report)=>(
                    <div key={report.id} className="Report">
                        <div className="ReportDateName">
                            <text className="aid-date">{new Date(report.time).toLocaleDateString()}</text>
                            <text className="username">{report.user.username}</text>
                        </div>
                            <h2>{report.disaster.disasterType} REPORT -  {report.disaster.settlement.settlementName} AREA</h2>
                            <text>{report.shortDescription}</text>

                        
                    </div>
                ))}
    </div>  
     );
}
 
export default ReportComponent;