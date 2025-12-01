import React, { useState, useEffect } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import DashboardCard from "../components/DashboardCard";
import { TRAINER_MENU } from "../constants/menuItems";
import ScheduleClassModal from "../components/ScheduleClassModal";
import TrainingPlanModal from "../components/TrainingPlanModal";


export default function TrainerDashboard() {
  const { token, userId } = useAuth();

  const [activeClassesCount, setActiveClassesCount] = useState(0);
  const [averageRating, setAverageRating] = useState(0);
  const [totalReviews, setTotalReviews] = useState(0);
  const [bio, setBio] = useState("");
  const [reviews, setReviews] = useState([]);
  const [reviewsLoading, setReviewsLoading] = useState(false);
  const renderStars = (rating) => "⭐".repeat(rating);
  const [showScheduleModal, setShowScheduleModal] = useState(false);
  const [showTrainingPlanModal, setShowTrainingPlanModal] = useState(false);





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

    const fetchBio = async () => {
      try {
        const response = await fetch(
          `http://localhost:8080/api/trainers/bio/${userId}`,
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
          console.error("[v0] Bio API error:", err);
          throw new Error("Failed to fetch bio");
        }

        const data = await response.text(); // bio is a simple string
        setBio(data);
      } catch (error) {
        console.error("[v0] Error fetching bio:", error);
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

    const fetchRecentReviews = async () => {
      setReviewsLoading(true);
      try {
        const response = await fetch(
          `http://localhost:8080/api/reviews/getByTrainer/${userId}`,
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
          console.error("[v0] Trainer reviews API error:", err);
          throw new Error("Failed to fetch trainer reviews");
        }

        const data = await response.json();
        setReviews(Array.isArray(data) ? data : []);
      } catch (error) {
        console.error("[v0] Error fetching trainer reviews:", error);
        setReviews([]);
      } finally {
        setReviewsLoading(false);
      }
    };


    fetchActiveClasses();
    fetchBio();
    fetchAverageRating();
    fetchTotalReviews();
    fetchRecentReviews();
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
            title="My Bio"
            value={bio || "No bio provided"}
            icon="🧍"
            color="accent"
          />
     
        </div>
      </div>
    <div className="grid grid-cols-1 lg:grid-cols-2 gap-6">
      <div className="bg-card rounded-lg shadow p-6">
        <h2 className="text-xl font-bold text-foreground mb-4">Recent Reviews</h2>

        <div className="space-y-3 max-h-96 overflow-y-auto">
          {reviewsLoading ? (
            <p className="text-muted-foreground">Loading reviews...</p>
          ) : reviews.length > 0 ? (
            reviews.map((review, index) => (
              <div key={index} className="p-3 bg-muted rounded border-l-4 border-accent">
                <div className="flex justify-between items-start mb-1">
                  <p className="font-medium text-sm">
                    {review.client_name} {review.client_surname}
                  </p>
                  <span className="text-xs">{renderStars(review.rating)}</span>
                </div>

                <p className="text-sm">{review.comment}</p>

                <p className="text-xs text-muted-foreground mt-1">
                  {new Date(review.date).toLocaleDateString()}
                </p>
              </div>
            ))
          ) : (
            <p className="text-muted-foreground">No reviews available</p>
          )}
        </div>
      </div>

      <div className="bg-card rounded-lg shadow p-6">
        <h2 className="text-xl font-bold text-foreground mb-4">Quick Actions</h2>

        <div className="space-y-3">
          <button
            onClick={() => setShowScheduleModal(true)}
            className="w-full bg-primary text-primary-foreground py-2 rounded-lg hover:opacity-90 transition-opacity"
          >
            Schedule New Group Class
          </button>


          <button
            onClick={() => setShowTrainingPlanModal(true)}
            className="w-full bg-accent text-accent-foreground py-2 rounded-lg hover:opacity-90 transition-opacity"
          >
            Add New Training Plan
          </button>
        </div>
      </div>
    </div>
    <ScheduleClassModal
      isOpen={showScheduleModal}
      onClose={() => setShowScheduleModal(false)}
      onSuccess={() => {}}
    />
    <TrainingPlanModal
      isOpen={showTrainingPlanModal}
      onClose={() => setShowTrainingPlanModal(false)}
      onSuccess={() => {}}
    />

    </DashboardLayout>
  );
}
