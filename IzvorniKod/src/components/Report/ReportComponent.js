const ReportComponent = (props) => {
    const reports = props.reports;


    console.log("Props dobiven:", props);
    console.log("Reports before map:", reports);

    return (
        <div>
            {reports && Array.isArray(reports) ? (
                reports.map((report) => (
                    <div key={report.id} className="Report">
                        <div className="ReportDateName">
                            <span className="aid-date">{new Date(report.time).toLocaleDateString()}</span>
                            <span className="username">{report.user.username}</span>
                        </div>
                        <h2>{report.disaster.disasterType} REPORT - {report.disaster.settlement.settlementName} AREA</h2>
                        <p>{report.shortDescription}</p>
                    </div>
                ))
            ) : (
                <div>No reports available to display.</div> // Fallback in case reports is not an array or empty
            )}
        </div>
    );
};

export default ReportComponent;