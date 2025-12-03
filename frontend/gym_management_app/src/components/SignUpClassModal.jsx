import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";

export default function SignUpClassModal({ isOpen, onClose, onSuccess }) {
  const { token, userId } = useAuth();

  const [availableClasses, setAvailableClasses] = useState([]);
  const [selectedClass, setSelectedClass] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Load available classes when modal opens
  useEffect(() => {
    if (isOpen) {
      setSelectedClass("");
      setError("");

      const fetchAvailable = async () => {
        try {
          const res = await fetch(
            `http://localhost:8080/api/group_classes/available/${userId}`,
            {
              headers: {
                Authorization: `Bearer ${token}`,
                "Content-Type": "application/json"
              }
            }
          );

          const data = await res.json();
          setAvailableClasses(Array.isArray(data) ? data : []);
        } catch (err) {
          console.error("Error fetching available classes:", err);
          setAvailableClasses([]);
        }
      };

      fetchAvailable();
    }
  }, [isOpen, token, userId]);

  if (!isOpen) return null;

  const handleSubmit = async () => {
    if (!selectedClass) {
      setError("Please select a class.");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const response = await fetch(
        `http://localhost:8080/api/group_classes/${selectedClass}/${userId}`,
        {
          method: "POST",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        }
      );

      if (!response.ok) {
        const text = await response.text();
        throw new Error(text || "Failed to sign up.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Sign up error:", err);
      setError("Failed to sign up for the class.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold text-foreground mb-4">
          Sign Up For Group Class
        </h2>

        {/* Class Selection */}
        <label className="block mb-1 text-sm">Choose Class</label>
        <select
          name="class"
          value={selectedClass}
          onChange={(e) => setSelectedClass(e.target.value)}
          className="w-full mb-4 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Select Class</option>
          {availableClasses.map((cls) => (
            <option key={cls.group_class_id} value={cls.group_class_id}>
              {cls.name} — {new Date(cls.date_time).toLocaleString()}
            </option>
          ))}
        </select>

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
            {loading ? "Signing up..." : "Sign Up"}
          </button>
        </div>
      </div>
    </div>
  );
}
