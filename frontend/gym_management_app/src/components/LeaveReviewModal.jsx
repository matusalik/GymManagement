import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";

export default function LeaveReviewModal({ isOpen, onClose, onSuccess }) {
  const { token, userId } = useAuth();

  const emptyForm = {
    trainer_id: "",
    rating: "",
    comment: ""
  };

  const [form, setForm] = useState(emptyForm);
  const [trainers, setTrainers] = useState([]);
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Load trainers list + reset form
  useEffect(() => {
    if (isOpen) {
      setForm(emptyForm);
      setError("");

      const fetchTrainers = async () => {
        try {
          const res = await fetch("http://localhost:8080/api/trainers/notdetailed", {
            headers: { Authorization: `Bearer ${token}` }
          });

          const data = await res.json();
          setTrainers(data);
        } catch (err) {
          console.error("Error loading trainers:", err);
          setTrainers([]);
        }
      };

      fetchTrainers();
    }
  }, [isOpen, token]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const handleSubmit = async () => {
    if (!form.trainer_id || !form.rating || !form.comment) {
      setError("All fields are required.");
      return;
    }

    setLoading(true);
    setError("");

    const today = new Date().toISOString().split("T")[0]; // yyyy-mm-dd

    const payload = {
      client_id: userId,
      trainer_id: parseInt(form.trainer_id),
      rating: parseInt(form.rating),
      comment: form.comment,
      date: today
    };

    try {
      const response = await fetch("http://localhost:8080/api/reviews", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!response.ok) {
        const txt = await response.text();
        throw new Error(txt || "Failed to submit review");
      }

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Review submission error:", err);
      setError("Failed to submit review.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-md">
        <h2 className="text-2xl font-bold text-foreground mb-4">Leave a Review</h2>

        {/* Trainer */}
        <label className="block mb-1 text-sm">Trainer</label>
        <select
          name="trainer_id"
          value={form.trainer_id}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Select Trainer</option>
          {trainers.map((t) => (
            <option key={t.trainer_id} value={t.trainer_id}>
              {t.name} {t.last_name}
            </option>
          ))}
        </select>

        {/* Rating */}
        <label className="block mb-1 text-sm">Rating</label>
        <select
          name="rating"
          value={form.rating}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Rate (1–5)</option>
          <option value="1">⭐ 1</option>
          <option value="2">⭐⭐ 2</option>
          <option value="3">⭐⭐⭐ 3</option>
          <option value="4">⭐⭐⭐⭐ 4</option>
          <option value="5">⭐⭐⭐⭐⭐ 5</option>
        </select>

        {/* Comment */}
        <label className="block mb-1 text-sm">Comment</label>
        <textarea
          name="comment"
          value={form.comment}
          onChange={handleChange}
          className="w-full mb-4 px-3 py-2 rounded bg-input border border-border"
          placeholder="Write your review..."
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
            {loading ? "Submitting..." : "Submit Review"}
          </button>
        </div>
      </div>
    </div>
  );
}
