import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function ChangePasswordModal({ isOpen, onClose }) {
  const { token, user } = useAuth();

  const [oldPassword, setOldPassword] = useState("");
  const [newPassword, setNewPassword] = useState("");
  const [newPasswordRepeat, setNewPasswordRepeat] = useState("");

  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");
  const [successMsg, setSuccessMsg] = useState("");

  // Reset modal every time it opens
  useEffect(() => {
    if (isOpen) {
      setOldPassword("");
      setNewPassword("");
      setNewPasswordRepeat("");
      setErrorMsg("");
      setSuccessMsg("");
    }
  }, [isOpen]);

  if (!isOpen) return null;

  const handleSubmit = async () => {
    setErrorMsg("");
    setSuccessMsg("");

    if (!oldPassword || !newPassword || !newPasswordRepeat) {
      setErrorMsg("All fields are required.");
      return;
    }

    if (newPassword !== newPasswordRepeat) {
      setErrorMsg("New passwords do not match.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/auth/change_password", {
        method: "PATCH",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          username: user.username,
          oldPassword,
          newPassword,
          newPasswordRepeat
        })
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Failed to change password");
      }

      setSuccessMsg("Password changed successfully.");
      setOldPassword("");
      setNewPassword("");
      setNewPasswordRepeat("");

    } catch (err) {
      console.error("Change password error:", err);
      setErrorMsg(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold mb-4">Change Password</h2>

        <input
          type="password"
          placeholder="Current password"
          value={oldPassword}
          onChange={(e) => setOldPassword(e.target.value)}
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        <input
          type="password"
          placeholder="New password"
          value={newPassword}
          onChange={(e) => setNewPassword(e.target.value)}
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        <input
          type="password"
          placeholder="Repeat new password"
          value={newPasswordRepeat}
          onChange={(e) => setNewPasswordRepeat(e.target.value)}
          className="w-full mb-4 px-3 py-2 border border-border bg-input rounded"
        />

        {errorMsg && (
          <p className="text-red-500 text-sm mb-3">{errorMsg}</p>
        )}

        {successMsg && (
          <p className="text-green-600 text-sm mb-3">{successMsg}</p>
        )}

        <div className="flex justify-end gap-3">
          <button
            onClick={onClose}
            className="px-4 py-2 bg-muted rounded hover:opacity-80"
          >
            Close
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
