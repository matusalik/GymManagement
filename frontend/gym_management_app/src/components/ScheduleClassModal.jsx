import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";

export default function ScheduleClassModal({ isOpen, onClose, onSuccess }) {
  const { token, userId } = useAuth();

  const emptyForm = {
    name: "",
    description: "",
    date: "",
    time: "",
    max_participants: ""
  };

  const [form, setForm] = useState(emptyForm);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Reset on open
  useEffect(() => {
    if (isOpen) {
      setForm(emptyForm);
      setError("");
      setLoading(false);
    }
  }, [isOpen]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async () => {
    if (!form.name || !form.description || !form.date || !form.time || !form.max_participants) {
      setError("All fields are required.");
      return;
    }

    setLoading(true);
    setError("");

    // Combine date + time into ISO
    const dateTimeISO = new Date(`${form.date}T${form.time}:00`).toISOString();

    const payload = {
      name: form.name,
      description: form.description,
      trainer_id: userId,
      clients_ids: [], // no clients on creation
      date_time: dateTimeISO,
      max_participants: parseInt(form.max_participants, 10)
    };

    try {
      const response = await fetch("http://localhost:8080/api/group_classes", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(text || "Failed to create class.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Create class error:", err);
      setError("Failed to create group class.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold text-foreground mb-4">Schedule New Group Class</h2>

        {/* Name */}
        <label className="block mb-1 text-sm">Class Name</label>
        <input
          type="text"
          name="name"
          value={form.name}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        />

        {/* Description */}
        <label className="block mb-1 text-sm">Description</label>
        <textarea
          name="description"
          value={form.description}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        />

        {/* Date */}
        <label className="block mb-1 text-sm">Date</label>
        <input
          type="date"
          name="date"
          value={form.date}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        />

        {/* Time */}
        <label className="block mb-1 text-sm">Time</label>
        <input
          type="time"
          name="time"
          value={form.time}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        />

        {/* Max Participants */}
        <label className="block mb-1 text-sm">Max Participants</label>
        <input
          type="number"
          name="max_participants"
          value={form.max_participants}
          onChange={handleChange}
          className="w-full mb-4 px-3 py-2 rounded bg-input border border-border"
        />

        {/* Error */}
        {error && <p className="text-red-500 text-sm mb-3">{error}</p>}

        {/* Buttons */}
        <div className="flex justify-end space-x-3">
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
            {loading ? "Creating..." : "Create Class"}
          </button>
        </div>
      </div>
    </div>
  );
}
