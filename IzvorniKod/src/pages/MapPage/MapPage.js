import React, { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';
import { MapContainer, TileLayer, Marker, Popup } from 'react-leaflet';
import 'leaflet/dist/leaflet.css';
import L from 'leaflet';
import axios from 'axios';
import './MapPage.css'; 
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import BackButton from '../../components/BackButton/BackButton';

// Postavljanje markera za Leaflet
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
  const [reports, setReports] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);

  useEffect(() => {
    // Dohvati izvještaje sa backend-a
    const fetchReports = async () => {
      try {
        const response = await axios.get('http://localhost:8081/reports'); // Pretpostavljamo da je endpoint za izvještaje /reports
        setReports(response.data);
        setLoading(false);
      } catch (error) {
        setError('Failed to load reports');
        setLoading(false);
      }
    };
    fetchReports();
  }, []);

  if (loading) {
    return <div>Loading...</div>;
  }

  if (error) {
    return <div>{error}</div>;
  }

  return (
    <div className="map-page-container">
      <AnonHeader />
      <BackButton /> 
      <div className="map-content-container">
        <div className="map-wrapper">
          <MapContainer
            center={[45.1, 15.2]} // Početna pozicija karte (središnja točka Hrvatske)
            zoom={7}
            scrollWheelZoom={true}
            style={{ height: '100%', width: '100%' }}
          >
            <TileLayer
              attribution='&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
              url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
            />
            {/* Iteracija kroz izvještaje i dodavanje markera */}
            {reports.map((report) => {
              // Provjera da li izvještaj ima koordinate
              if (report.latitude && report.longitude) {
                return (
                  <Marker
                    key={report.id} // Preporučuje se koristiti jedinstveni ID kao ključ
                    position={[report.latitude, report.longitude]}
                  >
                    <Popup>
                      <div>
                        <strong>{report.disasterType}</strong>
                        <p>{report.shortDescription}</p>
                      </div>
                    </Popup>
                  </Marker>
                );
              }
              return null; // Ako izvještaj nema koordinate, ne prikazujemo marker
            })}
          </MapContainer>
        </div>
        <aside className="map-sidebar">
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
