import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";

export default function AddTrainerModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const [form, setForm] = useState({
    username: "",
    password: "",
    first_name: "",
    last_name: "",
    phone: "",
    email: "",
    bio: ""
  });

  const [loading, setLoading] = useState(false);
  const [errorMsg, setErrorMsg] = useState("");

  // Reset modal every time it opens
  useEffect(() => {
    if (isOpen) {
      setForm({
        username: "",
        password: "",
        first_name: "",
        last_name: "",
        phone: "",
        email: "",
        bio: ""
      });
      setErrorMsg("");
    }
  }, [isOpen]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setForm((prev) => ({
      ...prev,
      [e.target.name]: e.target.value
    }));
  };

  const handleSubmit = async () => {
    setErrorMsg("");

    const {
      username,
      password,
      first_name,
      last_name,
      phone,
      email,
      bio
    } = form;

    if (!username || !password || !first_name || !last_name || !email) {
      setErrorMsg("Please fill in all required fields.");
      return;
    }

    setLoading(true);

    try {
      const res = await fetch("http://localhost:8080/api/trainers", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify({
          username,
          password,
          first_name,
          last_name,
          phone,
          email,
          bio,
          status: "ACTIVE"
        })
      });

      if (!res.ok) {
        const text = await res.text();
        throw new Error(text || "Failed to add trainer");
      }

      onSuccess();
      onClose();

    } catch (err) {
      console.error("Add trainer error:", err);
      setErrorMsg(err.message);
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-lg">
        <h2 className="text-2xl font-bold mb-4">Add New Trainer</h2>

        {/* Username */}
        <input
          name="username"
          value={form.username}
          onChange={handleChange}
          placeholder="Username *"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Password */}
        <input
          name="password"
          type="password"
          value={form.password}
          onChange={handleChange}
          placeholder="Password *"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* First Name */}
        <input
          name="first_name"
          value={form.first_name}
          onChange={handleChange}
          placeholder="First name *"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Last Name */}
        <input
          name="last_name"
          value={form.last_name}
          onChange={handleChange}
          placeholder="Last name *"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Phone */}
        <input
          name="phone"
          value={form.phone}
          onChange={handleChange}
          placeholder="Phone"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Email */}
        <input
          name="email"
          type="email"
          value={form.email}
          onChange={handleChange}
          placeholder="Email *"
          className="w-full mb-3 px-3 py-2 border border-border bg-input rounded"
        />

        {/* Bio */}
        <textarea
          name="bio"
          value={form.bio}
          onChange={handleChange}
          placeholder="Trainer bio"
          className="w-full mb-4 px-3 py-2 border border-border bg-input rounded resize-none"
          rows={3}
        />

        {errorMsg && (
          <p className="text-red-500 text-sm mb-3">{errorMsg}</p>
        )}

        {/* Buttons */}
        <div className="flex justify-end gap-3">
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
            {loading ? "Saving..." : "Add Trainer"}
          </button>
        </div>
      </div>
    </div>
  );
}
