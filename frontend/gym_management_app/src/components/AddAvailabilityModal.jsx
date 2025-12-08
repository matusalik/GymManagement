import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function AddAvailabilityModal({ isOpen, onClose, onSuccess }) {
  const { token, userId } = useAuth();

  const DAYS = [
    "MONDAY", "TUESDAY", "WEDNESDAY",
    "THURSDAY", "FRIDAY", "SATURDAY", "SUNDAY"
  ];

  const [day, setDay] = useState("");
  const [startTime, setStartTime] = useState("");
  const [endTime, setEndTime] = useState("");
  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  if (!isOpen) return null;

  const handleSubmit = async () => {
    setErrorMsg("");

    if (!day || !startTime || !endTime) {
      setErrorMsg("All fields are required.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/trainer_availability", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          trainer_id: userId,
          day_of_the_week: day,
          start_time: `${startTime}:00`,
          end_time: `${endTime}:00`
        })
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Failed to add availability");
      }

      onSuccess();
      onClose();

    } catch (err) {
      console.error("Error adding availability:", err);
      setErrorMsg(err.message || "Failed to add availability");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Add New Availability</h2>

        {/* Day Select */}
        <label className="block mb-1 text-sm">Day of the Week</label>
        <select
          value={day}
          onChange={(e) => setDay(e.target.value)}
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        >
          <option value="">Select a day...</option>
          {DAYS.map((d) => (
            <option key={d} value={d}>{d}</option>
          ))}
        </select>

        {/* Start Time */}
        <label className="block mb-1 text-sm">Start Time</label>
        <input
          type="time"
          value={startTime}
          onChange={(e) => setStartTime(e.target.value)}
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* End Time */}
        <label className="block mb-1 text-sm">End Time</label>
        <input
          type="time"
          value={endTime}
          onChange={(e) => setEndTime(e.target.value)}
          className="w-full mb-4 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Error message from backend */}
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
            onClick={handleSubmit}
            disabled={loading}
            className="px-4 py-2 bg-primary text-primary-foreground rounded hover:opacity-90 disabled:opacity-50"
          >
            {loading ? "Saving..." : "Add Availability"}
          </button>
        </div>
      </div>
    </div>
  );
}
