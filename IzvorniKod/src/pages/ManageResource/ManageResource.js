import React, { useState, useEffect } from "react";
import "./ManageResource.css";
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import BackButton from '../../components/BackButton/BackButton';
import Footer from "../../components/Footer/Footer";
import axios from 'axios';

function ManageResource() {
  const [allResources, setAllResources] = useState([]);
  const [filters, setFilters] = useState({ type: "", location: "" });
  const [newResource, setNewResource] = useState({ type: "", location: "", quantity: "" });
  const [myResources, setMyResources] = useState([]); // Initialize as an array



useEffect(() => {
  fetchResources();
  fetchMyResources();
}, []);


const fetchMyResources = async () => {
  try {
    const response = await axios.get("http://localhost:8081/resource/own", {
      withCredentials: true,
    });
    setMyResources(response.data);
  } catch (error) {
    console.error("Failed to fetch resources:", error);
    alert("Could not fetch resources. Please try again later.");
  }
};

  const fetchResources = async () => {
    try {
      const response = await axios.get("http://localhost:8081/resource/all", {
        withCredentials: true,
      });
      setAllResources(response.data);
    } catch (error) {
      console.error("Failed to fetch resources:", error);
      alert("Could not fetch resources. Please try again later.");
    }
  };
  
  // Handlers for filters
  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters((prev) => ({ ...prev, [name]: value }));
  };

  const filteredResources = allResources.filter((resource) => {
    const typeMatch = !filters.type || resource.id.resourceType.toLowerCase().includes(filters.type.toLowerCase());
    const locationMatch = !filters.location || resource.id.address.toLowerCase().includes(filters.location.toLowerCase());
    return typeMatch && locationMatch;
  });
  
  // Handler for adding new resource
  const handleAddResource = async () => {
    const { type, location, quantity } = newResource;
  
    if (type.trim() !== "" && location.trim() !== "" && quantity > 0) {
      try {
        const requestBody = {
          type: newResource.type.toUpperCase(),
          location: newResource.location,
          quantity: parseInt(newResource.quantity, 10),
        };
  
        await axios.post(
          "http://localhost:8081/resource/add/new",
          requestBody,
          { withCredentials: true }
        );
  
        // Fetch updated resources
        fetchResources();
  
        alert("Resource added successfully!");
        setNewResource({ type: "", location: "", quantity: "" });
      } catch (error) {
        if (error.response) {
          alert(`Error: ${error.response.data.message}`);
        } else {
          alert("Failed to add resource. Please try again.");
        }
      }
    } else {
      alert("Please fill out all fields before adding a resource.");
    }
  };

  // Handler for updating resource
  const handleUpdateResource = async (index) => {
    const currentResource = myResources[index];
    const updatedQuantity = prompt("Enter new quantity:", currentResource.quantity);
  
    if (updatedQuantity !== null) {
      const newQuantity = Number(updatedQuantity);
      if (isNaN(newQuantity) || newQuantity < 0) {
        alert("Invalid quantity. Please enter a non-negative number.");
        return;
      }
  
      try {
        // Prepare the request body as per the backend DTO
        const requestBody = {
          quantity: newQuantity,
          resourceType: currentResource.id.resourceType,
          address: currentResource.id.address
          
        };
          console.log(requestBody);
        // Make the PATCH request
        await axios.patch("http://localhost:8081/resource/update", requestBody, {headers: {
      "Content-Type": "application/json",
    },
          withCredentials: true,
        });
  
        // Update the state locally after a successful update
        setMyResources((prev) => {
          const updatedResources = [...prev];
          if (newQuantity === 0) {
            // Remove the resource if quantity is 0
            updatedResources.splice(index, 1);
          } else {
            // Update the quantity otherwise
            updatedResources[index].quantity = newQuantity;
          }
          return updatedResources;
        });
  
        alert("Resource updated successfully!");
      } catch (error) {
        console.error("Failed to update resource:", error);
        if (error.response) {
          //alert(Error: ${error.response.data.message});
        } else {
          alert("Failed to update resource. Please try again.");
        }
      }
    }
  };
  
  

  return (
    <div className="manage-report-page">
      <AnonHeader />
      <div className="ManageReport">
        <BackButton />
        <div className="resources-section">
          <h1>All resources</h1>
          <div className="filter-container">
            <p>Filter:</p>
            <label>Resource type: </label>
            <select
              name="type"
              value={filters.type}
              onChange={handleFilterChange}
            >
              <option value="">Select a resource</option>
              <option value="water">Water</option>
              <option value="food">Food</option>
              <option value="capacity">Capacity</option>
              <option value="first_aid_kit">First Aid Kit</option>
              <option value="heater">Heater</option>
              <option value="sandbag">Sand Bag</option>
            </select>
          </div>

          <table>
            <thead>
              <tr>
                <th>Resource type</th>
                <th>Quantity</th>
                <th>Organization</th>
                <th>Location</th>
              </tr>
            </thead>
            <tbody>
              {filteredResources.map((res, index) => (
                <tr key={index}>
                  <td>{res.id.resourceType}</td>
                  <td>{res.quantity}</td>
                  <td>{res.humanitarianOrganization.organizationName}</td>
                  <td>{res.id.address}</td>
                </tr>
              ))}
            </tbody>
          </table>
        </div>
        <h1>Humanitarian organization</h1>
        <table>
            <thead>
            <tr>
                <th>Resource type</th>
                <th>Location</th>
                <th>Quantity</th>
                <th>Action</th>
            </tr>
            </thead>
            <tbody>
                {myResources.map((res, index) => (
                <tr >
                  <td>{res.id.resourceType}</td>
                  <td>{res.id.address}</td>
                  <td>{res.quantity}</td>
                  <td>
                   { <button onClick={() => handleUpdateResource(index)}>Change quantity</button> }
                  </td>
                </tr>
                ))}
              </tbody>

        </table>


        <div className="humanitarian-action">
          <h2>Add new resource</h2>
          <div className="add-resource-container">
            <div className="form-field">
              <label>Resource type </label>
              <select
                name="type"
                value={newResource.type}
                onChange={(e) => setNewResource({ ...newResource, type: e.target.value })}
              >
                <option value="">Select a resource</option>
                <option value="WATER">Water</option>
                <option value="FOOD">Food</option>
                <option value="CAPACITY">Capacity</option>
                <option value="FIRST_AID_KIT">First Aid Kit</option>
                <option value="HEATER">Heater</option>
                <option value="SANDBAG">Sand Bag</option>
              </select>
            </div>
            <div className="form-field">
              <label>Location: </label>
              <input
                name="location"
                value={newResource.location}
                onChange={(e) => setNewResource({ ...newResource, location: e.target.value })}
              />
            </div>
            <div className="form-field">
              <label>Quantity: </label>
              <input
                type="number"
                name="quantity"
                value={newResource.quantity}
                onChange={(e) => setNewResource({ ...newResource, quantity: e.target.value })}
              />
            </div>
            <div className="form-field">
              <button onClick={handleAddResource}>Add</button>
            </div>
          </div>
        </div>
      </div>
      <Footer />
    </div>
  );
}

export default ManageResource;
