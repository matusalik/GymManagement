import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function EditProfileModal({ isOpen, onClose, userData, onSuccess }) {
  const { token, userId } = useAuth();

  const [form, setForm] = useState({
    username: "",
    first_name: "",
    last_name: "",
    phone: "",
    email: ""
  });

  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Load user data into form when modal opens
  useEffect(() => {
    if (isOpen && userData) {
      setForm({
        username: userData.username,
        first_name: userData.first_name,
        last_name: userData.last_name,
        phone: userData.phone,
        email: userData.email
      });
      setError("");
    }
  }, [isOpen, userData]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async () => {
    setLoading(true);
    setError("");

    try {
      const res = await fetch(`http://localhost:8080/api/users/${userId}`, {
        method: "PUT",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(form)
      });

      if (!res.ok) {
        const txt = await res.text();
        throw new Error(txt || "Failed to save changes.");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Update profile error:", err);
      setError("Failed to save profile changes.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold text-foreground mb-4">Edit Profile</h2>

        {/* Username */}
        <label className="block text-sm mb-1">Username</label>
        <input
            name="username"
            value={form.username}
            disabled
            className="w-full mb-3 px-3 py-2 bg-muted border border-border rounded text-muted-foreground cursor-not-allowed"
        />


        {/* First Name */}
        <label className="block text-sm mb-1">First Name</label>
        <input
          name="first_name"
          value={form.first_name}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 bg-input border border-border rounded"
        />

        {/* Last Name */}
        <label className="block text-sm mb-1">Last Name</label>
        <input
          name="last_name"
          value={form.last_name}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 bg-input border border-border rounded"
        />

        {/* Phone */}
        <label className="block text-sm mb-1">Phone</label>
        <input
          name="phone"
          value={form.phone}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 bg-input border border-border rounded"
        />

        {/* Email */}
        <label className="block text-sm mb-1">Email</label>
        <input
          name="email"
          value={form.email}
          onChange={handleChange}
          className="w-full mb-4 px-3 py-2 bg-input border border-border rounded"
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
            {loading ? "Saving..." : "Save Changes"}
          </button>
        </div>
      </div>
    </div>
  );
}
