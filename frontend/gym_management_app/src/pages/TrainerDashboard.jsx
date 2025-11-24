import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import DashboardCard from "../components/DashboardCard";
import { TRAINER_MENU } from "../constants/menuItems";

export default function TrainerDashboard() {
  const { token, userId } = useAuth();

  const [activeClassesCount, setActiveClassesCount] = useState(0);
  const [clientCount, setClientCount] = useState(0);
  const [averageRating, setAverageRating] = useState(0);
  const [totalReviews, setTotalReviews] = useState(0);

  useEffect(() => {
    if (!token || !userId) return;

    // Fetch active classes count
    const fetchActiveClasses = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/group_classes/countByTrainer/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) {
          const err = await response.text();
          console.error("[v0] Active classes error:", err);
          throw new Error("Failed to fetch active classes");
        }

        const data = await response.json();
        setActiveClassesCount(data.count || data);
      } catch (error) {
        console.error("[v0] Active classes fetch error:", error);
      }
    };

    // Fetch number of assigned clients
    const fetchClientCount = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/trainers/${userId}/clients/count`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) return;

        const data = await response.json();
        setClientCount(data.count || data);
      } catch (error) {
        console.error("[v0] Client count fetch error:", error);
      }
    };

    // Fetch average trainer rating
    const fetchAverageRating = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/reviews/averageByTrainer/${userId}`,
          {
            method: "GET",
            headers: {
              Authorization: `Bearer ${token}`,
              "Content-Type": "application/json",
            },
          }
        );

        if (!response.ok) return;

        const data = await response.json();
        setAverageRating(data.average || data);
      } catch (error) {
        console.error("[v0] Average rating fetch error:", error);
      }
    };

    fetchActiveClasses();
    fetchClientCount();
    fetchAverageRating();
    fetchTotalReviews();
  }, [token, userId]);

  // Fetch total number of reviews
  const fetchTotalReviews = async () => {
    try {
      const response = await fetch(
        `http://localhost:8080/api/reviews/countByTrainer/${userId}`,
        {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        }
      );

      if (!response.ok) {
        const err = await response.text();
        console.error("[v0] Total reviews error:", err);
        throw new Error("Failed to fetch review count");
      }

      const data = await response.json();
      setTotalReviews(data.count || data);
    } catch (error) {
      console.error("[v0] Total reviews fetch error:", error);
    }
  };


  return (
    <DashboardLayout menuItems={TRAINER_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">
          Trainer Dashboard
        </h1>

        {/* Metric Cards */}
        <div className="grid grid-cols-1 md:grid-cols-2 lg:grid-cols-4 gap-6 mb-8">
          <DashboardCard
            title="Active Group Classes"
            value={activeClassesCount}
            icon="📅"
            color="primary"
          />
          <DashboardCard
            title="Total Reviews"
            value={totalReviews}
            icon="📝"
            color="accent"
          />
          <DashboardCard
            title="Average Rating"
            value={averageRating.toFixed(1)}
            icon="⭐"
            color="primary"
          />
          <DashboardCard
            title="Assigned Clients"
            value={clientCount}
            icon="👥"
            color="accent"
          />     
        </div>
        {/* Additional sections can go here */}
      </div>
    </DashboardLayout>
  );
}
