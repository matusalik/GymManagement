import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function ChangeClientStatusModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const STATUS_OPTIONS = ["ACTIVE", "INACTIVE"];

  const [clientId, setClientId] = useState("");
  const [status, setStatus] = useState("");
  const [errorMsg, setErrorMsg] = useState("");
  const [loading, setLoading] = useState(false);

  if (!isOpen) return null;

  const handleSubmit = async () => {
    setErrorMsg("");

    if (!clientId || !status) {
      setErrorMsg("All fields are required.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/clients/status", {
        method: "PATCH",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          client_id: Number(clientId),
          status: status
        })
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Failed to update client status");
      }

      onSuccess();
      onClose();

    } catch (err) {
      console.error("Change client status error:", err);
      setErrorMsg(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Change Client Status</h2>

        {/* Client ID */}
        <label className="block text-sm mb-1">Client ID</label>
        <input
          type="number"
          value={clientId}
          onChange={(e) => setClientId(e.target.value)}
          placeholder="Enter client ID..."
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Status Select */}
        <label className="block text-sm mb-1">Status</label>
        <select
          value={status}
          onChange={(e) => setStatus(e.target.value)}
          className="w-full mb-4 px-3 py-2 border border-border bg-input rounded"
        >
          <option value="">Select status...</option>
          {STATUS_OPTIONS.map((s) => (
            <option key={s} value={s}>{s}</option>
          ))}
        </select>

        {/* Backend Error */}
        {errorMsg && <p className="text-red-500 text-sm mb-3">{errorMsg}</p>}

        {/* Buttons */}
        <div className="flex justify-end gap-3 mt-4">
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
            {loading ? "Saving..." : "Change Status"}
          </button>
        </div>
      </div>
    </div>
  );
}
