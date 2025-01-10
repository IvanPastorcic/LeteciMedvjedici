import React from "react";
import "./SystemSignIns.css";

const SystemSignIns = ({ signIns = [] }) => {
  return (
    <div className="SystemSignIns">
      <h2>SYSTEM SIGN-INS</h2>
      {signIns.length > 0 ? (
        signIns.map((signIn, index) => (
          <div key={index} className="SignInItem">
            <p>
              <strong>Username:</strong> {signIn.username}
            </p>
            <p>
              <strong>Signed in:</strong> {signIn.signInTime}
            </p>
            <p>
              <strong>Report made:</strong> {signIn.reportTime}
            </p>
          </div>
        ))
      ) : (
        <p>No system sign-ins available.</p>
      )}
    </div>
  );
};


export default SystemSignIns; 

