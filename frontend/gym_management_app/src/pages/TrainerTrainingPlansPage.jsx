import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { TRAINER_MENU } from "../constants/menuItems";
import { useNavigate } from "react-router-dom";

export default function TrainerTrainingPlansPage() {
  const { token } = useAuth();
  const [plans, setPlans] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchPlans = async () => {
      try {
        const response = await fetch(
          "http://localhost:8080/api/training_plans/notdetailed",
          {
            method: "GET",
            headers: {
              "Authorization": `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          throw new Error("Failed to load training plans");
        }

        const data = await response.json();
        setPlans(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error loading training plans:", err);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchPlans();
  }, [token]);

  return (
    <DashboardLayout menuItems={TRAINER_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold text-foreground mb-6">
          Training Plans
        </h1>

        {/* Back Button */}
        <button
          onClick={() => navigate("/dashboard")}
          className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          ← Back to Dashboard
        </button>

        {loading ? (
          <p className="text-muted-foreground">Loading training plans...</p>
        ) : plans.length === 0 ? (
          <p className="text-muted-foreground">No training plans available.</p>
        ) : (
          <div className="grid md:grid-cols-2 gap-6">
            {plans.map((plan, index) => (
              <div
                key={index}
                className="bg-card p-5 rounded-xl shadow border border-border"
              >
                <h2 className="text-xl font-semibold mb-2">{plan.name}</h2>
                <p className="text-sm text-muted-foreground mb-3">
                  {plan.description}
                </p>

                <p className="text-sm mb-1">
                  <strong>Difficulty:</strong> {plan.difficulty_level}
                </p>

                <p className="text-sm mb-1">
                  <strong>Training Goal:</strong> {plan.training_goal.name}
                </p>

                <p className="text-sm mb-1">
                  <strong>Exercises:</strong>{" "}
                  {plan.exercises.length === 0
                    ? "None"
                    : plan.exercises.map((e) => e.name).join(", ")}
                </p>
              </div>
            ))}
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
