import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function DeleteAvailabilityModal({ isOpen, onClose, onSuccess }) {
  const { token, userId } = useAuth();

  const DAYS = [
    "MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"
  ];

  const [day, setDay] = useState("");
  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  if (!isOpen) return null;

  const handleDelete = async () => {
    setErrorMsg("");

    if (!day) {
      setErrorMsg("Please select a day.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/trainer_availability", {
        method: "DELETE",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          trainer_id: userId,
          day_of_the_week: day
        })
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Failed to delete availability");
      }

      onSuccess();
      onClose();

    } catch (err) {
      console.error("Error deleting availability:", err);
      setErrorMsg(err.message || "Failed to delete availability");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Delete Availability</h2>

        {/* Day Selection */}
        <label className="block mb-1 text-sm">Day of the Week</label>
        <select
          value={day}
          onChange={(e) => setDay(e.target.value)}
          className="w-full mb-4 px-3 py-2 border border-border bg-input rounded"
        >
          <option value="">Select a day...</option>
          {DAYS.map((d) => (
            <option key={d} value={d}>{d}</option>
          ))}
        </select>

        {/* Backend Error */}
        {errorMsg && (
          <p className="text-red-500 text-sm mb-3">{errorMsg}</p>
        )}

        {/* Buttons */}
        <div className="flex justify-end gap-3 mt-2">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-muted rounded hover:opacity-80"
          >
            Cancel
          </button>

          <button
            onClick={handleDelete}
            disabled={loading}
            className="px-4 py-2 bg-destructive text-destructive-foreground rounded hover:opacity-90 disabled:opacity-50"
          >
            {loading ? "Deleting..." : "Delete"}
          </button>
        </div>
      </div>
    </div>
  );
}
