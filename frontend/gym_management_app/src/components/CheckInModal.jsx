import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";

export default function CheckInModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const [clientId, setClientId] = useState("");
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Reset modal when opened
  useEffect(() => {
    if (isOpen) {
      setClientId("");
      setError("");
      setLoading(false);
    }
  }, [isOpen]);

  if (!isOpen) return null;

  const handleSubmit = async () => {
    if (!clientId) {
      setError("Client ID is required.");
      return;
    }

    setLoading(true);
    setError("");

    const payload = {
      client_id: parseInt(clientId, 10),
      date_time: new Date().toISOString(),
    };

    try {
      const response = await fetch("http://localhost:8080/api/client_attendance", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json",
        },
        body: JSON.stringify(payload),
      });

      if (!response.ok) {
        const text = await response.text();
        throw new Error(text || "Failed to check in client.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Check-in error:", err);
      setError("Failed to check in client.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold text-foreground mb-4">Check-In Client</h2>

        <label className="block mb-2 text-sm text-muted-foreground">
          Client ID
        </label>
        <input
          type="number"
          value={clientId}
          onChange={(e) => setClientId(e.target.value)}
          className="w-full mb-4 px-3 py-2 rounded bg-input border border-border"
          placeholder="Enter client ID"
        />

        {error && <p className="text-red-500 text-sm mb-3">{error}</p>}

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
            {loading ? "Checking in..." : "Check-In"}
          </button>
        </div>
      </div>
    </div>
  );
}
