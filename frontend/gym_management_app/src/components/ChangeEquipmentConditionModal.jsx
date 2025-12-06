import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function ChangeEquipmentConditionModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const [equipmentList, setEquipmentList] = useState([]);
  const [selectedEquipment, setSelectedEquipment] = useState("");
  const [selectedCondition, setSelectedCondition] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  const CONDITIONS = [
    "NEW",
    "GOOD",
    "FAIR",
    "POOR",
    "BROKEN",
    "UNDER_MAINTENANCE"
  ];

  // Load equipment on modal open
  useEffect(() => {
    if (isOpen) {
      setSelectedEquipment("");
      setSelectedCondition("");
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
    if (!selectedEquipment || !selectedCondition) {
      setError("Please select equipment and a condition.");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/equipment/condition", {
        method: "PATCH",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          equipment_id: Number(selectedEquipment),
          condition: selectedCondition
        })
      });

      if (!res.ok) {
        const txt = await res.text();
        throw new Error(txt || "Failed to update condition.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Update condition error:", err);
      setError("Failed to update condition.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Change Equipment Condition</h2>

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

        {/* Condition Selection */}
        <label className="block text-sm mb-1">Select New Condition</label>
        <select
          value={selectedCondition}
          onChange={(e) => setSelectedCondition(e.target.value)}
          className="w-full mb-4 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Choose condition...</option>
          {CONDITIONS.map((c) => (
            <option key={c} value={c}>{c}</option>
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
            {loading ? "Saving..." : "Update Condition"}
          </button>
        </div>
      </div>
    </div>
  );
}
