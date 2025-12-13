import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";
import { useNavigate } from "react-router-dom";

export default function EquipmentPage() {
  const { token } = useAuth();
  const [equipment, setEquipment] = useState([]);
  const [filteredEquipment, setFilteredEquipment] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);
  const navigate = useNavigate();

  useEffect(() => {
    const fetchEquipment = async () => {
      try {
        const res = await fetch("http://localhost:8080/api/equipment", {
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        });

        const data = await res.json();
        const list = Array.isArray(data) ? data : [];

        setEquipment(list);
        setFilteredEquipment(list);
      } catch (err) {
        console.error("Error fetching equipment:", err);
        setEquipment([]);
        setFilteredEquipment([]);
      } finally {
        setLoading(false);
      }
    };

    if (token) {
      fetchEquipment();
    }
  }, [token]);

  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredEquipment(
      equipment.filter((eq) =>
        eq.name.toLowerCase().includes(q) ||
        eq.equipment_type.toLowerCase().includes(q) ||
        eq.equipment_location.toLowerCase().includes(q) ||
        eq.equipment_condition.toLowerCase().includes(q) ||
        eq.equipment_availability.toLowerCase().includes(q)
      )
    );
  }, [search, equipment]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">
          Gym Equipment
        </h1>

        <button
          onClick={() => navigate("/dashboard")}
          className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          ← Back to Dashboard
        </button>

        {/* SEARCH BAR (only addition) */}
        <input
          type="text"
          placeholder="Search by name, type, location or condition..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="mb-4 block w-full md:w-1/3 px-3 py-2 border border-border rounded bg-input"
        />

        {loading ? (
          <p className="text-muted-foreground">Loading equipment...</p>
        ) : filteredEquipment.length === 0 ? (
          <p className="text-muted-foreground">No equipment found.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Name</th>
                  <th className="p-3 text-left">Type</th>
                  <th className="p-3 text-left">Location</th>
                  <th className="p-3 text-left">Condition</th>
                  <th className="p-3 text-left">Availability</th>
                </tr>
              </thead>

              <tbody>
                {filteredEquipment.map((eq, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">{eq.name}</td>
                    <td className="p-3">{eq.equipment_type}</td>
                    <td className="p-3">{eq.equipment_location}</td>
                    <td className="p-3">{eq.equipment_condition}</td>
                    <td className="p-3">
                      <span
                        className={`px-2 py-1 rounded text-xs font-medium ${
                          eq.equipment_availability === "AVAILABLE"
                            ? "bg-primary/20 text-primary"
                            : "bg-destructive/20 text-destructive"
                        }`}
                      >
                        {eq.equipment_availability}
                      </span>
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
