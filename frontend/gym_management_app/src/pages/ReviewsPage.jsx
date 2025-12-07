import { useEffect, useState } from "react";
import { useAuth } from "../context/AuthContext";
import DashboardLayout from "../components/DashboardLayout";
import { RECEPTIONIST_MENU } from "../constants/menuItems";
import { useNavigate } from "react-router-dom";

export default function ReviewsPage() {
  const { token } = useAuth();
  const [reviews, setReviews] = useState([]);
  const [loading, setLoading] = useState(true);

  const navigate = useNavigate();

  useEffect(() => {
    const fetchReviews = async () => {
      try {
        const response = await fetch("http://localhost:8080/api/reviews/notdetailed", {
          method: "GET",
          headers: {
            Authorization: `Bearer ${token}`,
            "Content-Type": "application/json",
          },
        });

        if (!response.ok) {
          throw new Error("Failed to fetch reviews");
        }

        const data = await response.json();
        setReviews(Array.isArray(data) ? data : []);
      } catch (err) {
        console.error("Error fetching reviews:", err);
      } finally {
        setLoading(false);
      }
    };

    if (token) fetchReviews();
  }, [token]);

  const renderStars = (rating) => "⭐".repeat(rating);

  return (
    <DashboardLayout menuItems={RECEPTIONIST_MENU}>
      <div>
        <h1 className="text-3xl font-bold text-foreground mb-6">All Reviews</h1>

        <button
          onClick={() => navigate("/dashboard")}
          className="mb-4 bg-primary text-primary-foreground px-4 py-2 rounded-lg hover:opacity-90 transition"
        >
          ← Back to Dashboard
        </button>

        {loading ? (
          <p className="text-muted-foreground">Loading reviews...</p>
        ) : reviews.length === 0 ? (
          <p className="text-muted-foreground">No reviews found.</p>
        ) : (
          <div className="rounded-lg shadow bg-card overflow-hidden">
            <table className="w-full border-collapse">
              <thead className="bg-muted">
                <tr>
                  <th className="p-3 text-left">Client</th>
                  <th className="p-3 text-left">Trainer</th>
                  <th className="p-3 text-left">Rating</th>
                  <th className="p-3 text-left">Comment</th>
                  <th className="p-3 text-left">Date</th>
                </tr>
              </thead>

              <tbody>
                {reviews.map((rev, index) => (
                  <tr
                    key={index}
                    className="border-b border-border hover:bg-muted/60 transition"
                  >
                    <td className="p-3">
                      {rev.client_name} {rev.client_surname}
                    </td>

                    <td className="p-3">
                      {rev.trainer_name} {rev.trainer_surname}
                    </td>

                    <td className="p-3">
                      {renderStars(rev.rating || rev.Rating)}
                    </td>

                    <td className="p-3">
                      {rev.comment}
                    </td>

                    <td className="p-3">
                      {new Date(rev.date).toLocaleDateString()}
                    </td>
                  </tr>
                ))}
              </tbody>
            </table>
          </div>
        )}
      </div>
    </DashboardLayout>
  );
}
