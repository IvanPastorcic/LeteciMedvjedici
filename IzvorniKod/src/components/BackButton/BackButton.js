import './BackButton.css';
import { FaArrowLeft, FaFire, FaWater, FaBolt, FaMountain, FaHome } from "react-icons/fa";
import { useNavigate } from 'react-router-dom';





const BackButton = () => {
    const navigate = useNavigate();
    const goBack = () => {
        navigate(-1); // Go back to the previous page
      };
    return ( 
        <div>
            <button className="back-button" onClick={goBack}>
                <FaArrowLeft /> Back to newsfeed
            </button>
        </div>  
     );
}
 
export default BackButton;