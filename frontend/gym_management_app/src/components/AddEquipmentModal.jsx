import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function AddEquipmentModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const EQUIPMENT_TYPES = [
    "CARDIO_MACHINE",
    "STRENGTH_MACHINE",
    "FREE_WEIGTH",
    "BENCH",
    "ACCESSORY",
    "MATS",
    "RACK",
    "FLEXIBILITY_EQUIPMENT",
    "OTHER"
  ];

  const LOCATIONS = ["FLOOR_A", "FLOOR_B", "FLOOR_C"];

  const CONDITIONS = [
    "NEW",
    "GOOD",
    "FAIR",
    "POOR",
    "BROKEN",
    "UNER_MAINTENANCE"
  ];

  const [form, setForm] = useState({
    name: "",
    equipment_type: "",
    equipment_location: "",
    equipment_condition: ""
  });

  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  // Reset modal on open
  useEffect(() => {
    if (isOpen) {
      setForm({
        name: "",
        equipment_type: "",
        equipment_location: "",
        equipment_condition: ""
      });
      setErrorMsg("");
    }
  }, [isOpen]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setForm((prev) => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async () => {
    setErrorMsg("");

    const {
      name,
      equipment_type,
      equipment_location,
      equipment_condition
    } = form;

    if (!name || !equipment_type || !equipment_location || !equipment_condition) {
      setErrorMsg("All fields are required.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/equipment", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          name,
          equipment_type,
          equipment_location,
          equipment_condition,
          equipment_availability: "AVAILABLE"
        })
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Failed to add equipment");
      }

      onSuccess();
      onClose();

    } catch (err) {
      console.error("Add equipment error:", err);
      setErrorMsg(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Add Equipment</h2>

        <input
          name="name"
          value={form.name}
          onChange={handleChange}
          placeholder="Equipment name"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        <select
          name="equipment_type"
          value={form.equipment_type}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        >
          <option value="">Select type...</option>
          {EQUIPMENT_TYPES.map((t) => (
            <option key={t} value={t}>{t}</option>
          ))}
        </select>

        <select
          name="equipment_location"
          value={form.equipment_location}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        >
          <option value="">Select location...</option>
          {LOCATIONS.map((l) => (
            <option key={l} value={l}>{l}</option>
          ))}
        </select>

        <select
          name="equipment_condition"
          value={form.equipment_condition}
          onChange={handleChange}
          className="w-full mb-4 px-3 py-2 border border-border bg-input rounded"
        >
          <option value="">Select condition...</option>
          {CONDITIONS.map((c) => (
            <option key={c} value={c}>{c}</option>
          ))}
        </select>

        {errorMsg && (
          <p className="text-red-500 text-sm mb-3">{errorMsg}</p>
        )}

        <div className="flex justify-end gap-3">
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
            {loading ? "Saving..." : "Add Equipment"}
          </button>
        </div>
      </div>
    </div>
  );
}
