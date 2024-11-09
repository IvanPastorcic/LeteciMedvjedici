import React from 'react';
import "./Anonymous.css";

function Anonymous() {
    return (
        <div className="Anonymous">
            <p>Want to report an emergency anonymously?</p>
            <button type="submit" name='anonymous-button'>Anonymous report</button>
        </div>
    );
}

export default Anonymous;