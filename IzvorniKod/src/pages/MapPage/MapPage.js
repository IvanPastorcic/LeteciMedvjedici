
import React from 'react';
import { useNavigate } from 'react-router-dom';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import './MapPage.css'; 
import AnonHeader from '../../components/AnonHeader/AnonHeader';


import markerIcon2x from 'leaflet/dist/images/marker-icon-2x.png';
import markerIcon from 'leaflet/dist/images/marker-icon.png';
import markerShadow from 'leaflet/dist/images/marker-shadow.png';

delete L.Icon.Default.prototype._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: markerIcon2x,
  iconUrl: markerIcon,
  shadowUrl: markerShadow,
});

const MapPage = () => {
  const navigate = useNavigate();
  return (
    <div className="page-container">
    <AnonHeader />
    <button className="back-button" onClick={() => navigate('/home')}>
        &lt; Back to newsfeed</button>
    <div className="content-container">
      <div className="map-wrapper">
        <MapContainer
          center={[45.1, 15.2]}
          zoom={7}
          scrollWheelZoom={true}
          style={{ height: '100%', width: '100%' }}
        >
          <TileLayer
            attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
            url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
          />
          <Marker position={[45.1, 15.2]}>
            <Popup>Dobrodošli na kartu Hrvatske!</Popup>
          </Marker>
        </MapContainer>
      </div>
      <aside className="sidebar">
        <h3>Filter by category</h3>
        {['Fire', 'Earthquake', 'Heavy storms', 'Flooding', 'Landslide'].map((category, index) => (
            <button key={index} className="filter-button">
            {category}
            </button>
        ))}
      </aside>

    </div>
  </div>
  
  
  );
};

export default MapPage;
