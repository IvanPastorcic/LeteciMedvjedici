const ReportComponent = (props) => {

    const reports = props.reports;
    console.log(props, reports);
    return ( 
        <div>
            
                {reports.map((report)=>(
                    <div className="Report">
                        <div className="ReportDateName">
                            <text className="aid-date">{report.date}</text>
                            <text className="username">{report.username}</text>
                        </div>
                            <h2>{report.disasterType} REPORT - {report.area} AREA</h2>
                            <text>{report.description}</text>

                        
                    </div>
                ))}
    </div>  
     );
}
 
export default ReportComponent;