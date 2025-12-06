import React, { useEffect, useState } from "react";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import { useAuth } from "../context/AuthContext";

export default function EquipmentPage() {
  const { token } = useAuth();
  const [equipment, setEquipment] = useState([]);
  const [loading, setLoading] = useState(true);

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
        setEquipment(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error fetching equipment:", err);
        setEquipment([]);
      } finally {
        setLoading(false);
      }
    };

    if (token) {
      fetchEquipment();
    }
  }, [token]);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold text-foreground mb-6">
          Gym Equipment
        </h1>

        {loading ? (
          <p className="text-muted-foreground">Loading equipment...</p>
        ) : equipment.length === 0 ? (
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
                {equipment.map((eq, index) => (
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
