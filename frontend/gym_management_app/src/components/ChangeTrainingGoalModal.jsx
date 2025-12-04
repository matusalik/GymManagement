import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function ChangeTrainingGoalModal({ isOpen, onClose, onSuccess }) {
  const { token, userId } = useAuth();

  const [trainingGoals, setTrainingGoals] = useState([]);
  const [selectedGoal, setSelectedGoal] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Load training goals when modal opens
  useEffect(() => {
    if (isOpen) {
      setSelectedGoal("");
      setError("");

      const fetchGoals = async () => {
        try {
          const res = await fetch("http://localhost:8080/api/training_goals/notdetailed", {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json"
            }
          });

          const data = await res.json();
          setTrainingGoals(Array.isArray(data) ? data : []);
        } catch (err) {
          console.error("Error loading training goals:", err);
          setTrainingGoals([]);
        }
      };

      fetchGoals();
    }
  }, [isOpen, token]);

  if (!isOpen) return null;

  const handleSubmit = async () => {
    if (!selectedGoal) {
      setError("Please select a training goal.");
      return;
    }

    setLoading(true);
    setError("");

    try {
      const res = await fetch(
        `http://localhost:8080/api/clients/training_goal/${userId}/${selectedGoal}`,
        {
          method: "PATCH",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          }
        }
      );

      if (!res.ok) {
        const txt = await res.text();
        throw new Error(txt || "Failed to update training goal.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Training goal update error:", err);
      setError("Failed to update training goal.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Change Training Goal</h2>

        {/* Combo box */}
        <label className="block text-sm mb-1">Select Training Goal</label>
        <select
          value={selectedGoal}
          onChange={(e) => setSelectedGoal(e.target.value)}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Choose...</option>
          {trainingGoals.map((g) => (
            <option key={g.id} value={g.id}>
              {g.name}
            </option>
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
            {loading ? "Saving..." : "Save"}
          </button>
        </div>
      </div>
    </div>
  );
}
