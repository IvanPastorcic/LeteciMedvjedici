import React, { useState } from "react";
import "./ManageResource.css";
import AnonHeader from '../../components/AnonHeader/AnonHeader';
import Footer from "../../components/Footer/Footer";

function ManageResource(){
  // Sample data for resources
  const [allResources, setAllResources] = useState([
    { type: "Water", location: "Zagreb", quantity: 5000, organization: "A" },
    { type: "Sand bag", location: "Karlovac", quantity: 1000, organization: "B" },
  ]);

  const [myResources, setMyResources] = useState([
    { type: "Water", location: "Zagreb", quantity: 5000 },
    { type: "Sand bag", location: "Karlovac", quantity: 1000 },
  ]);

  const [filters, setFilters] = useState({ type: "", location: "" });

  const [newResource, setNewResource] = useState({ type: "", location: "", quantity: "" });

  // Handlers for filters
  const handleFilterChange = (e) => {
    const { name, value } = e.target;
    setFilters((prev) => ({ ...prev, [name]: value }));
  };

  const filteredResources = allResources.filter(
    (resource) =>
      (!filters.type || resource.type.includes(filters.type)) &&
      (!filters.location || resource.location.includes(filters.location))
  );

  // Handler for adding new resource
  const handleAddResource = () => {
    if (newResource.type && newResource.location && newResource.quantity) {
      setMyResources((prev) => [...prev, { ...newResource, quantity: Number(newResource.quantity) }]);
      setNewResource({ type: "", location: "", quantity: "" });
    }
  };

  // Handler for updating resource
  const handleUpdateResource = (index) => {
    const updatedQuantity = prompt("Enter new quantity:", myResources[index].quantity);
    if (updatedQuantity !== null) {
      const newQuantity = Number(updatedQuantity);
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
    }
  };
  

  return (
    <div className="manage-report-page">
    <AnonHeader />
    <div className="ManageReport">
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
            <option value="shelter">Shelter</option>
            <option value="first_aid_kit">First Aid Kit</option>
            <option value="heater">Heater</option>
            <option value="sand_bag">Sand Bag</option>
            </select>
            <label> Location: </label>
            <input name="location" value={filters.location} onChange={handleFilterChange} />
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
                <td>{res.type}</td>
                <td>{res.quantity}</td>
                <td>{res.organization}</td>
                <td>{res.location}</td>
                </tr>
            ))}
            </tbody>
        </table>
        </div>

        <div className="humanitarian-action">
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
                <tr key={index}>
                <td>{res.type}</td>
                <td>{res.location}</td>
                <td>{res.quantity}</td>
                <td>
                    <button onClick={() => handleUpdateResource(index)}>Change quantity</button>
                </td>
                </tr>
            ))}
            </tbody>
        </table>

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
                <option value="Water">Water</option>
                <option value="Food">Food</option>
                <option value="Shelter">Shelter</option>
                <option value="First aid kit">First Aid Kit</option>
                <option value="Heater">Heater</option>
                <option value="Sand bag">Sand Bag</option>
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

