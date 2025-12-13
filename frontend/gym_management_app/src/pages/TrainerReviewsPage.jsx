import React, { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { TRAINER_MENU } from "../constants/menuItems";
import { useNavigate } from "react-router-dom";

export default function TrainerReviewsPage() {
  const { token, userId } = useAuth();
  const [reviews, setReviews] = useState([]);
  const [filteredReviews, setFilteredReviews] = useState([]);
  const [search, setSearch] = useState("");
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  const renderStars = (rating) => "⭐".repeat(rating);

  useEffect(() => {
    const fetchReviews = async () => {
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
          throw new Error("Failed to fetch trainer reviews");
        }

        const data = await response.json();
        const list = Array.isArray(data) ? data : [];

        setReviews(list);
        setFilteredReviews(list);
      } catch (err) {
        console.error("Error fetching trainer reviews:", err);
      } finally {
        setLoading(false);
      }
    };

    if (token && userId) fetchReviews();
  }, [token, userId]);

  // 🔍 SEARCH (client, comment, date)
  useEffect(() => {
    const q = search.toLowerCase();

    setFilteredReviews(
      reviews.filter((review) => {
        const dateString = new Date(review.date)
          .toLocaleDateString()
          .toLowerCase();

        return (
          review.client_name.toLowerCase().includes(q) ||
          review.client_surname.toLowerCase().includes(q) ||
          review.comment.toLowerCase().includes(q) ||
          dateString.includes(q)
        );
      })
    );
  }, [search, reviews]);

  return (
    <DashboardLayout menuItems={TRAINER_MENU}>
      <div className="p-6">
        <h1 className="text-3xl font-bold text-foreground mb-6">
          My Reviews
        </h1>

        {/* Back Button */}
        <button
          onClick={() => navigate("/dashboard")}
          className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          ← Back to Dashboard
        </button>

        {/* SEARCH BAR (only addition) */}
        <input
          type="text"
          placeholder="Search by client, comment or date..."
          value={search}
          onChange={(e) => setSearch(e.target.value)}
          className="mb-4 block w-full md:w-1/3 px-3 py-2 border border-border rounded bg-input"
        />

        {loading ? (
          <p className="text-muted-foreground">Loading reviews...</p>
        ) : filteredReviews.length === 0 ? (
          <p className="text-muted-foreground">You have no reviews yet.</p>
        ) : (
          <div className="space-y-4">
            {filteredReviews.map((review, index) => (
              <div
                key={index}
                className="bg-card p-5 rounded-xl shadow border border-border"
              >
                <div className="flex justify-between items-start mb-2">
                  <p className="font-medium">
                    {review.client_name} {review.client_surname}
                  </p>
                  <span className="text-sm">
                    {renderStars(review.rating || review.Rating)}
                  </span>
                </div>

                <p className="text-sm text-muted-foreground mb-2">
                  Date: {review.date}
                </p>

                <p className="text-sm">{review.comment}</p>
              </div>
            ))}
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
