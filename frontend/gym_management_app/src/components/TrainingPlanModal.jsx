import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";

export default function TrainingPlanModal({ isOpen, onClose, onSuccess }) {
  const { token } = useAuth();

  const emptyForm = {
    name: "",
    description: "",
    difficulty_level: "",
    training_goal_id: "",
    selectedExerciseName: ""
  };

  const [form, setForm] = useState(emptyForm);
  const [goals, setGoals] = useState([]);
  const [exercises, setExercises] = useState([]);
  const [addedExercises, setAddedExercises] = useState([]); // [{id, name}]
  const [loading, setLoading] = useState(false);
  const [error, setError] = useState("");

  // Load goals + exercises when opening
  useEffect(() => {
    if (!isOpen) return;

    setForm(emptyForm);
    setAddedExercises([]);
    setError("");
    setLoading(false);

    const fetchDependencies = async () => {
      try {
        const resGoals = await fetch("http://localhost:8080/api/training_goals", {
          headers: { Authorization: `Bearer ${token}` }
        });

        const resExercises = await fetch("http://localhost:8080/api/exercises", {
          headers: { Authorization: `Bearer ${token}` }
        });

        const goalsData = await resGoals.json();
        const exercisesData = await resExercises.json();

        setGoals(goalsData);
        setExercises(exercisesData);
      } catch (err) {
        console.error("Error fetching training plan modal data:", err);
      }
    };

    fetchDependencies();
  }, [isOpen, token]);

  if (!isOpen) return null;

  const handleChange = (e) => {
    setForm({
      ...form,
      [e.target.name]: e.target.value
    });
  };

  const addExercise = () => {
    if (!form.selectedExerciseName) return;

    const found = exercises.find(e => e.name === form.selectedExerciseName);
    if (!found) return;

    // Prevent duplicates
    if (addedExercises.some(ex => ex.id === found.exercise_id)) return;

    setAddedExercises(prev => [
      ...prev,
      { id: found.exercise_id, name: found.name }
    ]);

    setForm({ ...form, selectedExerciseName: "" });
  };

  const removeExercise = (id) => {
    setAddedExercises(prev => prev.filter(ex => ex.id !== id));
  };

  const handleSubmit = async () => {
    if (!form.name || !form.description || !form.difficulty_level || !form.training_goal_id) {
      setError("All fields except exercises are required.");
      return;
    }

    const payload = {
      name: form.name,
      description: form.description,
      difficulty_level: form.difficulty_level,
      training_goal_id: parseInt(form.training_goal_id),
      exercises_ids: addedExercises.map(e => e.id)
    };

    setLoading(true);
    setError("");

    try {
      const res = await fetch("http://localhost:8080/api/training_plans", {
        method: "POST",
        headers: {
          Authorization: `Bearer ${token}`,
          "Content-Type": "application/json"
        },
        body: JSON.stringify(payload)
      });

      if (!res.ok) throw new Error(await res.text());

      onSuccess();
      onClose();
    } catch (err) {
      console.error("Create training plan error:", err);
      setError("Failed to create training plan.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="fixed inset-0 bg-black/50 flex items-center justify-center z-50">
      <div className="bg-card p-6 rounded-xl shadow-lg w-full max-w-lg">
        <h2 className="text-2xl font-bold text-foreground mb-4">Add New Training Plan</h2>

        {/* Name */}
        <label className="block mb-1 text-sm">Name</label>
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

        {/* Difficulty */}
        <label className="block mb-1 text-sm">Difficulty Level</label>
        <select
          name="difficulty_level"
          value={form.difficulty_level}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Select Difficulty</option>
          <option value="EASY">Easy</option>
          <option value="MEDIUM">Medium</option>
          <option value="HARD">Hard</option>
        </select>

        {/* Training Goal */}
        <label className="block mb-1 text-sm">Training Goal</label>
        <select
          name="training_goal_id"
          value={form.training_goal_id}
          onChange={handleChange}
          className="w-full mb-3 px-3 py-2 rounded bg-input border border-border"
        >
          <option value="">Select Training Goal</option>
          {goals.map(goal => (
            <option key={goal.training_goal_id} value={goal.training_goal_id}>
              {goal.name}
            </option>
          ))}
        </select>

        {/* Exercises */}
        <label className="block mb-1 text-sm">Exercises</label>
        <div className="flex space-x-2 mb-3">
          <select
            name="selectedExerciseName"
            value={form.selectedExerciseName}
            onChange={handleChange}
            className="flex-grow px-3 py-2 rounded bg-input border border-border"
          >
            <option value="">Select Exercise</option>
            {exercises.map((ex, index) => (
            <option key={ex.exercise_id || index} value={ex.name}>
                {ex.name}
            </option>
            ))}
          </select>

          <button
            onClick={addExercise}
            className="px-4 py-2 bg-primary text-primary-foreground rounded hover:opacity-90"
          >
            +
          </button>
        </div>

        {/* Added exercises list */}
        <div className="space-y-2 mb-4">
          {addedExercises.map((ex, index) => (
            <div key={ex.id || index} className="flex justify-between bg-muted p-2 rounded">
              <span>{ex.name}</span>
              <button
                onClick={() => removeExercise(ex.id)}
                className="text-red-500 font-bold"
              >
                ✕
              </button>
            </div>
          ))}
        </div>

        {error && <p className="text-red-500 text-sm mb-3">{error}</p>}

        {/* Actions */}
        <div className="flex justify-end space-x-3 mt-4">
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
            {loading ? "Submitting..." : "Create Training Plan"}
          </button>
        </div>
      </div>
    </div>
  );
}
