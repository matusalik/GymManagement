import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function ChangeEquipmentLocationModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const [equipmentList, setEquipmentList] = useState([]);
  const [selectedEquipment, setSelectedEquipment] = useState("");
  const [selectedLocation, setSelectedLocation] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // List of allowed locations (adjust as needed)
  const LOCATIONS = [
    "FLOOR_A",
    "FLOOR_B",
    "FLOOR_C"
  ];

  // Fetch equipment list
  useEffect(() => {
    if (isOpen) {
      setSelectedEquipment("");
      setSelectedLocation("");
      setError("");

      const fetchEquipment = async () => {
        try {
          const res = await fetch("http://localhost:8080/api/equipment/notdetailed", {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json"
            }
          });

          const data = await res.json();
          setEquipmentList(Array.isArray(data) ? data : []);
        } catch (err) {
          console.error("Error loading equipment:", err);
          setEquipmentList([]);
        }
      };

      fetchEquipment();
    }
  }, [isOpen, token]);

  if (!isOpen) return null;

  const handleSubmit = async () => {
    if (!selectedEquipment || !selectedLocation) {
      setError("Please select equipment and a location.");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/equipment/location", {
        method: "PATCH",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          equipment_id: Number(selectedEquipment),
          location: selectedLocation
        })
      });

      if (!res.ok) {
        const txt = await res.text();
        throw new Error(txt || "Failed to update location.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Update location error:", err);
      setError("Failed to update location.");
    } finally {
      setLoading(false);
    }
  };


  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Change Equipment Location</h2>

        {/* Equipment Selection */}
        <label className="block text-sm mb-1">Select Equipment</label>
        <select
          value={selectedEquipment}
          onChange={(e) => setSelectedEquipment(e.target.value)}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Choose equipment...</option>
          {equipmentList.map((eq) => (
            <option key={eq.equipment_id} value={eq.equipment_id}>
              {eq.name} — {eq.equipment_location}
            </option>
          ))}
        </select>

        {/* Location Selection */}
        <label className="block text-sm mb-1">Select New Location</label>
        <select
          value={selectedLocation}
          onChange={(e) => setSelectedLocation(e.target.value)}
          className="w-full mb-4 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Choose location...</option>
          {LOCATIONS.map((loc) => (
            <option key={loc} value={loc}>{loc}</option>
          ))}
        </select>

        {error && <p className="text-red-500 text-sm mb-2">{error}</p>}

        {/* Buttons */}
        <div className="flex justify-end space-x-3 mt-3">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-muted rounded hover:opacity-80"
          >
            Cancel
          </button>

          <button
            onClick={handleSubmit}
            disabled={loading}
            className="px-4 py-2 bg-primary text-primary-foreground rounded hover:opacity-90 disabled:opacity-50"
          >
            {loading ? "Saving..." : "Update Location"}
          </button>
        </div>
      </div>
    </div>
  );
}
