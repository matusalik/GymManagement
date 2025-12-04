import React, { useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function ChangePasswordModal({ isOpen, onClose }) {
  const { token, userId } = useAuth();

  const [newPassword, setNewPassword] = useState("");
  const [confirmPassword, setConfirmPassword] = useState("");

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");

  if (!isOpen) return null;

  const handleSubmit = async () => {
    setError("");
    setSuccess("");

    if (!newPassword || !confirmPassword) {
      setError("Both fields are required.");
      return;
    }

    if (newPassword !== confirmPassword) {
      setError("Passwords do not match.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch(
        `http://localhost:8080/api/users/password/${userId}`,
        {
          method: "PATCH", 
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json"
          },
          body: JSON.stringify({
            newPassword: newPassword
          })
        }
      );

      if (!res.ok) {
        const txt = await res.text();
        throw new Error(txt || "Failed to change password.");
      }

      setSuccess("Password updated successfully!");

      setTimeout(() => {
        onClose();
      }, 1000);

    } catch (err) {
      console.error("Password change error:", err);
      setError("Failed to update password.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Change Password</h2>

        {/* New Password */}
        <label className="block text-sm mb-1">New Password</label>
        <input
          type="password"
          value={newPassword}
          onChange={(e) => {
            setNewPassword(e.target.value);
            setError("");
          }}
          className="w-full mb-3 px-3 py-2 bg-input border border-border rounded"
        />

        {/* Confirm Password */}
        <label className="block text-sm mb-1">Confirm New Password</label>
        <input
          type="password"
          value={confirmPassword}
          onChange={(e) => {
            setConfirmPassword(e.target.value);
            setError("");
          }}
          className="w-full mb-4 px-3 py-2 bg-input border border-border rounded"
        />

        {/* Errors */}
        {error && <p className="text-red-500 text-sm mb-2">{error}</p>}
        {success && <p className="text-green-500 text-sm mb-2">{success}</p>}

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
            {loading ? "Saving..." : "Change Password"}
          </button>
        </div>
      </div>
    </div>
  );
}
